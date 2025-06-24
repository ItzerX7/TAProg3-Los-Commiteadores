using System;
using System.Globalization;
using System.Text;
using System.Web.UI;
using System.Web.UI.WebControls;
using FrontVR.GestionlentesvrWS;

namespace FrontVR.Vistas
{
    public partial class Aplicaciones : Page
    {
        private AplicacionWSClient aplicacionWSClient;

        protected void Page_Init(object sender, EventArgs e)
        {
            aplicacionWSClient = new GestionlentesvrWS.AplicacionWSClient();
        }

        protected void Page_Load(object sender, EventArgs e)
        {
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

            // Limpiar tabla de dispositivos al recargar
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
                if (nuevaApp.id == 0)
                    aplicacionWSClient.registrarAplicacion(nuevaApp);
                else
                    aplicacionWSClient.actualizarAplicacion(nuevaApp);

                ClearModal();

                ScriptManager.RegisterStartupScript(
                    this, GetType(), "CerrarModal",
                    "$('#modalAplicacion').modal('hide');", true);

                BindGrid();
            }
            catch (System.Exception ex)
            {
                lblError.Text = "Error: " + ex.Message;
                lblError.Visible = true;
            }
        }

        private void ClearModal()
        {
            txtNombre.Text = txtVersion.Text = txtTamano.Text = string.Empty;
            txtDescripcion.Text = txtDesarrollador.Text = ddlCategoria.SelectedValue = string.Empty;
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
                aplicacionWSClient.eliminarAplicacion(id);
                BindGrid();
            }
            else if (e.CommandName == "VerDispApp")
            {
                MostrarDispositivosPorAplicacion(id);
            }
        }

        private void MostrarDispositivosPorAplicacion(int idAplicacion)
        {
            // Limpiar tabla anterior
            phDispositivos.Controls.Clear();

            // Usar el WS correcto
            var dispositivos = aplicacionWSClient.listarDispositivosPorAplicaciones(idAplicacion);

            if (dispositivos != null && dispositivos.Length > 0)
            {
                StringBuilder html = new StringBuilder();

                html.Append("<h4 class='mt-4'>Dispositivos vinculados</h4>");
                html.Append("<table class='table table-bordered table-dark mt-2'>");
                html.Append("<thead><tr>");
                html.Append("<th>Nombre</th>");
                html.Append("<th>Modelo</th>");
                html.Append("<th>Número de Serie</th>");
                html.Append("<th>Estado</th>");
                html.Append("</tr></thead>");
                html.Append("<tbody>");

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
    }
}
