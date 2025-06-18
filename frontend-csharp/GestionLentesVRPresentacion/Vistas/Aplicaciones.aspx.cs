using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Web.UI;
using System.Web.UI.WebControls;
using FrontVR.ServiceReference1;

namespace FrontVR.Vistas
{
    public partial class Aplicaciones : Page
    {
        private AplicacionWSClient aplicacionWSClient;

        protected void Page_Init(object sender, EventArgs e)
        {
            aplicacionWSClient = new AplicacionWSClient();
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

            if (!DateTime.TryParse(txtFechaLanzamiento.Text, CultureInfo.CurrentCulture,
                       DateTimeStyles.None, out DateTime fechaLanzamiento))
            {
                lblError.Text = "La fecha de lanzamiento es inválida.";
                lblError.Visible = true;
                return;
            }


            var nuevaApp = new aplicacion
            {
                nombre = txtNombre.Text.Trim(),
                version = txtVersion.Text.Trim(),
                tamanomb = tamMb,
                descripcion = txtDescripcion.Text.Trim(),
                desarrollador = txtDesarrollador.Text.Trim(),
                categoria = (categoriaAplicacion)Enum.Parse(typeof(categoriaAplicacion), ddlCategoria.SelectedValue),
                categoriaSpecified = true
            };

            try
            {
                aplicacionWSClient.registrarAplicacion(nuevaApp);
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

        // ========= ACCIÓN INSTALAR/DESINSTALAR =========
        protected void gvAplicaciones_RowCommand(object sender, GridViewCommandEventArgs e)
        {
            int id = Convert.ToInt32(e.CommandArgument);

            if (e.CommandName == "EliminarApp")
            {
                try
                {
                    aplicacionWSClient.eliminarAplicacion(id);
                    System.Diagnostics.Debug.WriteLine($"[Eliminado] ID: {id}");
                }
                catch (System.Exception ex)
                {
                    System.Diagnostics.Debug.WriteLine($"[Error al eliminar] {ex.Message}");
                }

                BindGrid();
                return;
            }

            // Otras acciones (como ToggleInstall si decides mantenerlo)
        }



        protected string GetBadgeCss(object activoObj)
        {
            bool activo = (bool)activoObj;
            return activo ? "badge bg-success" : "badge bg-secondary";
        }

        protected string GetEstadoTexto(string activoObj)
        {
            return activoObj;
        }
    }
}
