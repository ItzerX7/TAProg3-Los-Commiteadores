using BCrypt.Net;
using FrontVR.GestionlentesvrWS;
using System;

namespace FrontVR
{
    public partial class Login : System.Web.UI.Page
    {
        private UsuarioWSClient usuarioWSClient = new UsuarioWSClient();

        protected void Page_Load(object sender, EventArgs e)
        {
            if (Session["usuario"] != null)
            {
                var usuarioSesion = (usuario)Session["usuario"];
                RedirigirSegunRol(usuarioSesion.rol.id);
            }
        }

        protected void btnLogin_Click(object sender, EventArgs e)
        {
            string usuarioInput = txtUsuario.Text.Trim();
            string claveInput = txtPassword.Text;

            try
            {
                var usuarioValido = usuarioWSClient.obtenerUsuarioCorreo(usuarioInput);

                // 🔐 Verifica con BCrypt si la contraseña ingresada es válida
                if (usuarioValido != null &&
                    BCrypt.Net.BCrypt.Verify(claveInput, usuarioValido.contrasena)) // contrasena es el hash
                {
                    Session["usuario"] = usuarioValido;
                    Session["RolId"] = usuarioValido.rol.id;

                    RedirigirSegunRol(usuarioValido.rol.id);
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

        private void RedirigirSegunRol(int rolId)
        {
            if (rolId == 1)
                Response.Redirect("~/Vistas/PantallaInicio.aspx");
            else if (rolId == 2)
                Response.Redirect("~/Vistas/PantallaInicio.aspx");
            else
                Response.Redirect("~/Login.aspx");
        }
    }
}
