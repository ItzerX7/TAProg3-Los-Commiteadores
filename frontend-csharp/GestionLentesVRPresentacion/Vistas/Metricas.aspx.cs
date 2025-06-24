using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Web.UI;
using FrontVR.GestionlentesvrWS;
using Newtonsoft.Json; // Descomentar cuando se agregue el servicio web

namespace FrontVR.Vistas
{
    public partial class Metricas : Page
    {
        MetricaUsoWSClient servicio = new MetricaUsoWSClient(); // Descomentar cuando esté listo el WS
        AplicacionWSClient appservicio = new AplicacionWSClient();
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
            lblAppPopularData.Text = servicio.obtenerAppMasUsada().nombre;
        }

        private void CargarDatosGraficoUso()
        {
            //var dispositivos = servicio.();
            // var tiempos = servicio.obtenerTiempoUsoPorDispositivo();
            // Aquí devolverías los datos vía JSON para llenar el Chart.js desde code-behind o WebMethod
        }

        private void CargarDatosGraficoApps()
        {

            // llamas a tu servicio que te devuelve la lista con nombre y contador
            // Aquí asumo que listarAppsConContador() retorna List<AppContadorDTO>
            var apps = appservicio.contarAplicacionesPorTipoEnMetricas().ToList();

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

            // serializa a JSON para inyectar en JS
            LabelsJson = JsonConvert.SerializeObject(nombres);
            ValuesJson = JsonConvert.SerializeObject(apps);
            // Retornarías nombre y cantidad para el gráfico tipo doughnut
        }
    }
}
