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
                var usuarios = usuarioWSClient.listarUsuario();

                // Verifica con la estructura real
                var usuarioValido = usuarios.FirstOrDefault(u =>
                    u.correo.Equals(usuarioInput, StringComparison.OrdinalIgnoreCase) &&
                    u.contrasena == claveInput //&&
                    //u.activo == 1 // ushort: 1 = activo, 0 = inactivo
                );

                if (usuarioValido != null)
                {
                    Session["usuario"] = usuarioValido;
                    Session["RolId"] = usuarioValido.rol.id; // la propiedad anidada del rol

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
                Response.Redirect("~/Vistas/PantallaInicio.aspx"); // Admin ve todo
            else if (rolId == 2)
                Response.Redirect("~/Vistas/PantallaInicio.aspx"); // O PantallaInicioTecnico si usas eso
            else
                Response.Redirect("~/Login.aspx"); // Rol inválido
        }
    }
}
