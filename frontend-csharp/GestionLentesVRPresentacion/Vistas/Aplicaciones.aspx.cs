using FrontVR.GestionlentesvrWS;
using System;
using System.Globalization;
using System.ServiceModel;
using System.Text;
using System.Threading;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace FrontVR.Vistas
{
    public static class EstadoEliminacion
    {
        public static volatile bool EliminacionCompletada = false;
        public static volatile string MensajeError = null;
    }

    public partial class Aplicaciones : Page
    {
        private AplicacionWSClient aplicacionWSClient;

        protected void Page_Init(object sender, EventArgs e)
        {
            aplicacionWSClient = new GestionlentesvrWS.AplicacionWSClient();
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            if (Request.QueryString["checkEliminacion"] == "true")
            {
                if (EstadoEliminacion.EliminacionCompletada)
                {
                    EstadoEliminacion.EliminacionCompletada = false; // Reset
                    Response.Write("OK");
                }
                else if (!string.IsNullOrEmpty(EstadoEliminacion.MensajeError))
                {
                    string mensaje = EstadoEliminacion.MensajeError;
                    EstadoEliminacion.MensajeError = null;
                    Response.Write("ERROR: " + mensaje);
                }
                else
                {
                    Response.Write("PENDING");
                }

                Response.End();
                return;
            }

            if (!IsPostBack)
            {
                BindGrid();
            }
        }


        private void BindGrid()
        {
            var aplicaciones = aplicacionWSClient.listarAplicacion();
            gvAplicaciones.DataSource = aplicaciones;
            gvAplicaciones.DataBind();
            phDispositivos.Controls.Clear();
        }

        // ========= NUEVA APP =========
        protected void btnGuardar_Click(object sender, EventArgs e)
        {
            if (string.IsNullOrWhiteSpace(txtNombre.Text))
            {
                lblError.Text = "El nombre es obligatorio.";
                lblError.Visible = true;
                return;
            }

            if (!double.TryParse(txtTamano.Text, NumberStyles.Any, CultureInfo.InvariantCulture, out double tamMb))
            {
                lblError.Text = "Tamaño inválido.";
                lblError.Visible = true;
                return;
            }

            var nuevaApp = new aplicacion
            {
                id = string.IsNullOrWhiteSpace(hfIdAplicacion.Value) ? 0 : int.Parse(hfIdAplicacion.Value),
                nombre = txtNombre.Text.Trim(),
                version = txtVersion.Text.Trim(),
                tamanomb = tamMb,
                descripcion = txtDescripcion.Text.Trim(),
                desarrollador = txtDesarrollador.Text.Trim(),
                categoria = (categoriaAplicacion)Enum.Parse(typeof(categoriaAplicacion), ddlCategoria.SelectedValue),
                categoriaSpecified = true,
            };

            try
            {
                bool esNuevo = nuevaApp.id == 0;

                if (esNuevo)
                    aplicacionWSClient.registrarAplicacion(nuevaApp);
                else
                    aplicacionWSClient.actualizarAplicacion(nuevaApp);

                ClearModal();

                ScriptManager.RegisterStartupScript(this, GetType(), "CerrarModal",
                    "$('#modalAplicacion').modal('hide');", true);

                string mensaje = esNuevo ? "Aplicación registrada exitosamente" : "Aplicación actualizada correctamente";
                string script = $@"
                    Swal.fire({{
                        icon: 'success',
                        title: '{mensaje}',
                        confirmButtonText: 'OK'
                    }}).then(() => {{
                        window.location.href = window.location.href;
                    }});";
                ScriptManager.RegisterStartupScript(this, GetType(), "SwalSuccess", script, true);
            }
            catch (System.Exception ex)
            {
                lblError.Text = "Error: " + ex.Message;
                lblError.Visible = true;
            }
        }

        private void ClearModal()
        {
            hfIdAplicacion.Value = "";
            txtNombre.Text = "";
            txtVersion.Text = "";
            txtTamano.Text = "";
            txtDescripcion.Text = "";
            txtDesarrollador.Text = "";
            ddlCategoria.SelectedIndex = 0;
            lblError.Visible = false;
        }

        // ========= ACCIONES =========
        protected void gvAplicaciones_RowCommand(object sender, GridViewCommandEventArgs e)
        {
            int id = Convert.ToInt32(e.CommandArgument);

            if (e.CommandName == "EditarApp")
            {
                var app = aplicacionWSClient.obtenerAplicacion(id);

                hfIdAplicacion.Value = app.id.ToString();
                txtNombre.Text = app.nombre;
                txtVersion.Text = app.version;
                txtTamano.Text = app.tamanomb.ToString(CultureInfo.InvariantCulture);
                txtDescripcion.Text = app.descripcion;
                txtDesarrollador.Text = app.desarrollador;
                ddlCategoria.SelectedValue = app.categoria.ToString();

                ScriptManager.RegisterStartupScript(this, GetType(), "abrirModal", @"
                    document.getElementById('modalNuevaAppLabel').textContent = 'Editar aplicación';
                    var modal = new bootstrap.Modal(document.getElementById('modalAplicacion'));
                    modal.show();", true);
            }
            else if (e.CommandName == "EliminarApp")
            {
                int idApp = id;
                var app = aplicacionWSClient.obtenerAplicacion(idApp);
                var dispositivos = aplicacionWSClient.listarDispositivosPorAplicaciones(idApp);

                if (dispositivos != null && dispositivos.Length > 0)
                {
                    hfEliminarIdApp.Value = idApp.ToString(); // Setea el campo oculto
                    string mensaje = $"La aplicación <strong>{app.nombre}</strong> está instalada en <strong>{dispositivos.Length}</strong> dispositivo(s). ¿Desea eliminarla de todos ellos?";

                    string script = $@"
                        Swal.fire({{
                            title: 'Confirmar eliminación',
                            html: '{mensaje}',
                            icon: 'warning',
                            showCancelButton: true,
                            confirmButtonText: 'Sí, eliminar',
                            cancelButtonText: 'Cancelar'
                        }}).then((result) => {{
                            if (result.isConfirmed) {{
                                document.getElementById('{btnConfirmarEliminar.ClientID}').click();
                            }}
                        }});";
                    ScriptManager.RegisterStartupScript(this, GetType(), "SwalConfirmarEliminacion", script, true);
                }
                else
                {
                    aplicacionWSClient.eliminarAplicacion(idApp);
                    BindGrid();
                }
            }
            else if (e.CommandName == "VerDispApp")
            {
                MostrarDispositivosPorAplicacion(id);
            }
        }

        private void MostrarDispositivosPorAplicacion(int idAplicacion)
        {
            phDispositivos.Controls.Clear();
            var dispositivos = aplicacionWSClient.listarDispositivosPorAplicaciones(idAplicacion);

            var aplicacion = aplicacionWSClient.obtenerAplicacion(idAplicacion);
            string nombreAplicacion = aplicacion?.nombre ?? "Aplicación desconocida";

            if (dispositivos != null && dispositivos.Length > 0)
            {
                StringBuilder html = new StringBuilder();

                html.AppendFormat("<h4 class='mt-4'>Dispositivos vinculados a <strong>{0}</strong></h4>", nombreAplicacion);
                html.Append("<table class='table table-bordered table-dark mt-2'>");
                html.Append("<thead><tr>");
                html.Append("<th>Nombre</th>");
                html.Append("<th>Modelo</th>");
                html.Append("<th>Número de Serie</th>");
                html.Append("<th>Estado</th>");
                html.Append("</tr></thead><tbody>");

                foreach (var dispositivo in dispositivos)
                {
                    html.Append("<tr>");
                    html.AppendFormat("<td>{0}</td>", dispositivo.nombre);
                    html.AppendFormat("<td>{0}</td>", dispositivo.modelo);
                    html.AppendFormat("<td>{0}</td>", dispositivo.numeroSerie);
                    html.AppendFormat("<td>{0}</td>", dispositivo.estado.ToString());
                    html.Append("</tr>");
                }

                html.Append("</tbody></table>");
                phDispositivos.Controls.Add(new Literal { Text = html.ToString() });
            }
            else
            {
                phDispositivos.Controls.Add(new Literal
                {
                    Text = "<div class='alert alert-info mt-3'>No se encontraron dispositivos para esta aplicación.</div>"
                });
            }
        }

        protected string GetBadgeCss(object activoObj)
        {
            string estado = activoObj?.ToString().ToLower();
            bool activo = (estado == "s" || estado == "true");
            return activo ? "badge bg-success" : "badge bg-secondary";
        }

        protected string GetEstadoTexto(object activoObj)
        {
            string estado = activoObj?.ToString().ToLower();
            bool activo = (estado == "s" || estado == "true");
            return activo ? "Instalada" : "Disponible";
        }

        protected void btnConfirmarEliminar_Click(object sender, EventArgs e)
        {
            if (int.TryParse(hfEliminarIdApp.Value, out int idApp))
            {
                // Mostrar el spinner
                ScriptManager.RegisterStartupScript(this, GetType(), "MostrarSpinner", "$('#spinnerOperacion').show();", true);

                // Reinicia el estado antes de iniciar
                EstadoEliminacion.EliminacionCompletada = false;
                EstadoEliminacion.MensajeError = null;

                Thread eliminarThread = new Thread(() =>
                {
                    try
                    {
                        aplicacionWSClient.eliminarAplicacion(idApp);
                        EstadoEliminacion.EliminacionCompletada = true;
                    }
                    catch (System.Exception ex)
                    {
                        EstadoEliminacion.MensajeError = ex.Message;
                    }
                });

                eliminarThread.IsBackground = true;
                eliminarThread.Start();

                // JavaScript para verificar estado
                string script = @"
            const checkEstadoEliminacion = () => {
                fetch('Aplicaciones.aspx?checkEliminacion=true')
                    .then(response => response.text())
                    .then(result => {
                        if (result === 'OK') {
                            $('#spinnerOperacion').hide();
                            Swal.fire({
                                icon: 'success',
                                title: 'Aplicación eliminada',
                                showConfirmButton: false,
                                timer: 1500
                            }).then(() => {
                                window.location.href = window.location.href;
                            });
                        } else if (result.startsWith('ERROR')) {
                            $('#spinnerOperacion').hide();
                            Swal.fire({
                                icon: 'error',
                                title: 'Error al eliminar',
                                text: result.replace('ERROR:', '').trim()
                            });
                        } else {
                            setTimeout(checkEstadoEliminacion, 1000);
                        }
                    })
                    .catch(() => setTimeout(checkEstadoEliminacion, 1000));
            };
            checkEstadoEliminacion();";

                ScriptManager.RegisterStartupScript(this, GetType(), "CheckEliminacion", script, true);
            }
        }


    }
}
