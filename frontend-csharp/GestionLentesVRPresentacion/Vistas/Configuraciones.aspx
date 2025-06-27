<%@ Page Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true"
         CodeBehind="Configuraciones.aspx.cs" Inherits="FrontVR.Vistas.Configuraciones" %>

<asp:Content ID="Main" ContentPlaceHolderID="MainContent" runat="server">

    <h2 class="page-title mb-3">Configuración de Usuarios</h2>

    <asp:Button ID="btnAgregar" runat="server" Text="Agregar Configuración"
        CssClass="btn btn-success mb-3"
        OnClick="btnAgregar_Click" />

    <!-- Tabla -->
    <asp:GridView ID="gvConfiguraciones" runat="server"
        AutoGenerateColumns="False" CssClass="table table-bordered table-hover align-middle text-center"
        DataKeyNames="id" OnRowCommand="gvConfiguraciones_RowCommand">
        <HeaderStyle CssClass="table-dark" />
        <Columns>
            <asp:BoundField DataField="id" HeaderText="ID" />
            <asp:BoundField DataField="nombre" HeaderText="Nombre" />
            <asp:BoundField DataField="descripcion" HeaderText="Descripción" />
            <asp:BoundField DataField="tipo" HeaderText="Tipo" />
            <asp:BoundField DataField="valor" HeaderText="Valor" />
            <asp:BoundField DataField="fechaCreacion" HeaderText="Fecha Creación" DataFormatString="{0:yyyy-MM-dd HH:mm}" />

            <asp:TemplateField HeaderText="Acciones">
                <ItemTemplate>
                    <asp:LinkButton ID="lnkEditar" runat="server"
                        CommandName="EditarConfiguracion"
                        CommandArgument='<%# Eval("id") %>'
                        CssClass="btn btn-sm btn-warning me-1">Editar</asp:LinkButton>

                    <asp:LinkButton ID="lnkEliminar" runat="server"
                        CommandName="EliminarConfiguracion"
                        CommandArgument='<%# Eval("id") %>'
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
            <asp:UpdatePanel ID="upModalConfiguracion" runat="server">
                <ContentTemplate>
                    <div class="modal-header border-secondary">
                        <h5 class="modal-title" id="modalConfiguracionLabel">Nueva configuración</h5>
                        <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                    </div>

                    <div class="modal-body">
                        <asp:HiddenField ID="hfConfiguracionId" runat="server" />

                        <div class="mb-3">
                            <label class="form-label">Nombre <span class="text-danger">*</span></label>
                            <asp:TextBox ID="txtNombre" runat="server" CssClass="form-control" placeholder="Nombre" />
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Descripción <span class="text-danger">*</span></label>
                            <asp:TextBox ID="txtDescripcion" runat="server" CssClass="form-control" placeholder="Descripción" />
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Tipo <span class="text-danger">*</span></label>
                            <asp:DropDownList ID="ddlTipo" runat="server" CssClass="form-select" />
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Valor <span class="text-danger">*</span></label>
                            <asp:TextBox ID="txtValor" runat="server" CssClass="form-control" placeholder="Valor" />
                        </div>

                        <asp:Label ID="lblError" runat="server" CssClass="text-danger" Visible="false" />
                    </div>

                    <div class="modal-footer border-secondary">
                        <button type="button" class="btn btn-outline-light" data-bs-dismiss="modal">Cancelar</button>
                        <asp:Button ID="Button1" runat="server" CssClass="btn btn-primary"
                            Text="Guardar" OnClick="btnGuardar_Click" />
                    </div>
                </ContentTemplate>
            </asp:UpdatePanel>
        </div>
    </div>
</div>


    <!-- Bootstrap 5 y SweetAlert -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</asp:Content>
