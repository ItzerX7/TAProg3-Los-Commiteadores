<%@ Page Language="C#" 
    MasterPageFile="~/Site.Master"
    AutoEventWireup="true"
    CodeBehind="Actividades.aspx.cs"
    Inherits="FrontVR.Vistas.Actividades" %>

<asp:Content ID="Main" ContentPlaceHolderID="MainContent" runat="server">
    <h2 class="page-title mb-3">Lista de actividades</h2>

    <asp:GridView ID="gvActividades" runat="server"
        AutoGenerateColumns="False" CssClass="table table-striped"
        OnRowDataBound="gvActividades_RowDataBound">
        <Columns>
            <asp:BoundField DataField="id" HeaderText="ID" />
            <asp:BoundField DataField="nombre" HeaderText="Nombre" />
            <asp:BoundField DataField="descripcion" HeaderText="Descripción" />
            <asp:BoundField DataField="fechaInicio" HeaderText="Fecha Inicio" DataFormatString="{0:yyyy-MM-dd}" />
            <asp:BoundField DataField="fechaFin" HeaderText="Fecha Fin" DataFormatString="{0:yyyy-MM-dd}" />
            <asp:TemplateField HeaderText="Estado">
                <ItemTemplate>
                    <asp:Label ID="lblEstado" runat="server"
                        Text='<%# GetEstadoTexto(Eval("activo")) %>'
                        CssClass='<%# GetBadgeCss(Eval("activo")) %>' />
                </ItemTemplate>
            </asp:TemplateField>
        </Columns>
    </asp:GridView>
</asp:Content>
