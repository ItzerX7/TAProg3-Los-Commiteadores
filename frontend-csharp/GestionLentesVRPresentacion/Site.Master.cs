using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace FrontVR
{
    public partial class SiteMaster : MasterPage
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (Session["usuario"] == null)
                Response.Redirect("~/Login.aspx");

            string page = System.IO.Path.GetFileName(Request.Path).ToLower();

            // Remueve clase activa de todos (si quieres más robusto)
            lnkDispositivos.Attributes["class"] = "nav-link";
            lnkAplicaciones.Attributes["class"] = "nav-link";
            lnkPantallaInicio.Attributes["class"] = "nav-link";
            lnkConfiguracion.Attributes["class"] = "nav-link";
            lnkActividades.Attributes["class"] = "nav-link";
            lnkMetrica.Attributes["class"] = "nav-link";
            lnkAdministracion.Attributes["class"] = "nav-link";
            lnkGrupos.Attributes["class"] = "nav-link";
            //Asigna activa según la página
            switch (page)
            {
                case "dispositivos":
                    lnkDispositivos.Attributes["class"] = "nav-link active";
                    break;
                case "administracion":
                    lnkAdministracion.Attributes["class"] = "nav-link active";
                    break;
                case "grupos":
                    lnkGrupos.Attributes["class"] = "nav-link active";
                    break;
                case "aplicaciones":
                    lnkAplicaciones.Attributes["class"] = "nav-link active";
                    break;
                case "metricas":
                    lnkMetrica.Attributes["class"] = "nav-link active";
                    break;
                case "pantallainicio":
                    lnkPantallaInicio.Attributes["class"] = "nav-link active";
                    break;
                case "configuracion":
                    lnkConfiguracion.Attributes["class"] = "nav-link active";
                    break;
                case "actividades":
                    lnkActividades.Attributes["class"] = "nav-link active";
                    break;
            }
        }

        protected void btnLogout_Click(object sender, EventArgs e)
        {
            Session.Abandon();
            Response.Redirect("~/Login.aspx");
        }
    }
}