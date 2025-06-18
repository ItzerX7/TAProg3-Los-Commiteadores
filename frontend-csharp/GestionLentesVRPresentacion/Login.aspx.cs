using FrontVR.GestionlentesvrWS;
using System;
using System.Linq;

namespace FrontVR
{
    public partial class Login : System.Web.UI.Page
    {
        private UsuarioWSClient usuarioWSClient = new UsuarioWSClient();

        protected void Page_Load(object sender, EventArgs e)
        {
            if (Session["usuario"] != null)
                Response.Redirect("~/Vistas/Pantallainicio.aspx");
        }

        protected void btnLogin_Click(object sender, EventArgs e)
        {
            string usuarioInput = txtUsuario.Text.Trim();
            string claveInput = txtPassword.Text;

            try
            {
                // Obtiene todos los usuarios
                var usuarios = usuarioWSClient.listarUsuario();

                // Busca coincidencia
                var usuarioValido = usuarios.FirstOrDefault(u =>
                    u.correo.Equals(usuarioInput, StringComparison.OrdinalIgnoreCase) &&
                    u.contrasena == claveInput &&
                    u.activo.ToString().ToLower() == "s"
                );

                if (usuarioValido != null)
                {
                    Session["usuario"] = usuarioValido;
                    Response.Redirect("~/Vistas/Pantallainicio.aspx");
                }
                else
                {
                    lblError.Text = "Credenciales incorrectas o usuario inactivo.";
                    lblError.Visible = true;
                }
            }
            catch (System.Exception ex)
            {
                lblError.Text = "Error de conexión: " + ex.Message;
                lblError.Visible = true;
            }
        }
    }
}
