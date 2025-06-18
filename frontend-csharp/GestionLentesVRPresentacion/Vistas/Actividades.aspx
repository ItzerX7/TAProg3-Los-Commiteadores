<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Actividades.aspx.cs" Inherits="FrontVR.Vistas.Actividades" MasterPageFile="~/Site.Master" %>

<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <h2 class="page-title mb-3">Lista de actividades</h2>

    <asp:GridView ID="gvActividades" runat="server"
        AutoGenerateColumns="False"
        CssClass="table table-striped table-bordered"
        OnRowDataBound="gvActividades_RowDataBound">
        <Columns>

            <asp:BoundField DataField="id" HeaderText="ID" />

            <asp:BoundField DataField="descripcion" HeaderText="Descripción" />

            <asp:BoundField DataField="detallesTecnicos" HeaderText="Detalles Técnicos" />

            <asp:BoundField DataField="fechaHora" HeaderText="Fecha y Hora" DataFormatString="{0:yyyy-MM-dd HH:mm}" />

            <asp:TemplateField HeaderText="Tipo">
                <ItemTemplate>
                    <%# Eval("tipo") %>
                </ItemTemplate>
            </asp:TemplateField>

            <asp:TemplateField HeaderText="Dispositivo">
                <ItemTemplate>
                    <%# Eval("dispositivo.nombre") %>
                </ItemTemplate>
            </asp:TemplateField>

            <asp:TemplateField HeaderText="Usuario">
                <ItemTemplate>
                    <%# Eval("usuario.nombre") %>
                </ItemTemplate>
            </asp:TemplateField>

            <asp:TemplateField HeaderText="Estado" ItemStyle-HorizontalAlign="Center">
                <ItemTemplate>
                    <asp:Label ID="lblEstado" runat="server"
                        Text='<%# GetEstadoTexto(Eval("activo")) %>'
                        CssClass='<%# GetBadgeCss(Eval("activo")) %>' />
                </ItemTemplate>
            </asp:TemplateField>

        </Columns>
    </asp:GridView>
</asp:Content>
