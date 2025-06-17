<%@ Page Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true"
         CodeBehind="Metricas.aspx.cs" Inherits="FrontVR.Vistas.Metricas" %>

<asp:Content ID="Main" ContentPlaceHolderID="MainContent" runat="server">
    <h2 class="page-title mb-3">Dashboard de Métricas</h2>

    <!-- Indicadores clave -->
    <div class="row mb-4">
        <div class="col-md-4">
            <div class="card text-white bg-info mb-3">
                <div class="card-body">
                    <h5 class="card-title">Dispositivo más usado</h5>
                    <p class="card-text" id="lblMasUsado">-</p>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card text-white bg-danger mb-3">
                <div class="card-body">
                    <h5 class="card-title">Dispositivo más inactivo</h5>
                    <p class="card-text" id="lblMenosUsado">-</p>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card text-white bg-success mb-3">
                <div class="card-body">
                    <h5 class="card-title">App más ejecutada</h5>
                    <p class="card-text" id="lblAppPopular">-</p>
                </div>
            </div>
        </div>
    </div>

    <!-- Graficos -->
    <div class="row">
        <div class="col-md-6">
            <canvas id="graficoTiempoUso"></canvas>
        </div>
        <div class="col-md-6">
            <canvas id="graficoApps"></canvas>
        </div>
    </div>

    <div class="row mt-5">
        <div class="col-md-12">
            <canvas id="graficoLineaTiempo"></canvas>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script>
        document.addEventListener("DOMContentLoaded", function () {
            // TODO: Los siguientes datos deben venir desde el backend via WS

            // Tiempo de uso por dispositivo
            const graficoTiempo = new Chart(document.getElementById('graficoTiempoUso'), {
                type: 'bar',
                data: {
                    labels: ['VR-01', 'VR-02', 'VR-03'],
                    datasets: [{
                        label: 'Horas de uso',
                        data: [5.5, 2.8, 6.7],
                        backgroundColor: 'rgba(54, 162, 235, 0.6)'
                    }]
                }
            });

            // Distribucion de apps
            const graficoApps = new Chart(document.getElementById('graficoApps'), {
                type: 'doughnut',
                data: {
                    labels: ['Educativa', 'Terapéutica', 'Entretenimiento'],
                    datasets: [{
                        data: [40, 35, 25],
                        backgroundColor: [
                            'rgba(255, 99, 132, 0.6)',
                            'rgba(75, 192, 192, 0.6)',
                            'rgba(255, 206, 86, 0.6)'
                        ]
                    }]
                }
            });

            // Actividad en el tiempo
            const graficoLinea = new Chart(document.getElementById('graficoLineaTiempo'), {
                type: 'line',
                data: {
                    labels: ['Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes'],
                    datasets: [
                        {
                            label: 'VR-01',
                            data: [60, 45, 30, 50, 70],
                            borderColor: 'rgba(54, 162, 235, 1)',
                            fill: false
                        },
                        {
                            label: 'VR-02',
                            data: [20, 35, 25, 30, 40],
                            borderColor: 'rgba(255, 99, 132, 1)',
                            fill: false
                        }
                    ]
                }
            });

            // TODO: Mostrar los KPIs desde WS
            document.getElementById('lblMasUsado').innerText = 'VR-03';
            document.getElementById('lblMenosUsado').innerText = 'VR-02';
            document.getElementById('lblAppPopular').innerText = 'Terapéutica';
        });
    </script>
</asp:Content>
