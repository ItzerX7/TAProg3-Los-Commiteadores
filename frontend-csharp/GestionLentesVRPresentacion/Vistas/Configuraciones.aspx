<%@ Page Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true"
         CodeBehind="Configuraciones.aspx.cs" Inherits="FrontVR.Vistas.Configuraciones" %>

<asp:Content ID="Main" ContentPlaceHolderID="MainContent" runat="server">
    <h2 class="page-title mb-3">Configuración de Usuarios</h2>

    <asp:Button ID="btnAgregar" runat="server" Text="Agregar Configuración"
        CssClass="btn btn-success mb-3" OnClick="btnAgregar_Click" />

    <asp:GridView ID="gvConfiguraciones" runat="server" AutoGenerateColumns="False"
        OnRowCommand="gvConfiguraciones_RowCommand" DataKeyNames="id"
        CssClass="table table-striped">
        <Columns>
            <asp:BoundField DataField="nombre" HeaderText="Nombre" />
            <asp:BoundField DataField="descripcion" HeaderText="Descripción" />
            <asp:BoundField DataField="tipo" HeaderText="Tipo" />
            <asp:BoundField DataField="valor" HeaderText="Valor" />
            <asp:BoundField DataField="fechaCreacion" HeaderText="Fecha Creación" DataFormatString="{0:yyyy-MM-dd}" />
            <asp:TemplateField HeaderText="Acciones">
                <ItemTemplate>
                    <asp:LinkButton ID="btnEditar" runat="server" CommandName="EditarConfiguracion"
                        CommandArgument='<%# Container.DataItemIndex %>'
                        CssClass="btn btn-sm btn-warning me-1">Editar</asp:LinkButton>
                    <asp:LinkButton ID="btnEliminar" runat="server" CommandName="Eliminar"
                        CommandArgument='<%# Container.DataItemIndex %>'
                        CssClass="btn btn-sm btn-danger"
                        OnClientClick="return confirm('¿Está seguro de eliminar esta configuración?');">Eliminar</asp:LinkButton>
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
                                <label for="txtNombre" class="form-label">Nombre <span class="text-danger">*</span></label>
                                <asp:TextBox ID="txtNombre" runat="server" CssClass="form-control" />
                            </div>
                            <div class="mb-3">
                                <label for="txtDescripcion" class="form-label">Descripción <span class="text-danger">*</span></label>
                                <asp:TextBox ID="txtDescripcion" runat="server" CssClass="form-control" />
                            </div>
                            <div class="mb-3">
                                <label for="ddlTipo" class="form-label">Tipo <span class="text-danger">*</span></label>
                                <asp:DropDownList ID="ddlTipo" runat="server" CssClass="form-select" />
                            </div>
                            <div class="mb-3">
                                <label for="txtValor" class="form-label">Valor <span class="text-danger">*</span></label>
                                <asp:TextBox ID="txtValor" runat="server" CssClass="form-control" />
                            </div>
                            <div class="mb-3">
                                <label for="txtFecha" class="form-label">Fecha de Creación</label>
                                <asp:TextBox ID="txtFecha" runat="server" CssClass="form-control" TextMode="Date" />
                            </div>
                            <asp:Label ID="lblError" runat="server" CssClass="text-danger" Visible="false" />
                        </div>
                        <div class="modal-footer border-secondary">
                            <button type="button" class="btn btn-outline-light" data-bs-dismiss="modal">Cancelar</button>
                            <asp:Button ID="btnGuardar" runat="server" CssClass="btn btn-primary" Text="Guardar" OnClick="btnGuardar_Click" />
                        </div>
                    </ContentTemplate>
                </asp:UpdatePanel>
            </div>
        </div>
    </div>

    <!-- Bootstrap y SweetAlert -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</asp:Content>