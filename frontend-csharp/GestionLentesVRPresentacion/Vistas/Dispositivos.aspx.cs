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
        private GrupoWSClient grupoServicio = new GrupoWSClient();

        protected void Page_Load(object sender, EventArgs e)
        {
            if (Session["usuario"] == null)
            {
                Response.Redirect("~/Login.aspx");
                return;
            }

            if (!IsPostBack)
            {
                var usuario = (usuario)Session["usuario"];
                bool esTecnico = usuario.rol.id == 2;

                gvDispositivos.Columns[5].Visible = !esTecnico; // Oculta columna Estado para técnicos

                CargarEstados();
                CargarGrupos();
                CargarDispositivos();
            }
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

        protected void btnGuardar_Click(object sender, EventArgs e)
        {
            try
            {
                if (Session["usuario"] == null)
                {
                    Response.Redirect("~/Login.aspx");
                    return;
                }

                var usuario = (usuario)Session["usuario"];
                bool esTecnico = usuario.rol.id == 2;

                if (ddlGrupo.SelectedValue == "" || (ddlEstado.SelectedValue == "" && !esTecnico))
                {
                    lblError.Text = "Debe seleccionar el grupo" + (!esTecnico ? " y el estado de conexión" : "") + ".";
                    lblError.Visible = true;
                    ScriptManager.RegisterStartupScript(this, GetType(), "AbrirModal",
                        "setTimeout(function() { new bootstrap.Modal(document.getElementById('modalDispositivo')).show(); }, 300);", true);
                    return;
                }

                bool esNuevo = string.IsNullOrWhiteSpace(hfIdDispositivo.Value) || hfIdDispositivo.Value == "0";
                int id = esNuevo ? 0 : int.Parse(hfIdDispositivo.Value);
                int idGrupo = int.Parse(ddlGrupo.SelectedValue);

                estadoConexion estadoFinal;

                if (esTecnico && !esNuevo)
                {
                    var actual = servicio.obtenerDispositivo(id);
                    estadoFinal = actual.estado; // evitar que el técnico lo cambie al editar
                }
                else
                {
                    estadoFinal = (estadoConexion)Enum.Parse(typeof(estadoConexion), ddlEstado.SelectedValue);
                }

                var dispositivo = new dispositivo
                {
                    id = id,
                    nombre = txtNombre.Text,
                    modelo = txtModelo.Text,
                    numeroSerie = txtSerie.Text,
                    fechaRegistro = DateTime.Parse(txtFecha.Text),
                    ubicacion = txtUbicacion.Text,
                    ultimaConexion = DateTime.Now,
                    ultimaConexionSpecified = true,
                    nivelBateria = 100,
                    estado = estadoFinal,
                    estadoSpecified = true,
                    grupo = new grupo { id = idGrupo },
                    activo = 'S'
                };

                if (esNuevo)
                    servicio.registrarDispositivo(dispositivo);
                else
                    servicio.actualizarDispositivo(dispositivo);

                LimpiarFormulario();

                ScriptManager.RegisterStartupScript(this, GetType(), "CerrarModal",
                    "$('#modalDispositivo').modal('hide');", true);

                string mensaje = esNuevo ? "Se insertó el dispositivo correctamente" : "Se editó el dispositivo correctamente";
                string script = $@"
                    Swal.fire({{
                        icon: 'success',
                        title: '{mensaje}',
                        confirmButtonText: 'OK'
                    }}).then(() => {{
                        window.location.href = window.location.href;
                    }});";
                ScriptManager.RegisterStartupScript(this, GetType(), "SwalSuccess", script, true);

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

        protected void gvDispositivos_RowCommand(object sender, GridViewCommandEventArgs e)
        {
            if (Session["usuario"] == null)
            {
                Response.Redirect("~/Login.aspx");
                return;
            }

            var usuario = (usuario)Session["usuario"];
            bool esTecnico = usuario.rol.id == 2;

            int id = Convert.ToInt32(e.CommandArgument);

            if (e.CommandName == "Eliminar")
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
            else if (e.CommandName == "EditarDispositivo")
            {
                try
                {
                    var d = servicio.obtenerDispositivo(id);
                    CargarGrupos();

                    hfIdDispositivo.Value = d.id.ToString();
                    txtNombre.Text = d.nombre;
                    txtModelo.Text = d.modelo;
                    txtSerie.Text = d.numeroSerie;
                    txtFecha.Text = d.fechaRegistro.ToString("yyyy-MM-dd");
                    txtUbicacion.Text = d.ubicacion;
                    ddlEstado.SelectedValue = d.estado.ToString();

                    divEstado.Visible = !esTecnico; // Oculta el campo estado si es técnico

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
            ddlGrupo.SelectedIndex = 0;
            lblError.Text = "";
            lblError.Visible = false;

            divEstado.Visible = true; // restaurar visibilidad por defecto
        }
    }
}
