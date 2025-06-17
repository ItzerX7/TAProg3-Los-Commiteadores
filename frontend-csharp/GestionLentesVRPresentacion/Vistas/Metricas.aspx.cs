using System;
using System.Web.UI;

using FrontVR.ServiceReference1; // Descomentar cuando se agregue el servicio web

namespace FrontVR.Vistas
{
    public partial class Metricas : Page
    {
        MetricaUsoWSClient servicio = new MetricaUsoWSClient(); // Descomentar cuando esté listo el WS

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                CargarKPI();
                CargarDatosGraficoUso();
                CargarDatosGraficoApps();
                CargarDatosGraficoLinea();
            }
        }

        private void CargarKPI()
        {
            // Ejemplo cuando el WS esté disponible:
            //lblMasUsado.Text = servicio.obtenerDispositivoMasUsado().nombre;
            //lblMenosUsado.Text = servicio.obtenerDispositivoMenosUsado().nombre;
            //lblAppPopular.Text = servicio.obtenerAppMasEjecutada().nombre;
        }

        private void CargarDatosGraficoUso()
        {
            // var dispositivos = servicio.listarDispositivos();
            // var tiempos = servicio.obtenerTiempoUsoPorDispositivo();
            // Aquí devolverías los datos vía JSON para llenar el Chart.js desde code-behind o WebMethod
        }

        private void CargarDatosGraficoApps()
        {
            // var apps = servicio.listarAppsConContador();
            // Retornarías nombre y cantidad para el gráfico tipo doughnut
        }

        private void CargarDatosGraficoLinea()
        {
            // var series = servicio.obtenerTiempoUsoDiarioPorDispositivo();
            // Devolverías series por dispositivo para construir un gráfico de líneas
        }
    }
}
