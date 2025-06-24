using System;
using System.Web.UI;
using System.Web.UI.WebControls;
using FrontVR.GestionlentesvrWS;

namespace FrontVR.Vistas
{
    public partial class Grupos : System.Web.UI.Page
    {
        private GrupoWSClient proxy = new GrupoWSClient();

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                CargarGrupos();
            }
        }

        private void CargarGrupos()
        {
            try
            {
                grupo[] lista = proxy.listarGrupo();
                gvGrupos.DataSource = lista;
                gvGrupos.DataBind();
            }
            catch (System.Exception ex)
            {
                lblErrorGrupo.Text = "Error al cargar los grupos: " + ex.Message;
                lblErrorGrupo.Visible = true;
            }
        }

        protected void btnGuardarGrupo_Click(object sender, EventArgs e)
        {
            try
            {
                grupo nuevoGrupo = new grupo
                {
                    nombre = txtNombreGrupo.Text.Trim(),
                    descripcion = txtDescripcionGrupo.Text.Trim(),
                    ubicacion = txtUbicacionGrupo.Text.Trim(),
                    fechaCreacion = DateTime.Now
                };

                if (string.IsNullOrEmpty(hfIdGrupo.Value))
                {
                    proxy.registrarGrupo(nuevoGrupo);
                }
                else
                {
                    nuevoGrupo.id = int.Parse(hfIdGrupo.Value);
                    proxy.actualizarGrupo(nuevoGrupo);
<<<<<<< HEAD
                    Response.Redirect(Request.RawUrl);

=======
>>>>>>> 72e72ce (Ignorar archivos temporales de Visual Studio y build)
                }

                // Redireccionar a la misma página para que se vea reflejado el cambio
                Response.Redirect(Request.RawUrl);
            }
            catch (System.Exception ex)
            {
                lblErrorGrupo.Text = "Error al guardar: " + ex.Message;
                lblErrorGrupo.Visible = true;
            }
        }

        protected void gvGrupos_RowCommand(object sender, GridViewCommandEventArgs e)
        {
            try
            {
                int id = Convert.ToInt32(e.CommandArgument);

                if (e.CommandName == "EditarGrupo")
                {
                    grupo g = proxy.obtenerGrupo(id);

                    hfIdGrupo.Value = g.id.ToString();
                    txtNombreGrupo.Text = g.nombre;
                    txtDescripcionGrupo.Text = g.descripcion;
                    txtUbicacionGrupo.Text = g.ubicacion;

                    // Cambiar el título del modal (opcional)
                    ScriptManager.RegisterStartupScript(this, GetType(), "AbrirModal", @"
                        document.getElementById('modalGrupoLabel').innerText = 'Editar grupo';
                        var modal = new bootstrap.Modal(document.getElementById('modalGrupo'));
                        modal.show();", true);
                }
                else if (e.CommandName == "EliminarGrupo")
                {
                    proxy.eliminarGrupo(id);
                    CargarGrupos();
                }
            }
            catch (System.Exception ex)
            {
                lblErrorGrupo.Text = "Error al procesar la acción: " + ex.Message;
                lblErrorGrupo.Visible = true;
            }
        }
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 72e72ce (Ignorar archivos temporales de Visual Studio y build)
