using System;
using System.Linq;
using System.Web.UI;
using System.Web.UI.WebControls;
using FrontVR.GestionlentesvrWS;

namespace FrontVR.Vistas
{
    public partial class Grupo : Page
    {
        private GrupoWSClient servicio = new GrupoWSClient();

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                CargarGrupos();
            }
        }

        private void CargarGrupos()
        {
            var lista = servicio.listarGrupo().ToList();
            gvGrupos.DataSource = lista;
            gvGrupos.DataBind();
        }

        protected void btnGuardarGrupo_Click(object sender, EventArgs e)
        {
            try
            {
                var grupo = new grupo
                {
                    id = string.IsNullOrWhiteSpace(hfIdGrupo.Value) ? 0 : int.Parse(hfIdGrupo.Value),
                    nombre = txtNombreGrupo.Text.Trim(),
                    descripcion = txtDescripcionGrupo.Text.Trim(),
                    fechaCreacion = DateTime.Now,
                    fechaCreacionSpecified = true
                };

                if (grupo.id == 0)
                {
                    servicio.registrarGrupo(grupo);
                }
                else
                {
                    servicio.actualizarGrupo(grupo);
                }

                LimpiarFormularioGrupo();

                ScriptManager.RegisterStartupScript(this, GetType(), "CerrarModalGrupo",
                    "$('#modalGrupo').modal('hide');", true);

                CargarGrupos();
            }
            catch (System.Exception ex)
            {
                lblErrorGrupo.Text = "Error al guardar: " + ex.Message;
                lblErrorGrupo.Visible = true;
            }
        }

        protected void gvGrupos_RowCommand(object sender, GridViewCommandEventArgs e)
        {
            int id = Convert.ToInt32(e.CommandArgument);

            if (e.CommandName == "EliminarGrupo")
            {
                try
                {
                    servicio.eliminarGrupo(id);
                    CargarGrupos();
                }
                catch (System.Exception ex)
                {
                    System.Diagnostics.Debug.WriteLine($"[Error al eliminar grupo] {ex.Message}");
                }
            }
            else if (e.CommandName == "EditarGrupo")
            {
                var grupo = servicio.obtenerGrupo(id);

                hfIdGrupo.Value = grupo.id.ToString();
                txtNombreGrupo.Text = grupo.nombre;
                txtDescripcionGrupo.Text = grupo.descripcion;

                ScriptManager.RegisterStartupScript(this, GetType(), "AbrirModalGrupo", @"
                    document.getElementById('modalGrupoLabel').textContent = 'Editar grupo';
                    var modal = new bootstrap.Modal(document.getElementById('modalGrupo'));
                    modal.show();", true);
            }
        }

        private void LimpiarFormularioGrupo()
        {
            hfIdGrupo.Value = "";
            txtNombreGrupo.Text = "";
            txtDescripcionGrupo.Text = "";
            lblErrorGrupo.Text = "";
            lblErrorGrupo.Visible = false;
        }
    }
}
