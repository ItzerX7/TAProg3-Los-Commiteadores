using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
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
            if (Session["usuario"] == null)
                Response.Redirect("~/Login.aspx");

            if (!IsPostBack) BindGrid();
        }

        private void BindGrid()
        {
            var aplicaciones = aplicacionWSClient.listarAplicacion();
            gvAplicaciones.DataSource = aplicaciones;
            gvAplicaciones.DataBind();
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
            /*
            if (!DateTime.TryParse(txtFechaLanzamiento.Text, CultureInfo.CurrentCulture,
                       DateTimeStyles.None, out DateTime fechaLanzamiento))
            {
                lblError.Text = "La fecha de lanzamiento es inválida.";
                lblError.Visible = true;
                return;
            }*/

            var nuevaApp = new aplicacion
            {
                id = string.IsNullOrWhiteSpace(hfIdAplicacion.Value) ? 0 : int.Parse(hfIdAplicacion.Value),
                nombre = txtNombre.Text.Trim(),
                version = txtVersion.Text.Trim(),
                tamanomb = tamMb,
                descripcion = txtDescripcion.Text.Trim(),
                desarrollador = txtDesarrollador.Text.Trim(),
                //rutaInstalador = txtRutaInstalador.Text.Trim(),
                //fechaLanzamiento = fechaLanzamiento.Date,
                //fechaLanzamientoSpecified = true,
                categoria = (categoriaAplicacion)Enum.Parse(typeof(categoriaAplicacion), ddlCategoria.SelectedValue),
                categoriaSpecified = true,
                //activo = chkActivo.Checked
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
                    "$('#modalNuevaApp').modal('hide');", true);

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
            lblError.Visible = false;
        }

        // ========= ACCIONES =========
        protected void gvAplicaciones_RowCommand(object sender, GridViewCommandEventArgs e)
        {
            int id = Convert.ToInt32(e.CommandArgument);

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
                txtNombre.Text = app.nombre;
                txtVersion.Text = app.version;
                txtTamano.Text = app.tamanomb.ToString(CultureInfo.InvariantCulture);
                txtDescripcion.Text = app.descripcion;
                txtDesarrollador.Text = app.desarrollador;
                //txtRutaInstalador.Text = app.rutaInstalador;
                //txtFechaLanzamiento.Text = app.fechaLanzamiento.ToString("yyyy-MM-dd");
                ddlCategoria.SelectedValue = app.categoria.ToString();
                //chkActivo.Checked = app.activo;

                // Mostrar modal
                ScriptManager.RegisterStartupScript(this, GetType(), "AbrirModal", "$('#modalNuevaApp').modal('show');", true);
            }
        }

        protected string GetBadgeCss(object activoObj)
        {
            bool activo = true;// (bool)activoObj;
            return activo ? "badge bg-success" : "badge bg-secondary";
        }

        protected string GetEstadoTexto(object activoObj)
        {
            //return ((bool)activoObj) ? "Instalada" : "Disponible";
            return true ? "Instalada" : "Disponible";
        }
    }
}
