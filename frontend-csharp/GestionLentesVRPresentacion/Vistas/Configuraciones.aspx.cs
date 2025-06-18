using FrontVR.GestionlentesvrWS;
using System;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace FrontVR.Vistas
{
    public partial class Configuracion : Page
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
            gvUsuarios.DataKeyNames = new[] { "id" };
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
                usuario u = new usuario
                {
                    id = string.IsNullOrEmpty(hfUsuarioId.Value) ? 0 : int.Parse(hfUsuarioId.Value),
                    nombre = txtNombres.Text.Trim(),
                    correo = txtCorreo.Text.Trim(),
                    rol = new rol { id = int.Parse(ddlRol.SelectedValue) },
                    activo = 's'
                };

                if (u.id == 0)
                    servicio.registrarUsuario(u);
                else
                    servicio.actualizarUsuario(u);

                LimpiarFormulario();
                CargarUsuarios();
            }
            catch (System.Exception ex)
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
                var u = servicio.obtenerUsuario(id);
                hfUsuarioId.Value = u.id.ToString();
                txtNombres.Text = u.nombre;
                txtCorreo.Text = u.correo;
                ddlRol.SelectedValue = u.rol.id.ToString();

                ScriptManager.RegisterStartupScript(this, GetType(), "abrirModal", "$('#modalUsuario').modal('show');", true);
            }
            else if (e.CommandName == "Eliminar")
            {
                servicio.eliminarUsuario(id);
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