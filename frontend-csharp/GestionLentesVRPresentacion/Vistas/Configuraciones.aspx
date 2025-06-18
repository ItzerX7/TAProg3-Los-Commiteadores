<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Configuraciones.aspx.cs" MasterPageFile="~/Site.Master"
    Inherits="FrontVR.Vistas.Configuraciones" %>

<asp:Content ID="Main" ContentPlaceHolderID="MainContent" runat="server">
    <h2 class="page-title mb-3">Configuraciones</h2>

    <asp:Button ID="btnAgregar" runat="server" Text="Agregar Configuración"
        CssClass="btn btn-success mb-3"
        OnClick="btnAgregar_Click" />

    <asp:GridView ID="gvConfiguraciones" runat="server" AutoGenerateColumns="False"
        OnRowCommand="gvConfiguraciones_RowCommand"
        DataKeyNames="id"
        CssClass="table table-striped">
        <Columns>
            <asp:BoundField DataField="nombre" HeaderText="Nombre" />
            <asp:BoundField DataField="descripcion" HeaderText="Descripción" />
            <asp:BoundField DataField="tipo" HeaderText="Tipo" />
            <asp:BoundField DataField="valor" HeaderText="Valor" />
            <asp:BoundField DataField="fechaCreacion" HeaderText="Fecha Creación" DataFormatString="{0:yyyy-MM-dd}" />
            <asp:TemplateField HeaderText="Acciones">
                <ItemTemplate>
                    <asp:LinkButton ID="lnkEditar" runat="server" CommandName="EditarConfiguracion"
                        CommandArgument='<%# Container.DataItemIndex %>'
                        CssClass="btn btn-sm btn-warning">
                        Editar</asp:LinkButton>
                    <asp:LinkButton ID="lnkEliminar" runat="server" CommandName="Eliminar"
                        CommandArgument='<%# Container.DataItemIndex %>'
                        CssClass="btn btn-sm btn-danger">
                        Eliminar</asp:LinkButton>
                </ItemTemplate>
            </asp:TemplateField>
        </Columns>
    </asp:GridView>

    <!-- Modal NUEVA / EDITAR CONFIGURACION -->
    <div class="modal fade" id="modalConfiguracion" tabindex="-1"
        aria-labelledby="modalConfiguracionLabel" aria-hidden="true"
        data-bs-theme="dark">
        <div class="modal-dialog">
            <div class="modal-content bg-dark text-white">
                <div class="modal-header border-secondary">
                    <h5 class="modal-title" id="modalConfiguracionLabel">Nueva configuración</h5>
                    <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                </div>

                <asp:UpdatePanel ID="upModalConfiguracion" runat="server">
                    <ContentTemplate>
                        <div class="modal-body">
                            <asp:HiddenField ID="hfConfiguracionId" runat="server" />

                            <div class="mb-3">
                                <label class="form-label">Nombre</label>
                                <asp:TextBox ID="txtNombre" runat="server" CssClass="form-control" placeholder="Nombre" />
                            </div>

                            <div class="mb-3">
                                <label class="form-label">Descripción</label>
                                <asp:TextBox ID="txtDescripcion" runat="server" CssClass="form-control" placeholder="Descripción" />
                            </div>

                            <div class="mb-3">
                                <label class="form-label">Tipo</label>
                                <asp:DropDownList ID="ddlTipo" runat="server" CssClass="form-select" />
                            </div>

                            <div class="mb-3">
                                <label class="form-label">Valor</label>
                                <asp:TextBox ID="txtValor" runat="server" CssClass="form-control" placeholder="Valor" />
                            </div>

                            <asp:Label ID="lblError" runat="server" CssClass="text-danger" Visible="false"></asp:Label>
                        </div>
                        <div class="modal-footer border-secondary">
                            <asp:Button ID="btnGuardar" runat="server" CssClass="btn btn-primary"
                                Text="Guardar" OnClick="btnGuardar_Click" />
                        </div>
                    </ContentTemplate>
                </asp:UpdatePanel>
            </div>
        </div>
    </div>
</asp:Content>
