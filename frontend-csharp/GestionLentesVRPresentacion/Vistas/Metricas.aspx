<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Metricas.aspx.cs" Inherits="FrontVR.Vistas.Metricas" MasterPageFile="~/Site.Master" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" />
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <div class="container mt-5">
        <h2 class="mb-4">Métricas de Uso</h2>

        <asp:Button ID="btnActualizar" runat="server" Text="Actualizar métricas" OnClick="btnActualizar_Click" CssClass="btn btn-primary mb-4" />

        <div class="row mb-4">
            <div class="col-md-4">
                <h5>Aplicación más usada:</h5>
                <asp:Label ID="lblAppMasUsada" runat="server" CssClass="form-label text-primary"></asp:Label>
            </div>
            <div class="col-md-4">
                <h5>Dispositivo más usado:</h5>
                <asp:Label ID="lblDispositivoMasUsado" runat="server" CssClass="form-label text-success"></asp:Label>
            </div>
            <div class="col-md-4">
                <h5>Dispositivo menos usado:</h5>
                <asp:Label ID="lblDispositivoMenosUsado" runat="server" CssClass="form-label text-danger"></asp:Label>
            </div>
        </div>

        <div class="row mb-5">
            <div class="col-md-12">
                <h4>Distribución por tipo de aplicación</h4>
                <canvas id="chartTipoApps" width="600" height="300"></canvas>
            </div>
        </div>

        <asp:HiddenField ID="hfDataTipoApps" runat="server" />
    </div>

    <script>
        window.onload = function () {
            var rawTipo = document.getElementById('<%= hfDataTipoApps.ClientID %>').value;
            if (rawTipo) {
                var dataTipo = JSON.parse(rawTipo);
                var ctx = document.getElementById('chartTipoApps').getContext('2d');
                new Chart(ctx, {
                    type: 'bar',
                    data: {
                        labels: ['Educativa', 'Entretenimiento', 'Terapéutica', 'Entrenamiento', 'Productividad', 'Simulación', 'Multimedia'],
                        datasets: [{
                            label: 'Cantidad de aplicaciones',
                            data: dataTipo,
                            backgroundColor: 'rgba(54, 162, 235, 0.5)',
                            borderColor: 'rgba(54, 162, 235, 1)',
                            borderWidth: 1
                        }]
                    },
                    options: {
                        responsive: true,
                        scales: {
                            y: { beginAtZero: true }
                        }
                    }
                });
            }
        };
    </script>
</asp:Content>