using System;
<<<<<<< HEAD
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
=======
using System.Globalization;
using System.Text;
>>>>>>> 72e72ce (Ignorar archivos temporales de Visual Studio y build)
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
<<<<<<< HEAD
            if (Session["usuario"] == null)
                Response.Redirect("~/Login.aspx");

            if (!IsPostBack) BindGrid();
=======
            if (!IsPostBack)
            {
                BindGrid();
            }
>>>>>>> 72e72ce (Ignorar archivos temporales de Visual Studio y build)
        }

        private void BindGrid()
        {
            var aplicaciones = aplicacionWSClient.listarAplicacion();
            gvAplicaciones.DataSource = aplicaciones;
            gvAplicaciones.DataBind();
<<<<<<< HEAD
=======

            // Limpiar tabla de dispositivos al recargar
            phDispositivos.Controls.Clear();
>>>>>>> 72e72ce (Ignorar archivos temporales de Visual Studio y build)
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
<<<<<<< HEAD
            /*
            if (!DateTime.TryParse(txtFechaLanzamiento.Text, CultureInfo.CurrentCulture,
                       DateTimeStyles.None, out DateTime fechaLanzamiento))
            {
                lblError.Text = "La fecha de lanzamiento es inválida.";
                lblError.Visible = true;
                return;
            }*/
=======
>>>>>>> 72e72ce (Ignorar archivos temporales de Visual Studio y build)

            var nuevaApp = new aplicacion
            {
                id = string.IsNullOrWhiteSpace(hfIdAplicacion.Value) ? 0 : int.Parse(hfIdAplicacion.Value),
                nombre = txtNombre.Text.Trim(),
                version = txtVersion.Text.Trim(),
                tamanomb = tamMb,
                descripcion = txtDescripcion.Text.Trim(),
                desarrollador = txtDesarrollador.Text.Trim(),
<<<<<<< HEAD
                //rutaInstalador = txtRutaInstalador.Text.Trim(),
                //fechaLanzamiento = fechaLanzamiento.Date,
                //fechaLanzamientoSpecified = true,
                categoria = (categoriaAplicacion)Enum.Parse(typeof(categoriaAplicacion), ddlCategoria.SelectedValue),
                categoriaSpecified = true,
                //activo = chkActivo.Checked
=======
                categoria = (categoriaAplicacion)Enum.Parse(typeof(categoriaAplicacion), ddlCategoria.SelectedValue),
                categoriaSpecified = true,
>>>>>>> 72e72ce (Ignorar archivos temporales de Visual Studio y build)
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
<<<<<<< HEAD
                    "$('#modalNuevaApp').modal('hide');", true);
=======
                    "$('#modalAplicacion').modal('hide');", true);
>>>>>>> 72e72ce (Ignorar archivos temporales de Visual Studio y build)

                BindGrid();
            }
            catch (System.Exception ex)
            {
                lblError.Text = "Error: " + ex.Message;
                lblError.Visible = true;
            }
        }

<<<<<<< HEAD


        private void ClearModal()
        {
            txtNombre.Text = txtVersion.Text = txtTamano.Text = string.Empty;
=======
        private void ClearModal()
        {
            txtNombre.Text = txtVersion.Text = txtTamano.Text = string.Empty;
            txtDescripcion.Text = txtDesarrollador.Text = ddlCategoria.SelectedValue = string.Empty;
>>>>>>> 72e72ce (Ignorar archivos temporales de Visual Studio y build)
            lblError.Visible = false;
        }

        // ========= ACCIONES =========
        protected void gvAplicaciones_RowCommand(object sender, GridViewCommandEventArgs e)
        {
            int id = Convert.ToInt32(e.CommandArgument);

<<<<<<< HEAD
            if (e.CommandName == "EliminarApp")
            {
                try
                {
                    aplicacionWSClient.eliminarAplicacion(id);
                }
                catch (System.Exception ex)
                {
                    System.Diagnostics.Debug.WriteLine($"[Error al eliminar] {ex.Message}");
                }
                BindGrid();
            }
            else if (e.CommandName == "EditarApp")
            {
                var app = aplicacionWSClient.obtenerAplicacion(id);

                // Precargar datos en los campos del modal
                hfIdAplicacion.Value = app.id.ToString(); // Necesitas agregar un HiddenField en el modal para ID
=======
            if (e.CommandName == "EditarApp")
            {
                var app = aplicacionWSClient.obtenerAplicacion(id);

                hfIdAplicacion.Value = app.id.ToString();
>>>>>>> 72e72ce (Ignorar archivos temporales de Visual Studio y build)
                txtNombre.Text = app.nombre;
                txtVersion.Text = app.version;
                txtTamano.Text = app.tamanomb.ToString(CultureInfo.InvariantCulture);
                txtDescripcion.Text = app.descripcion;
                txtDesarrollador.Text = app.desarrollador;
<<<<<<< HEAD
                //txtRutaInstalador.Text = app.rutaInstalador;
                //txtFechaLanzamiento.Text = app.fechaLanzamiento.ToString("yyyy-MM-dd");
                ddlCategoria.SelectedValue = app.categoria.ToString();
                //chkActivo.Checked = app.activo;

                // Mostrar modal
                ScriptManager.RegisterStartupScript(this, GetType(), "AbrirModal", "$('#modalNuevaApp').modal('show');", true);
=======
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
           // var dispositivos = aplicacionWSClient.listarDispositivosPorAplicaciones(idAplicacion);

            if (true)
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

               

                html.Append("</tbody></table>");

                phDispositivos.Controls.Add(new Literal { Text = html.ToString() });
            }
            else
            {
                phDispositivos.Controls.Add(new Literal
                {
                    Text = "<div class='alert alert-info mt-3'>No se encontraron dispositivos para esta aplicación.</div>"
                });
>>>>>>> 72e72ce (Ignorar archivos temporales de Visual Studio y build)
            }
        }

        protected string GetBadgeCss(object activoObj)
        {
<<<<<<< HEAD
            bool activo = true;// (bool)activoObj;
=======
            string estado = activoObj?.ToString().ToLower();
            bool activo = (estado == "s" || estado == "true");
>>>>>>> 72e72ce (Ignorar archivos temporales de Visual Studio y build)
            return activo ? "badge bg-success" : "badge bg-secondary";
        }

        protected string GetEstadoTexto(object activoObj)
        {
<<<<<<< HEAD
            //return ((bool)activoObj) ? "Instalada" : "Disponible";
            return true ? "Instalada" : "Disponible";
=======
            string estado = activoObj?.ToString().ToLower();
            bool activo = (estado == "s" || estado == "true");
            return activo ? "Instalada" : "Disponible";
>>>>>>> 72e72ce (Ignorar archivos temporales de Visual Studio y build)
        }
    }
}
