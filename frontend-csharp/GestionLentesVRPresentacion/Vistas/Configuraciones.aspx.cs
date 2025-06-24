<<<<<<< HEAD
﻿using System;
using System.Web.UI;
using System.Web.UI.WebControls;
using FrontVR.GestionlentesvrWS; 

namespace FrontVR.Vistas
{
    public partial class Configuracion : Page
    {
         UsuarioWSClient servicio = new UsuarioWSClient(); // Descomenta cuando esté disponible el WS
=======
﻿using FrontVR.GestionlentesvrWS;
using System;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace FrontVR.Vistas
{
    public partial class Configuraciones : Page
    {
        ConfiguracionWSClient servicio = new ConfiguracionWSClient();
>>>>>>> 72e72ce (Ignorar archivos temporales de Visual Studio y build)

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
<<<<<<< HEAD
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
=======
                CargarConfiguraciones();
                CargarTipos();
            }
        }

        private void CargarConfiguraciones()
        {
            var lista = servicio.listarConfiguracion();
            gvConfiguraciones.DataSource = lista;
            gvConfiguraciones.DataKeyNames = new[] { "id" };
            gvConfiguraciones.DataBind();
        }

        private void CargarTipos()
        {
            ddlTipo.Items.Clear();
            ddlTipo.Items.Add(new ListItem("Seleccione...", ""));

            foreach (var tipo in Enum.GetNames(typeof(tipoConfiguracion)))
            {
                ddlTipo.Items.Add(new ListItem(tipo, tipo));
            }
        }

        protected void btnAgregar_Click(object sender, EventArgs e)
        {
            LimpiarFormulario();
            ScriptManager.RegisterStartupScript(this, GetType(), "abrirModalScript",
                "var modal = new bootstrap.Modal(document.getElementById('modalConfiguracion')); modal.show();", true);
>>>>>>> 72e72ce (Ignorar archivos temporales de Visual Studio y build)
        }

        protected void btnGuardar_Click(object sender, EventArgs e)
        {
            try
            {
<<<<<<< HEAD
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
=======
                if (string.IsNullOrEmpty(ddlTipo.SelectedValue))
                {
                    lblError.Text = "Debe seleccionar un tipo de configuración.";
                    lblError.Visible = true;

                    ScriptManager.RegisterStartupScript(this, GetType(), "reabrirModal",
                        "var modal = new bootstrap.Modal(document.getElementById('modalConfiguracion')); modal.show();", true);
                    return;
                }

                configuracion c = new configuracion
                {
                    id = string.IsNullOrEmpty(hfConfiguracionId.Value) ? 0 : int.Parse(hfConfiguracionId.Value),
                    nombre = txtNombre.Text.Trim(),
                    descripcion = txtDescripcion.Text.Trim(),
                    tipo = (tipoConfiguracion)Enum.Parse(typeof(tipoConfiguracion), ddlTipo.SelectedValue),
                    tipoSpecified = true,
                    valor = txtValor.Text.Trim(),
                    fechaCreacion = DateTime.Now,
                    fechaCreacionSpecified = true,
                    activo = 1
                };

                bool esNuevo = c.id == 0;

                if (esNuevo)
                    servicio.registrarConfiguracion(c);
                else
                    servicio.actualizarConfiguracion(c);

                LimpiarFormulario();
                CargarConfiguraciones();

                string mensaje = esNuevo ? "Se registró la configuración correctamente" : "Se actualizó la configuración correctamente";

                string script = $@"
                    Swal.fire({{
                        icon: 'success',
                        title: '{mensaje}',
                        confirmButtonText: 'OK'
                    }}).then(() => {{
                        window.location.href = window.location.href;
                    }});";

                ScriptManager.RegisterStartupScript(this, GetType(), "SwalConfirm", script, true);
>>>>>>> 72e72ce (Ignorar archivos temporales de Visual Studio y build)
            }
            catch (System.Exception ex)
            {
                lblError.Text = "Error al guardar: " + ex.Message;
                lblError.Visible = true;
            }
        }

<<<<<<< HEAD
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
=======
        protected void gvConfiguraciones_RowCommand(object sender, GridViewCommandEventArgs e)
        {
            int index = Convert.ToInt32(e.CommandArgument);
            int id = int.Parse(gvConfiguraciones.DataKeys[index].Value.ToString());

            if (e.CommandName == "EditarConfiguracion")
            {
                try
                {
                    var c = servicio.obtenerConfiguracion(id);
                    hfConfiguracionId.Value = c.id.ToString();
                    txtNombre.Text = c.nombre;
                    txtDescripcion.Text = c.descripcion;
                    ddlTipo.SelectedValue = c.tipo.ToString();
                    txtValor.Text = c.valor;

                    ScriptManager.RegisterStartupScript(this, GetType(), "abrirModalEditar",
                        "var modal = new bootstrap.Modal(document.getElementById('modalConfiguracion')); modal.show();", true);
                }
                catch (System.Exception ex)
                {
                    lblError.Text = "Error al cargar configuración: " + ex.Message;
                    lblError.Visible = true;
                }
            }
            else if (e.CommandName == "Eliminar")
            {
                try
                {
                    servicio.eliminarConfiguracion(id);
                    CargarConfiguraciones();

                    ScriptManager.RegisterStartupScript(this, GetType(), "SwalEliminado", @"
                        Swal.fire({
                            icon: 'success',
                            title: 'Configuración eliminada correctamente',
                            confirmButtonText: 'OK'
                        });", true);
                }
                catch (System.Exception ex)
                {
                    lblError.Text = "Error al eliminar: " + ex.Message;
                    lblError.Visible = true;
                }
>>>>>>> 72e72ce (Ignorar archivos temporales de Visual Studio y build)
            }
        }

        private void LimpiarFormulario()
        {
<<<<<<< HEAD
            hfUsuarioId.Value = "";
            txtNombres.Text = "";
            txtCorreo.Text = "";
            ddlRol.ClearSelection();
=======
            hfConfiguracionId.Value = "";
            txtNombre.Text = "";
            txtDescripcion.Text = "";
            ddlTipo.SelectedIndex = 0;
            txtValor.Text = "";
>>>>>>> 72e72ce (Ignorar archivos temporales de Visual Studio y build)
            lblError.Visible = false;
        }
    }
}
