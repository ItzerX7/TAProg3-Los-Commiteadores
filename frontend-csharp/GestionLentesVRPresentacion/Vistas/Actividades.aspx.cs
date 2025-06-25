using System;
using System.Web.UI.WebControls;
using FrontVR.GestionlentesvrWS;

namespace FrontVR.Vistas
{
    public partial class Actividades : System.Web.UI.Page
    {
        private ActividadWSClient actividadWSClient;

        protected void Page_Init(object sender, EventArgs e)
        {
            actividadWSClient = new ActividadWSClient();
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            //int rol = Convert.ToInt32(Session["rol"]);
            //if (rol != 1 && rol != 2)
            //{
            //    Response.Redirect("~/PantallaInicio.aspx");
            //    return;
            //}

            if (!IsPostBack)
            {
                BindGrid();
            }
        }

        private void BindGrid()
        {
            try
            {
                var actividades = actividadWSClient.listarActividad();
                gvActividades.DataSource = actividades;
                gvActividades.DataBind();
            }
            catch (System.Exception ex)
            {
                // Log para desarrollador
                System.Diagnostics.Debug.WriteLine("[Actividades] Error al listar: " + ex.Message);
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
            return activo ? "Activa" : "Inactiva";
        }

        protected void gvActividades_RowDataBound(object sender, GridViewRowEventArgs e)
        {
            // Aquí puedes personalizar el contenido si lo deseas.
        }

        protected void ClickBotonDescarga(object sender, EventArgs e)
        {
            byte[] archivo = actividadWSClient.reporteClientes();
            Response.Clear();
            Response.ContentType = "application/pdf"; // Cambia esto si es Excel u otro tipo
            Response.AddHeader("Content-Disposition", "attachment; filename=reporteClientes.pdf");
            Response.BinaryWrite(archivo);
            Response.End();
        }

        
    }
}
