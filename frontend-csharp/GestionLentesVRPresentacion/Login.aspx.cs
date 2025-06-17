using System;

namespace FrontVR
{
    public partial class Login : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (Session["usuario"] != null)
                Response.Redirect("~/Vistas/Pantallainicio.aspx");
        }

        protected void btnLogin_Click(object sender, EventArgs e)
        {
            string usuario = txtUsuario.Text.Trim();
            string clave = txtPassword.Text;

            if (usuario == "admin" && clave == "123456")
            {
                Session["usuario"] = usuario;
                Response.Redirect("~/Vistas/Pantallainicio.aspx");
            }
            else
            {
                lblError.Text = "Credenciales incorrectas.";
                lblError.Visible = true;
            }
        }
    }
}
