using FrontVR.GestionlentesvrWS;
using System;
using System.Text;
using System.Web.UI;
using System.Web.UI.WebControls;

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
                else if (e.CommandName == "VerDispositivosGrupo")
                {
                    MostrarDispositivosPorGrupo(id);
                }
            }
            catch (System.Exception ex)
            {
                lblErrorGrupo.Text = "Error al procesar la acción: " + ex.Message;
                lblErrorGrupo.Visible = true;
            }
        }

        private void MostrarDispositivosPorGrupo(int idGrupo)
        {
            phDispositivosGrupo.Controls.Clear();
            var dispositivos = proxy.listarDispositivosGrupo(idGrupo);

            var aplicacion = proxy.obtenerGrupo(idGrupo);
            string nombreAplicacion = aplicacion?.nombre ?? "Aplicación desconocida";

            if (dispositivos != null && dispositivos.Length > 0)
            {
                StringBuilder html = new StringBuilder();

                html.AppendFormat("<h4 class='mt-4'>Dispositivos vinculados a <strong>{0}</strong></h4>", nombreAplicacion);
                html.Append("<table class='table table-bordered table-dark mt-2'>");
                html.Append("<thead><tr>");
                html.Append("<th>Nombre</th>");
                html.Append("<th>Modelo</th>");
                html.Append("<th>Número de Serie</th>");
                html.Append("<th>Estado</th>");
                html.Append("</tr></thead><tbody>");

                foreach (var dispositivo in dispositivos)
                {
                    html.Append("<tr>");
                    html.AppendFormat("<td>{0}</td>", dispositivo.nombre);
                    html.AppendFormat("<td>{0}</td>", dispositivo.modelo);
                    html.AppendFormat("<td>{0}</td>", dispositivo.numeroSerie);
                    html.AppendFormat("<td>{0}</td>", dispositivo.estado.ToString());
                    html.Append("</tr>");
                }

                html.Append("</tbody></table>");
                phDispositivosGrupo.Controls.Add(new Literal { Text = html.ToString() });
            }
            else
            {
                phDispositivosGrupo.Controls.Add(new Literal
                {
                    Text = "<div class='alert alert-info mt-3'>No se encontraron dispositivos para esta aplicación.</div>"
                });
            }
        }
    }
}