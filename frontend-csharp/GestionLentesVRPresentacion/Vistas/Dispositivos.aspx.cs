using System;
using System.Linq;
using System.Web.UI;
using System.Web.UI.WebControls;
using FrontVR.GestionlentesvrWS;

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
                // Validar que se haya seleccionado un estado
                if (string.IsNullOrWhiteSpace(ddlEstado.SelectedValue))
                {
                    lblError.Text = "Debe seleccionar el estado de conexión del dispositivo.";
                    lblError.Visible = true;
                    return;
                }

                grupo grupo = new grupo { id = 1 };

                estadoConexion estado = (estadoConexion)Enum.Parse(typeof(estadoConexion), ddlEstado.SelectedValue);

                dispositivo d = new dispositivo
                {
                    id = string.IsNullOrWhiteSpace(hfIdDispositivo.Value) ? 0 : int.Parse(hfIdDispositivo.Value),
                    nombre = txtNombre.Text,
                    modelo = txtModelo.Text,
                    numeroSerie = txtSerie.Text,
                    fechaRegistro = DateTime.Parse(txtFecha.Text),
                    ubicacion = txtUbicacion.Text,
                    ultimaConexion = DateTime.Now,
                    ultimaConexionSpecified = true,
                    estado = estado,
                    grupo = grupo,
                    activo = 'S' // Suponiendo que todos los dispositivos están activos al guardar
                };

                if (d.id == 0)
                    servicio.registrarDispositivo(d);
                else
                    servicio.actualizarDispositivo(d);

                LimpiarFormulario();

                ScriptManager.RegisterStartupScript(
                    this, GetType(), "CerrarModal",
                    "$('#modalDispositivo').modal('hide');", true);

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
            int id = Convert.ToInt32(e.CommandArgument);

            if (e.CommandName == "Eliminar")
            {
                try
                {
                    servicio.eliminarDispositivo(id);
                }
                catch (System.Exception ex)
                {
                    System.Diagnostics.Debug.WriteLine($"[Error al eliminar] {ex.Message}");
                }
                CargarDispositivos();
            }
            else if (e.CommandName == "EditarDispositivo")
            {
                var d = servicio.obtenerDispositivo(id);

                hfIdDispositivo.Value = d.id.ToString();
                txtNombre.Text = d.nombre;
                txtModelo.Text = d.modelo;
                txtSerie.Text = d.numeroSerie;
                txtFecha.Text = d.fechaRegistro.ToString("yyyy-MM-dd");
                txtUbicacion.Text = d.ubicacion;
                ddlEstado.SelectedValue = d.estado.ToString();

                ScriptManager.RegisterStartupScript(this, GetType(), "AbrirModal", @"
                    document.getElementById('modalDispositivoLabel').textContent = 'Editar dispositivo';
                    var modal = new bootstrap.Modal(document.getElementById('modalDispositivo'));
                    modal.show();", true);
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
            ddlEstado.SelectedIndex = 0;
            lblError.Text = "";
            lblError.Visible = false;
        }
    }
}
