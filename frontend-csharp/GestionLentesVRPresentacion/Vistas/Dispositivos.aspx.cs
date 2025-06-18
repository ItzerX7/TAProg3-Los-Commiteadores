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
            if (!IsPostBack || ddlEstado.Items.Count == 0)
                CargarEstados();

            if (!IsPostBack)
                CargarDispositivos();
        }

        private void CargarDispositivos()
        {
            var lista = servicio.listarDispositivo().ToList();
            gvDispositivos.DataSource = lista;
            gvDispositivos.DataBind();
        }

        private void CargarEstados()
        {
            ddlEstado.Items.Clear();
            ddlEstado.Items.Add(new ListItem("Seleccione...", ""));
            ddlEstado.Items.Add(new ListItem("CONECTADO", "CONECTADO"));
            ddlEstado.Items.Add(new ListItem("DESCONECTADO", "DESCONECTADO"));
            ddlEstado.Items.Add(new ListItem("EN_USO", "EN_USO"));
            ddlEstado.Items.Add(new ListItem("EN_MANTENIMIENTO", "EN_MANTENIMIENTO"));
        }

        protected void btnGuardar_Click(object sender, EventArgs e)
        {
            try
            {
                if (ddlEstado.SelectedValue == "")
                {
                    lblError.Text = "Debe seleccionar el estado de conexión.";
                    lblError.Visible = true;

                    ScriptManager.RegisterStartupScript(this, GetType(), "AbrirModal",
                        "setTimeout(function() { new bootstrap.Modal(document.getElementById('modalDispositivo')).show(); }, 300);", true);
                    return;
                }

                grupo grupo = new grupo { id = 1 };

                dispositivo d = new dispositivo
                {
                    id = string.IsNullOrWhiteSpace(hfIdDispositivo.Value) ? 0 : int.Parse(hfIdDispositivo.Value),
                    nombre = txtNombre.Text,
                    modelo = txtModelo.Text,
                    numeroSerie = txtSerie.Text,
                    // Si quieres usar una fecha manual, descomenta la línea de fechaRegistro
                    // fechaRegistro = DateTime.Parse(txtFecha.Text),
                    ubicacion = txtUbicacion.Text,
                    ultimaConexion = DateTime.Now,
                    ultimaConexionSpecified = true,
                    //nivelBateria = int.TryParse(txtNivelBateria.Text, out int nivel) ? nivel : 0,  // Nuevo campo
                    // Nivel de bateria seteao a 100
                    nivelBateria = 100, // Nuevo campo
                    estado = (estadoConexion)Enum.Parse(typeof(estadoConexion), ddlEstado.SelectedValue),
                    estadoSpecified = true,
                    grupo = grupo,
                    activo = 'S'
                };

                if (d.id == 0)
                    servicio.registrarDispositivo(d);
                else
                    servicio.actualizarDispositivo(d);

                LimpiarFormulario();

                ScriptManager.RegisterStartupScript(this, GetType(), "CerrarModal",
                    "$('#modalDispositivo').modal('hide');", true);

                CargarDispositivos();
            }
            catch (System.Exception ex)
            {
                string error = ex.Message;
                if (ex.InnerException != null)
                    error += " → " + ex.InnerException.Message;

                lblError.Text = "Error al guardar: " + error;
                lblError.Visible = true;
            }
        }

        private void LimpiarFormulario()
        {
            hfIdDispositivo.Value = "";
            txtNombre.Text = "";
            txtModelo.Text = "";
            txtSerie.Text = "";
            //txtFecha.Text = "";
            txtUbicacion.Text = "";
            //txtNivelBateria.Text = ""; // Limpiar nuevo campo
            ddlEstado.SelectedIndex = 0;
            lblError.Visible = false;
        }

        protected void gvDispositivos_RowCommand(object sender, GridViewCommandEventArgs e)
        {
            int id = Convert.ToInt32(e.CommandArgument);

            if (e.CommandName == "EditarDispositivo")
            {
                try
                {
                    dispositivo d = servicio.obtenerDispositivo(id);

                    hfIdDispositivo.Value = d.id.ToString();
                    txtNombre.Text = d.nombre;
                    txtModelo.Text = d.modelo;
                    txtSerie.Text = d.numeroSerie;
                    //txtFecha.Text = d.fechaRegistro.ToString("yyyy-MM-dd");
                    txtUbicacion.Text = d.ubicacion;
                    //txtNivelBateria.Text = d.nivelBateria.ToString(); // Cargar nuevo campo
                    ddlEstado.SelectedValue = d.estado.ToString();

                    ScriptManager.RegisterStartupScript(this, GetType(), "AbrirModal",
                        "setTimeout(function() { new bootstrap.Modal(document.getElementById('modalDispositivo')).show(); }, 300);", true);
                }
                catch (System.Exception ex)
                {
                    lblError.Text = "Error al cargar datos del dispositivo: " + ex.Message;
                    lblError.Visible = true;
                }
            }
            else if (e.CommandName == "Eliminar")
            {
                try
                {
                    servicio.eliminarDispositivo(id);
                    CargarDispositivos();
                }
                catch (System.Exception ex)
                {
                    lblError.Text = "Error al eliminar: " + ex.Message;
                    lblError.Visible = true;
                }
            }
        }
    }
}
