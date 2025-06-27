using FrontVR.GestionlentesvrWS;
using System;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace FrontVR.Vistas
{
    public partial class Administracion : Page
    {
        UsuarioWSClient servicio = new UsuarioWSClient();
        RolWSClient rolServicio = new RolWSClient();

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                CargarUsuarios();
                CargarRoles();
            }
        }

        private void CargarUsuarios()
        {
            var lista = servicio.listarUsuario();
            gvUsuarios.DataSource = lista;
            gvUsuarios.DataBind();
        }

        private void CargarRoles()
        {
            ddlRol.DataSource = rolServicio.listarRol();
            ddlRol.DataTextField = "nombre";
            ddlRol.DataValueField = "id";
            ddlRol.DataBind();
        }

        protected void btnGuardar_Click(object sender, EventArgs e)
        {
            try
            {
                int idUsuario = string.IsNullOrEmpty(hfUsuarioId.Value) ? 0 : int.Parse(hfUsuarioId.Value);
                bool esNuevo = idUsuario == 0;

                usuario u = new usuario
                {
                    id = idUsuario,
                    nombre = txtNombres.Text.Trim(),
                    apellido = txtApellido.Text.Trim(),
                    correo = txtCorreo.Text.Trim(),
                    rol = new rol { id = int.Parse(ddlRol.SelectedValue) },
                    contrasena = txtContrasena.Text.Trim(),
                    activo = 's'
                };

                if (!esNuevo && string.IsNullOrWhiteSpace(u.contrasena))
                {
                    var usuarioActual = servicio.obtenerUsuario(u.id);
                    u.contrasena = usuarioActual.contrasena;
                }

                if (esNuevo)
                    servicio.registrarUsuario(u);
                else
                    servicio.actualizarUsuario(u);

                LimpiarFormulario();
                CargarUsuarios();

                ScriptManager.RegisterStartupScript(this, GetType(), "CerrarModal",
                    "$('#modalUsuario').modal('hide');", true);

                string mensaje = esNuevo ? "Usuario registrado correctamente" : "Usuario editado correctamente";
                string script = $@"
                    Swal.fire({{
                        icon: 'success',
                        title: '{mensaje}',
                        confirmButtonText: 'OK'
                    }}).then(() => {{
                        window.location.href = window.location.href;
                    }});";
                ScriptManager.RegisterStartupScript(this, GetType(), "SwalSuccess", script, true);
            }
            catch (System.Exception ex)
            {
                lblError.Text = "Error al guardar: " + ex.Message;
                lblError.Visible = true;
            }
        }

        protected void gvUsuarios_RowCommand(object sender, GridViewCommandEventArgs e)
        {
            int id = Convert.ToInt32(e.CommandArgument);

            if (e.CommandName == "EditarUsuario")
            {
                var u = servicio.obtenerUsuario(id);

                hfUsuarioId.Value = u.id.ToString();
                txtNombres.Text = u.nombre;
                txtApellido.Text = u.apellido;
                txtCorreo.Text = u.correo;
                txtContrasena.Text = ""; // se deja en blanco por seguridad
                ddlRol.SelectedValue = u.rol.id.ToString();

                lblContrasenaInfo.Visible = true;

                ScriptManager.RegisterStartupScript(this, GetType(), "AbrirModalEditar", @"
                    mostrarMensajeContrasenaInfo(true);
                    document.getElementById('modalUsuarioLabel').textContent = 'Editar usuario';
                    var modal = new bootstrap.Modal(document.getElementById('modalUsuario'));
                    modal.show();", true);
            }
            else if (e.CommandName == "Eliminar")
            {
                servicio.eliminarUsuario(id);
                CargarUsuarios();

                string script = @"
                    Swal.fire({
                        icon: 'success',
                        title: 'Usuario eliminado correctamente',
                        confirmButtonText: 'OK'
                    });";
                ScriptManager.RegisterStartupScript(this, GetType(), "SwalDelete", script, true);
            }
        }

        private void LimpiarFormulario()
        {
            hfUsuarioId.Value = "";
            txtNombres.Text = "";
            txtApellido.Text = "";
            txtCorreo.Text = "";
            txtContrasena.Text = "";
            ddlRol.ClearSelection();
            lblError.Text = "";
            lblError.Visible = false;
            lblContrasenaInfo.Visible = false;
        }
    }
}
