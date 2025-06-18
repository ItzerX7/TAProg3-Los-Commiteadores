<%@ Page Title="Inicio" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="PantallaInicio.aspx.cs" Inherits="FrontVR.Vistas.PantallaInicio" %>

<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <div class="intro text-center p-4 rounded" style="background-color: #2a2a2a;">
        <h1 class="text-info mb-4">Bienvenido a la Plataforma de Gestión de Dispositivos VR</h1>
        <p class="text-light fs-5">
            Este sistema está diseñado para facilitar la supervisión, administración y análisis del uso de dispositivos
            de realidad virtual en entornos educativos, corporativos o de investigación. Desde aquí podrás acceder
            a estadísticas detalladas, gestionar grupos de dispositivos, visualizar alertas de batería y más.
        </p>
        <p class="text-light fs-5">
            Explora el panel lateral para acceder a cada módulo del sistema. ¡Optimiza tu experiencia con tecnología VR de forma inteligente y centralizada!
        </p>

        <img src="/Images/VR_Welcome.png" alt="Sistema VR" class="img-fluid mt-4 rounded" style="max-width: 500px;" />
    </div>
</asp:Content>
