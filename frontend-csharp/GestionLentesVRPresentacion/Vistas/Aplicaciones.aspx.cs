using FrontVR.GestionlentesvrWS;
using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Web.Services.Description;
using System.Web.UI;
using System.Web.UI.WebControls;

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
                if (!string.IsNullOrEmpty(Request.QueryString["deleteId"]))
                {
                    int id = int.Parse(Request.QueryString["deleteId"]);
                    try
                    {
                        aplicacionWSClient.eliminarAplicacion(id);
                    }
                    catch (System.Exception ex)
                    {
                        // Logging o manejo de error
                        System.Diagnostics.Debug.WriteLine($"[Eliminar Error] {ex.Message}");
                    }
                    Response.Redirect("Aplicaciones.aspx"); // Recarga limpia
                }

                BindGrid();
            }
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
            int index = Convert.ToInt32(e.CommandArgument);
            int id = int.Parse(gvAplicaciones.DataKeys[index].Value.ToString());
            
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

                ScriptManager.RegisterStartupScript(this, GetType(), "abrirModal", "$('#modalAplicacion').modal('show');", true);
            }
            else if (e.CommandName == "EliminarApp")
            {
                aplicacionWSClient.eliminarAplicacion(id);
                BindGrid();
            }
        }
        protected string GetBadgeCss(object activoObj)
        {
            // Si viene como string 's' o 'n'
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

        private void LimpiarFormulario()
        {
            hfIdAplicacion.Value = "";
            txtNombre.Text = "";
            txtVersion.Text = "";
            txtTamano.Text = "";
            txtDescripcion.Text = "";
            txtDesarrollador.Text = "";
            ddlCategoria.SelectedValue = "";
            lblError.Visible = false;
        }

        //protected string GetBadgeCss(object activoObj)
        //{
        //    bool activo = true;// (bool)activoObj;
        //    return activo ? "badge bg-success" : "badge bg-secondary";
        //}

        //protected string GetEstadoTexto(object activoObj)
        //{
        //    //return ((bool)activoObj) ? "Instalada" : "Disponible";
        //    return true ? "Instalada" : "Disponible";
        //}
    }
}
