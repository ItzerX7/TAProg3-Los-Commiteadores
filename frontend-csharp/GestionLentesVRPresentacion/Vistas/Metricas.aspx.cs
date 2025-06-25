using System;
using System.Web.Script.Serialization;
using System.Web.UI;
using FrontVR.GestionlentesvrWS;

namespace FrontVR.Vistas
{
    public partial class Metricas : Page
    {
        private AplicacionWSClient aplicacionWS = new AplicacionWSClient();
        private MetricaUsoWSClient metricaWS = new MetricaUsoWSClient();

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                CargarMetricaPrincipal();
                CargarDistribucionTipos();
            }
        }

        protected void btnActualizar_Click(object sender, EventArgs e)
        {
            CargarMetricaPrincipal();
            CargarDistribucionTipos();
        }

        private void CargarMetricaPrincipal()
        {
            try
            {
                var app = metricaWS.obtenerAppMasUsada();
                lblAppMasUsada.Text = app != null ? app.nombre : "No disponible";

                var dispositivoMas = metricaWS.obtenerDispositivoMasUsado();
                lblDispositivoMasUsado.Text = dispositivoMas != null ? dispositivoMas.nombre : "No disponible";

                var dispositivoMenos = metricaWS.obtenerDispositivoMenosUsado();
                lblDispositivoMenosUsado.Text = dispositivoMenos != null ? dispositivoMenos.nombre : "No disponible";
            }
            catch
            {
                lblAppMasUsada.Text = "Error al cargar";
                lblDispositivoMasUsado.Text = "Error al cargar";
                lblDispositivoMenosUsado.Text = "Error al cargar";
            }
        }

        private void CargarDistribucionTipos()
        {
            try
            {
                var lista = aplicacionWS.contarAplicacionesPorTipoEnMetricas(); // List<int> con 7 valores
                var json = new JavaScriptSerializer().Serialize(lista);
                hfDataTipoApps.Value = json;
            }
            catch
            {
                hfDataTipoApps.Value = "[]";
            }
        }
    }
}