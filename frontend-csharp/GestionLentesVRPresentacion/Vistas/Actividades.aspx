<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Actividades.aspx.cs" Inherits="FrontVR.Vistas.Actividades" MasterPageFile="~/Site.Master" %>


<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
    <h2 class="page-title mb-3">Lista de actividades</h2>

    <!-- Botón descargar reporte de actividades -->
    <asp:LinkButton ID="btnDescargarRepAct" runat="server" CssClass="btn btn-primary mb-3" OnClick="ClickBotonDescargaRepAct">
    <i class="bi bi-file-earmark-pdf"></i> Descargar reporte de actividades (.pdf)
    </asp:LinkButton>
    <!-- Botón descargar reporte de tipo de actividades-->
    <asp:LinkButton ID="btnDescargarRepTipoAct" runat="server" CssClass="btn btn-primary mb-3" OnClick="ClickBotonDescargaRepTipoAct">
    <i class="bi bi-file-earmark-pdf"></i> Descargar reporte de distribución de tipos de actividades (.pdf)
    </asp:LinkButton>
    <!-- Tabla -->
    <asp:GridView ID="gvActividades" runat="server" AutoGenerateColumns="False"
        CssClass="table table-bordered table-hover align-middle text-center" OnRowDataBound="gvActividades_RowDataBound">
        <HeaderStyle CssClass="table-dark" />
        <Columns>
            <asp:BoundField DataField="id" HeaderText="ID" />
            <asp:TemplateField HeaderText="Tipo">
                <ItemTemplate>
                    <%# Eval("tipo") %>
                </ItemTemplate>
            </asp:TemplateField>
            <asp:BoundField DataField="descripcion" HeaderText="Descripción" />
            <asp:BoundField DataField="detallesTecnicos" HeaderText="Detalles Técnicos" />
            <asp:BoundField DataField="fechaHora" HeaderText="Fecha y Hora" DataFormatString="{0:yyyy-MM-dd HH:mm}" />
            <asp:TemplateField HeaderText="Acción">
                <ItemTemplate>
                    <asp:LinkButton ID="lnkEliminar" runat="server"
                        CommandName="Eliminar"
                        CommandArgument='<%# Eval("id") %>'
                        Text="Eliminar"
                        CssClass="btn btn-sm btn-danger"
                        OnClientClick="return confirm('¿Está seguro de eliminar esta actividad?');" />
                    </div>
                </ItemTemplate>
            </asp:TemplateField>
        </Columns>
    </asp:GridView>
</asp:Content>
