using System;
using System.Linq;
using System.Web.UI;
using System.Web.UI.WebControls;
using FrontVR.ServiceReference1;
using Google.Protobuf.WellKnownTypes;

namespace FrontVR.Vistas
{
    public partial class Dispositivos : Page
    {
        private DispositivoWSClient servicio = new DispositivoWSClient();

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                CargarDispositivos();
            }
        }

        private void CargarDispositivos()
        {
            var lista = servicio.listarDispositivo().ToList();
            gvDispositivos.DataSource = lista;
            gvDispositivos.DataBind();
        }

        protected void btnGuardar_Click(object sender, EventArgs e)
        {
            try
            {
                grupo grupo = new grupo { id = 1 };
                dispositivo d = new dispositivo
                {
                    id = string.IsNullOrWhiteSpace(hfIdDispositivo.Value) ? 0 : int.Parse(hfIdDispositivo.Value),
                    nombre = txtNombre.Text,
                    modelo = txtModelo.Text,
                    numeroSerie = txtSerie.Text,
                    fechaRegistro = DateTime.Parse(txtFecha.Text),
                    ubicacion = txtUbicacion.Text,
                    ultimaConexion = DateTime.Now,
                    activo = 'S',
                    ultimaConexionSpecified = true,
                    grupo = grupo
                };

                if (d.id == 0)
                    servicio.registrarDispositivo(d);
                else
                    servicio.actualizarDispositivo(d);

                LimpiarFormulario();
                CargarDispositivos();
            }
            catch (System.Exception ex)
            {
                lblError.Text = "Error al guardar: " + ex.Message;
                lblError.Visible = true;
            }
        }

        protected void gvDispositivos_RowCommand(object sender, GridViewCommandEventArgs e)
        {
            int index = Convert.ToInt32(e.CommandArgument);
            int id = int.Parse(gvDispositivos.DataKeys[index].Value.ToString());

            if (e.CommandName == "EditarDispositivo")
            {
                var d = servicio.obtenerDispositivo(id);
                hfIdDispositivo.Value = d.id.ToString();
                txtNombre.Text = d.nombre;
                txtModelo.Text = d.modelo;
                txtSerie.Text = d.numeroSerie;
                txtFecha.Text = d.fechaRegistro.ToString("yyyy-MM-dd");
                txtUbicacion.Text = d.ubicacion;

                // Mostrar modal desde servidor
                ScriptManager.RegisterStartupScript(this, GetType(), "abrirModal", "$('#modalDispositivo').modal('show');", true);
            }
            else if (e.CommandName == "Eliminar")
            {
                servicio.eliminarDispositivo(id);
                CargarDispositivos();
            }
        }

        private void LimpiarFormulario()
        {
            hfIdDispositivo.Value = "";
            txtNombre.Text = "";
            txtModelo.Text = "";
            txtSerie.Text = "";
            txtFecha.Text = "";
            txtUbicacion.Text = "";
            lblError.Text = "";
            lblError.Visible = false;
        }
    }
}
