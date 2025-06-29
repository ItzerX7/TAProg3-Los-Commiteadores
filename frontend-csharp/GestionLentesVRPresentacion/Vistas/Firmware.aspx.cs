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
            //int id = Convert.ToInt32(e.CommandArgument);

            //if (e.CommandName == "EditarConfiguracion")
            //{
            //    var c = servicio.obtenerConfiguracion(id);
            //    hfConfiguracionId.Value = c.id.ToString();
            //    txtNombre.Text = c.nombre;
            //    txtDescripcion.Text = c.descripcion;
            //    ddlTipo.SelectedValue = c.tipo.ToString();
            //    txtValor.Text = c.valor;

            //    ScriptManager.RegisterStartupScript(this, GetType(), "abrirModal", "setTimeout(function() { new bootstrap.Modal(document.getElementById('modalConfiguracion')).show(); }, 300);", true);
            //    CargarConfiguraciones();
            //}
            //else if (e.CommandName == "EliminarConfiguracion")
            //{
            //    servicio.eliminarConfiguracion(id);
            //    CargarConfiguraciones();
            //}
        }
    }
}