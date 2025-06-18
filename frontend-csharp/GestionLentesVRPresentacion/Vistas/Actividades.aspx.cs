using System;
using System.Globalization;
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
            if (!IsPostBack)
                BindGrid();
        }

        private void BindGrid()
        {
            var actividades = actividadWSClient.listarActividad();
            gvActividades.DataSource = actividades;
            gvActividades.DataBind();
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

        protected void gvActividades_RowDataBound(object sender, System.Web.UI.WebControls.GridViewRowEventArgs e)
        {
            // Si necesitas formatear celdas adicionales, ponlo aquí.
        }
    }
}
