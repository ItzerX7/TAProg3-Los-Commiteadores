using System;
using System.Web.UI;
using System.Web.UI.WebControls;
// using FrontVR.UsuarioWS; // Descomenta cuando agregues la referencia

namespace FrontVR.Vistas
{
    public partial class Configuracion : Page
    {
        // UsuarioWSClient servicio = new UsuarioWSClient(); // Descomenta cuando esté disponible el WS

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
            // gvUsuarios.DataSource = servicio.listarUsuarios();
            // gvUsuarios.DataBind();

            // Temporal (para vista)
            gvUsuarios.DataSource = null;
            gvUsuarios.DataBind();
        }

        private void CargarRoles()
        {
            // ddlRol.DataSource = servicio.listarRoles();
            // ddlRol.DataTextField = "nombre";
            // ddlRol.DataValueField = "rolId";
            // ddlRol.DataBind();

            // Simulación temporal
            ddlRol.Items.Clear();
            ddlRol.Items.Add(new ListItem("Administrador", "1"));
            ddlRol.Items.Add(new ListItem("Usuario", "2"));
        }

        protected void btnGuardar_Click(object sender, EventArgs e)
        {
            try
            {
                // usuario u = new usuario
                // {
                //     usuarioId = string.IsNullOrEmpty(hfUsuarioId.Value) ? 0 : int.Parse(hfUsuarioId.Value),
                //     nombres = txtNombres.Text,
                //     correo = txtCorreo.Text,
                //     rol = new rol { rolId = int.Parse(ddlRol.SelectedValue) },
                //     activo = true
                // };

                // if (u.usuarioId == 0)
                //     servicio.registrarUsuario(u);
                // else
                //     servicio.actualizarUsuario(u);

                LimpiarFormulario();
                CargarUsuarios();
            }
            catch (Exception ex)
            {
                lblError.Text = "Error al guardar: " + ex.Message;
                lblError.Visible = true;
            }
        }

        protected void gvUsuarios_RowCommand(object sender, GridViewCommandEventArgs e)
        {
            int index = Convert.ToInt32(e.CommandArgument);
            int id = int.Parse(gvUsuarios.DataKeys[index].Value.ToString());

            if (e.CommandName == "EditarUsuario")
            {
                // var u = servicio.obtenerUsuario(id);
                // hfUsuarioId.Value = u.usuarioId.ToString();
                // txtNombres.Text = u.nombres;
                // txtCorreo.Text = u.correo;
                // ddlRol.SelectedValue = u.rol.rolId.ToString();

                ScriptManager.RegisterStartupScript(this, GetType(), "abrirModal", "$('#modalUsuario').modal('show');", true);
            }
            else if (e.CommandName == "Eliminar")
            {
                // servicio.eliminarUsuario(id);
                CargarUsuarios();
            }
        }

        private void LimpiarFormulario()
        {
            hfUsuarioId.Value = "";
            txtNombres.Text = "";
            txtCorreo.Text = "";
            ddlRol.ClearSelection();
            lblError.Visible = false;
        }
    }
}
