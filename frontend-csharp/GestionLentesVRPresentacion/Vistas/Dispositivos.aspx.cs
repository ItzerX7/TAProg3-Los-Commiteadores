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
<<<<<<< HEAD
=======
        private GrupoWSClient grupoServicio = new GrupoWSClient();
>>>>>>> 72e72ce (Ignorar archivos temporales de Visual Studio y build)

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
<<<<<<< HEAD
=======
                CargarEstados();
                CargarGrupos();
>>>>>>> 72e72ce (Ignorar archivos temporales de Visual Studio y build)
                CargarDispositivos();
            }
        }

        private void CargarDispositivos()
        {
            var lista = servicio.listarDispositivo().ToList();
            gvDispositivos.DataSource = lista;
            gvDispositivos.DataBind();
        }

<<<<<<< HEAD
=======
        private void CargarEstados()
        {
            ddlEstado.Items.Clear();
            ddlEstado.Items.Add(new ListItem("Seleccione...", ""));
            ddlEstado.Items.Add(new ListItem("CONECTADO", "CONECTADO"));
            ddlEstado.Items.Add(new ListItem("DESCONECTADO", "DESCONECTADO"));
            ddlEstado.Items.Add(new ListItem("EN_USO", "EN_USO"));
            ddlEstado.Items.Add(new ListItem("EN_MANTENIMIENTO", "EN_MANTENIMIENTO"));
        }

        private void CargarGrupos()
        {
            ddlGrupo.Items.Clear();
            ddlGrupo.Items.Add(new ListItem("Seleccione...", ""));
            var grupos = grupoServicio.listarGrupo();
            foreach (var g in grupos)
            {
                ddlGrupo.Items.Add(new ListItem(g.nombre, g.id.ToString()));
            }
        }

>>>>>>> 72e72ce (Ignorar archivos temporales de Visual Studio y build)
        protected void btnGuardar_Click(object sender, EventArgs e)
        {
            try
            {
<<<<<<< HEAD
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
=======
                if (ddlEstado.SelectedValue == "" || ddlGrupo.SelectedValue == "")
                {
                    lblError.Text = "Debe seleccionar el estado de conexión y el grupo.";
                    lblError.Visible = true;

                    ScriptManager.RegisterStartupScript(this, GetType(), "AbrirModal",
                        "setTimeout(function() { new bootstrap.Modal(document.getElementById('modalDispositivo')).show(); }, 300);", true);
                    return;
                }

                int idGrupo = int.Parse(ddlGrupo.SelectedValue.Trim());
                grupo grupoAsignado = new grupo { id = idGrupo };

                bool esNuevo = string.IsNullOrWhiteSpace(hfIdDispositivo.Value) || hfIdDispositivo.Value == "0";

                dispositivo d = new dispositivo
                {
                    id = esNuevo ? 0 : int.Parse(hfIdDispositivo.Value),
                    nombre = txtNombre.Text,
                    modelo = txtModelo.Text,
                    numeroSerie = txtSerie.Text,
                    ubicacion = txtUbicacion.Text,
                    ultimaConexion = DateTime.Now,
                    ultimaConexionSpecified = true,
                    nivelBateria = 100,
                    estado = (estadoConexion)Enum.Parse(typeof(estadoConexion), ddlEstado.SelectedValue),
                    estadoSpecified = true,
                    grupo = grupoAsignado,
                    activo = 'S'
                };

                if (esNuevo)
>>>>>>> 72e72ce (Ignorar archivos temporales de Visual Studio y build)
                    servicio.registrarDispositivo(d);
                else
                    servicio.actualizarDispositivo(d);

                LimpiarFormulario();

<<<<<<< HEAD
                ScriptManager.RegisterStartupScript(
                    this, GetType(), "CerrarModal",
                    "$('#modalDispositivo').modal('hide');", true);

                CargarDispositivos();
            }
            catch (System.Exception ex)
            {
                lblError.Text = "Error al guardar: " + ex.Message;
=======
                string mensaje = esNuevo ? "Se insertó el dispositivo correctamente" : "Se editó el dispositivo correctamente";
                string script = $@"
                    Sys.Application.add_load(function() {{
                        Swal.fire({{
                            icon: 'success',
                            title: '{mensaje}',
                            confirmButtonText: 'OK'
                        }}).then(() => {{
                            window.location.href = window.location.href;
                        }});
                    }});";

                ScriptManager.RegisterStartupScript(this, GetType(), "SwalSuccess", script, true);
            }
            catch (System.Exception ex)
            {
                string error = ex.Message;
                if (ex.InnerException != null)
                    error += " → " + ex.InnerException.Message;

                lblError.Text = "Error al guardar: " + error;
>>>>>>> 72e72ce (Ignorar archivos temporales de Visual Studio y build)
                lblError.Visible = true;
            }
        }

<<<<<<< HEAD
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

=======
>>>>>>> 72e72ce (Ignorar archivos temporales de Visual Studio y build)
        private void LimpiarFormulario()
        {
            hfIdDispositivo.Value = "";
            txtNombre.Text = "";
            txtModelo.Text = "";
            txtSerie.Text = "";
<<<<<<< HEAD
            txtFecha.Text = "";
            txtUbicacion.Text = "";
            ddlEstado.SelectedIndex = 0;
            lblError.Text = "";
            lblError.Visible = false;
        }
=======
            txtUbicacion.Text = "";
            ddlEstado.SelectedIndex = 0;
            ddlGrupo.SelectedIndex = 0;
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

                    CargarGrupos();

                    hfIdDispositivo.Value = d.id.ToString();
                    txtNombre.Text = d.nombre;
                    txtModelo.Text = d.modelo;
                    txtSerie.Text = d.numeroSerie;
                    txtUbicacion.Text = d.ubicacion;
                    ddlEstado.SelectedValue = d.estado.ToString();

                    var grupoItem = ddlGrupo.Items.FindByValue(d.grupo.id.ToString());
                    if (grupoItem != null)
                        ddlGrupo.SelectedValue = grupoItem.Value;
                    else
                        lblError.Text = "Grupo asociado al dispositivo no encontrado en la lista.";

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
>>>>>>> 72e72ce (Ignorar archivos temporales de Visual Studio y build)
    }
}
