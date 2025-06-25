using FrontVR.GestionlentesvrWS;
using System;
using System.Linq;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace FrontVR.Vistas
{
    public partial class Configuraciones : Page
    {
        ConfiguracionWSClient servicio = new ConfiguracionWSClient();

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
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
            ScriptManager.RegisterStartupScript(this, GetType(), "abrirModal", "setTimeout(function() { new bootstrap.Modal(document.getElementById('modalConfiguracion')).show(); }, 300);", true);
        }

        protected void btnGuardar_Click(object sender, EventArgs e)
        {
            try
            {
                if (string.IsNullOrEmpty(ddlTipo.SelectedValue))
                {
                    lblError.Text = "Debe seleccionar un tipo de configuración.";
                    lblError.Visible = true;
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

                if (c.id == 0)
                    servicio.registrarConfiguracion(c);
                else
                    servicio.actualizarConfiguracion(c);

                LimpiarFormulario();
                CargarConfiguraciones();

                ScriptManager.RegisterStartupScript(this, GetType(), "cerrarModal", "$('#modalConfiguracion').modal('hide');", true);
            }
            catch (System.Exception ex)
            {
                lblError.Text = "Error al guardar: " + ex.Message;
                lblError.Visible = true;
            }
        }

        protected void gvConfiguraciones_RowCommand(object sender, GridViewCommandEventArgs e)
        {
            int id = Convert.ToInt32(e.CommandArgument);

            if (e.CommandName == "EditarConfiguracion")
            {
                var c = servicio.obtenerConfiguracion(id);
                hfConfiguracionId.Value = c.id.ToString();
                txtNombre.Text = c.nombre;
                txtDescripcion.Text = c.descripcion;
                ddlTipo.SelectedValue = c.tipo.ToString();
                txtValor.Text = c.valor;

                ScriptManager.RegisterStartupScript(this, GetType(), "abrirModal", "setTimeout(function() { new bootstrap.Modal(document.getElementById('modalConfiguracion')).show(); }, 300);", true);
                 CargarConfiguraciones();
            }
            else if (e.CommandName == "EliminarConfiguracion")
            {
                servicio.eliminarConfiguracion(id);
                CargarConfiguraciones();
            }
        }

        private void LimpiarFormulario()
        {
            hfConfiguracionId.Value = "";
            txtNombre.Text = "";
            txtDescripcion.Text = "";
            ddlTipo.SelectedIndex = 0;
            txtValor.Text = "";
            lblError.Visible = false;
        }
    }
}