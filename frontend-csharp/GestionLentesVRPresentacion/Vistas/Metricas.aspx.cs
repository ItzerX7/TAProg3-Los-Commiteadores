using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Web.UI;

using FrontVR.ServiceReference1;
using Newtonsoft.Json; // Descomentar cuando se agregue el servicio web

namespace FrontVR.Vistas
{
    public partial class Metricas : Page
    {
        MetricaUsoWSClient servicio = new MetricaUsoWSClient(); // Descomentar cuando esté listo el WS
        public string LabelsJson { get; private set; }
        public string ValuesJson { get; private set; }

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                CargarKPI();
                CargarDatosGraficoUso();
                CargarDatosGraficoApps();
            }
        }

        private void CargarKPI()
        {
            lblMasUsadoData.Text = servicio.obtenerDispositivoMasUsado().nombre;
            lblMenosUsadoData.Text = servicio.obtenerDispositivoMenosUsado().nombre;
            lblAppPopularData.Text = servicio.obtenerAppMasEjecutada().nombre;
        }

        private void CargarDatosGraficoUso()
        {
            // var dispositivos = servicio.listarDispositivos();
            // var tiempos = servicio.obtenerTiempoUsoPorDispositivo();
            // Aquí devolverías los datos vía JSON para llenar el Chart.js desde code-behind o WebMethod
        }

        private void CargarDatosGraficoApps()
        {

            // llamas a tu servicio que te devuelve la lista con nombre y contador
            // Aquí asumo que listarAppsConContador() retorna List<AppContadorDTO>
            var apps = servicio.listarAppsConContador();

            // extraes dos arrays: uno de nombres, otro de conteos
            var nombres = new BindingList<string>
{
    "EDUCATIVA",
    "ENTRENAMIENTO",
    "ENTRETENIMIENTO",
    "MULTIMEDIA",
    "PRODUCTIVIDAD",
    "SIMULACION",
    "TERAPEUTICA"
};
            var cantidades = apps.Select(a => a.Contador).ToList();

            // serializa a JSON para inyectar en JS
            LabelsJson = JsonConvert.SerializeObject(nombres);
            ValuesJson = JsonConvert.SerializeObject(cantidades);

            // var apps = servicio.listarAppsConContador();
            // Retornarías nombre y cantidad para el gráfico tipo doughnut
        }
    }
}
