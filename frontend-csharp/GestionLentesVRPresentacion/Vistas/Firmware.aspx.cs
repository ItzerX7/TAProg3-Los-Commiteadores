using FrontVR.GestionlentesvrWS;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services.Description;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace FrontVR.Vistas
{
    public partial class Firmware : System.Web.UI.Page
    {
        private FirmwareWSClient servicio = new FirmwareWSClient();
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                CargarFirmwares();
            }
        }

        private void CargarFirmwares()
        {
            var lista = servicio.listarFirmware();
            gvFirmware.DataSource = lista;
            gvFirmware.DataKeyNames = new[] { "id" };
            gvFirmware.DataBind();
        }

        protected void gvFirmware_RowCommand(object sender, GridViewCommandEventArgs e)
        {
            int id = Convert.ToInt32(e.CommandArgument);

            if (e.CommandName == "EditarFirmware")
            {
                var c = servicio.obtenerFirmware(id);
                hfFirmwareId.Value = c.id.ToString();
                txtNombre.Text = c.nombre;
                txtDescripcion.Text = c.descripcion;
                txtVersion.Text = c.version.ToString();

                ScriptManager.RegisterStartupScript(this, GetType(), "abrirModal", "setTimeout(function() { new bootstrap.Modal(document.getElementById('modalFirmware')).show(); }, 300);", true);
                CargarFirmwares();
            }
            else if (e.CommandName == "EliminarFirmware")
            {
                servicio.eliminarFirmware(id);
                CargarFirmwares();
            }
        }
        protected void btnGuardar_Click(object sender, EventArgs e)
        {
            try
            {

                firmware c = new firmware
                {
                    id = string.IsNullOrEmpty(hfFirmwareId.Value) ? 0 : int.Parse(hfFirmwareId.Value),
                    nombre = txtNombre.Text.Trim(),
                    descripcion = txtDescripcion.Text.Trim(),
                    version = txtVersion.Text.Trim(),
                    activo = 1
                };

                if (c.id == 0)
                    servicio.registrarFirmware(c);
                else
                    servicio.actualizarFirmware(c);

                LimpiarFormulario();
                CargarFirmwares();

                ScriptManager.RegisterStartupScript(this, GetType(), "cerrarModal", "$('#modalFirmware').modal('hide');", true);
            }
            catch (System.Exception ex)
            {
                lblError.Text = "Error al guardar: " + ex.Message;
                lblError.Visible = true;
            }
        }

        protected void btnAgregar_Click(object sender, EventArgs e)
        {
            LimpiarFormulario();
            ScriptManager.RegisterStartupScript(this, GetType(), "abrirModal", "setTimeout(function() { new bootstrap.Modal(document.getElementById('modalFirmware')).show(); }, 300);", true);
        }
        private void LimpiarFormulario()
        {

            lblError.Visible = false;
            txtNombre.Text = "";
            txtDescripcion.Text = "";
            txtVersion.Text = "";
        }
    
    
    }
}