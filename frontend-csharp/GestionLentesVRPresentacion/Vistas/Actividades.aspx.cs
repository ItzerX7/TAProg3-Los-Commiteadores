using System;
using System.Linq;
using System.Web.UI.WebControls;
using FrontVR.GestionlentesvrWS;

namespace FrontVR.Vistas
{
    public partial class Actividades : System.Web.UI.Page
    {
        private readonly ActividadWSClient actividadWS = new ActividadWSClient();

        protected void Page_Load(object sender, EventArgs e)
        {

            if (Session["usuario"] == null)
            {
                Response.Redirect("~/Login.aspx");
                return;
            }

            if (!IsPostBack)
            {
                BindGrid();
            }
        }

        private void BindGrid()
        {
            try
            {
                var actividades = actividadWS.listarActividad()
                    .OrderByDescending(d => d.id)
                    .ToList();
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

        protected void ClickBotonDescargaRepTipoAct(object sender, EventArgs e)
        {
            byte[] archivo = actividadWS.reporteTipos();
            Response.Clear();
            Response.ContentType = "application/pdf"; 
            Response.AddHeader("Content-Disposition", "attachment; filename=reporteTiposActividades.pdf");
            Response.BinaryWrite(archivo);
            Response.End();
        }

        protected void ClickBotonDescargaRepAct(object sender, EventArgs e)
        {
            byte[] archivo = actividadWS.reporteActividades();
            Response.Clear();
            Response.ContentType = "application/pdf";
            Response.AddHeader("Content-Disposition", "attachment; filename=reporteActividades.pdf");
            Response.BinaryWrite(archivo);
            Response.End();
        }
    }
}
