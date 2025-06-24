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
<<<<<<< HEAD
            //LoginStatus1.Visible = Session["usuario"] != null;
=======
            if (Session["usuario"] == null)
                Response.Redirect("~/Login.aspx");

            string page = System.IO.Path.GetFileName(Request.Path).ToLower();

            // Remueve clase activa de todos (si quieres más robusto)
            //lnkDispositivos.Attributes["class"] = "nav-link";
            //lnkAplicaciones.Attributes["class"] = "nav-link";
            //lnkConfiguracion.Attributes["class"] = "nav-link";
            //lnkActividades.Attributes["class"] = "nav-link";

            // Asigna activa según la página
            //switch (page)
            //{
            //    //case "dispositivos.aspx":
            //        //lnkDispositivos.Attributes["class"] = "nav-link active";
            //        //break;
            //    case "aplicaciones.aspx":
            //        lnkAplicaciones.Attributes["class"] = "nav-link active";
            //        break;
            //    //case "configuracion.aspx":
            //        //lnkConfiguracion.Attributes["class"] = "nav-link active";
            //        //break;
            //    case "actividades.aspx":
            //        lnkActividades.Attributes["class"] = "nav-link active";
            //        break;
            //}
>>>>>>> 72e72ce (Ignorar archivos temporales de Visual Studio y build)
        }

        protected void btnLogout_Click(object sender, EventArgs e)
        {
            Session.Abandon();
            Response.Redirect("~/Login.aspx");
        }
    }
}