using System;
<<<<<<< HEAD
using System.Web.UI;

using FrontVR.GestionlentesvrWS; // Descomentar cuando se agregue el servicio web
=======
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Web.UI;
using FrontVR.GestionlentesvrWS;
using Newtonsoft.Json; // Descomentar cuando se agregue el servicio web
>>>>>>> 72e72ce (Ignorar archivos temporales de Visual Studio y build)

namespace FrontVR.Vistas
{
    public partial class Metricas : Page
    {
        MetricaUsoWSClient servicio = new MetricaUsoWSClient(); // Descomentar cuando esté listo el WS
<<<<<<< HEAD
=======
        AplicacionWSClient appservicio = new AplicacionWSClient();
        public string LabelsJson { get; private set; }
        public string ValuesJson { get; private set; }
>>>>>>> 72e72ce (Ignorar archivos temporales de Visual Studio y build)

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                CargarKPI();
                CargarDatosGraficoUso();
                CargarDatosGraficoApps();
<<<<<<< HEAD
                CargarDatosGraficoLinea();
=======
>>>>>>> 72e72ce (Ignorar archivos temporales de Visual Studio y build)
            }
        }

        private void CargarKPI()
        {
<<<<<<< HEAD
            // Ejemplo cuando el WS esté disponible:
            //lblMasUsado.Text = servicio.obtenerDispositivoMasUsado().nombre;
            //lblMenosUsado.Text = servicio.obtenerDispositivoMenosUsado().nombre;
            //lblAppPopular.Text = servicio.obtenerAppMasEjecutada().nombre;
=======
           // lblMasUsadoData.Text = servicio.obtenerDispositivoMasUsado().nombre;
            //lblMenosUsadoData.Text = servicio.obtenerDispositivoMenosUsado().nombre;
            //lblAppPopularData.Text = servicio.obtenerAppMasUsada().nombre;
>>>>>>> 72e72ce (Ignorar archivos temporales de Visual Studio y build)
        }

        private void CargarDatosGraficoUso()
        {
<<<<<<< HEAD
            // var dispositivos = servicio.listarDispositivos();
=======
            //var dispositivos = servicio.();
>>>>>>> 72e72ce (Ignorar archivos temporales de Visual Studio y build)
            // var tiempos = servicio.obtenerTiempoUsoPorDispositivo();
            // Aquí devolverías los datos vía JSON para llenar el Chart.js desde code-behind o WebMethod
        }

        private void CargarDatosGraficoApps()
        {
<<<<<<< HEAD
            // var apps = servicio.listarAppsConContador();
            // Retornarías nombre y cantidad para el gráfico tipo doughnut
        }

        private void CargarDatosGraficoLinea()
        {
            // var series = servicio.obtenerTiempoUsoDiarioPorDispositivo();
            // Devolverías series por dispositivo para construir un gráfico de líneas
=======

            // llamas a tu servicio que te devuelve la lista con nombre y contador
            // Aquí asumo que listarAppsConContador() retorna List<AppContadorDTO>
           // var apps = appservicio.contarAplicacionesPorTipoEnMetricas();

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
            //ar cantidades = apps.ToList();

            // serializa a JSON para inyectar en JS
            LabelsJson = JsonConvert.SerializeObject(nombres);
            //ValuesJson = JsonConvert.SerializeObject(cantidades);
            // Retornarías nombre y cantidad para el gráfico tipo doughnut
>>>>>>> 72e72ce (Ignorar archivos temporales de Visual Studio y build)
        }
    }
}
