using System;
using System.Web.UI;
using System.Web.UI.WebControls;
using FrontVR.ServiceReference1; 

namespace FrontVR.Vistas
{
    public partial class Configuracion : Page
    {
         UsuarioWSClient servicio = new UsuarioWSClient(); // Descomenta cuando esté disponible el WS

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
            gvUsuarios.DataSource = servicio.listarUsuario();
            gvUsuarios.DataBind();

           // Temporal (para vista)
        }

        private void CargarRoles()
        {
            ddlRol.DataSource = servicio.listarUsuario();
            ddlRol.DataTextField = "nombre";
            ddlRol.DataValueField = "rolId";
            ddlRol.DataBind();

            // Simulación temporal
            ddlRol.Items.Clear();
            ddlRol.Items.Add(new ListItem("Administrador", "1"));
            ddlRol.Items.Add(new ListItem("Usuario", "2"));
        }

        protected void btnGuardar_Click(object sender, EventArgs e)
        {
            try
            {
                usuario u = new usuario
                {
                   id = string.IsNullOrEmpty(hfUsuarioId.Value) ? 0 : int.Parse(hfUsuarioId.Value),
                   nombre = txtNombres.Text,
                   correo = txtCorreo.Text,
                   rol = new rol { id = int.Parse(ddlRol.SelectedValue) },
                   activo = 'S'
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
