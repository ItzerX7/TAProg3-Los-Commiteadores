<<<<<<< HEAD
﻿<%@ Page Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true"
         CodeBehind="Configuraciones.aspx.cs" Inherits="FrontVR.Vistas.Configuracion" %>

<asp:Content ID="Main" ContentPlaceHolderID="MainContent" runat="server">
    <h2 class="page-title mb-3">Configuración de Usuarios</h2>

    <button type="button" class="btn btn-primary mb-3" data-bs-toggle="modal" data-bs-target="#modalUsuario">
        <i class="fa fa-user-plus"></i> Nuevo usuario
    </button>

    <asp:GridView ID="gvUsuarios" runat="server"
                  AutoGenerateColumns="False"
                  CssClass="table table-striped"
                  DataKeyNames="usuarioId"
                  OnRowCommand="gvUsuarios_RowCommand">
        <Columns>
            <asp:BoundField DataField="nombres" HeaderText="Nombres" />
            <asp:BoundField DataField="correo" HeaderText="Correo" />
            <asp:BoundField DataField="rol.nombre" HeaderText="Rol" />
            <asp:TemplateField HeaderText="Acción" ItemStyle-HorizontalAlign="Center">
                <ItemTemplate>
                    <asp:LinkButton ID="lnkEditar" runat="server" CommandName="EditarUsuario"
                                    CommandArgument='<%# Eval("usuarioId") %>'
                                    CssClass="btn btn-sm btn-warning me-1" Text="Editar" />
                    <asp:LinkButton ID="lnkEliminar" runat="server" CommandName="Eliminar"
                                    CommandArgument='<%# Eval("usuarioId") %>'
                                    CssClass="btn btn-sm btn-danger" Text="Eliminar" />
=======
﻿<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Configuraciones.aspx.cs" MasterPageFile="~/Site.Master"
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
                        CssClass="btn btn-sm btn-warning me-1">Editar</asp:LinkButton>
                    <asp:LinkButton ID="lnkEliminar" runat="server" CommandName="Eliminar"
                        CommandArgument='<%# Container.DataItemIndex %>'
                        CssClass="btn btn-sm btn-danger"
                        OnClientClick="return confirm('¿Está seguro de eliminar esta configuración?');">Eliminar</asp:LinkButton>
>>>>>>> 72e72ce (Ignorar archivos temporales de Visual Studio y build)
                </ItemTemplate>
            </asp:TemplateField>
        </Columns>
    </asp:GridView>

<<<<<<< HEAD
    <!-- Modal NUEVO / EDITAR USUARIO -->
    <div class="modal fade" id="modalUsuario" tabindex="-1"
         aria-labelledby="modalUsuarioLabel" aria-hidden="true"
         data-bs-theme="dark">
        <div class="modal-dialog">
            <div class="modal-content bg-dark text-white">
                <div class="modal-header border-secondary">
                    <h5 class="modal-title" id="modalUsuarioLabel">Nuevo usuario</h5>
                    <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                </div>

                <asp:UpdatePanel ID="upModalUsuario" runat="server">
                    <ContentTemplate>
                        <div class="modal-body">
                            <asp:HiddenField ID="hfUsuarioId" runat="server" />

                            <div class="mb-3">
                                <label for="txtNombres" class="form-label">Nombres</label>
                                <asp:TextBox ID="txtNombres" runat="server" CssClass="form-control bg-dark text-white border-secondary" />
                            </div>

                            <div class="mb-3">
                                <label for="txtCorreo" class="form-label">Correo</label>
                                <asp:TextBox ID="txtCorreo" runat="server" TextMode="Email" CssClass="form-control bg-dark text-white border-secondary" />
                            </div>

                            <div class="mb-3">
                                <label for="ddlRol" class="form-label">Rol</label>
                                <asp:DropDownList ID="ddlRol" runat="server" CssClass="form-select bg-dark text-white border-secondary" />
                            </div>

                            <asp:Label ID="lblError" runat="server" CssClass="text-danger" Visible="false" />
                        </div>

                        <div class="modal-footer border-secondary">
                            <button type="button" class="btn btn-outline-light" data-bs-dismiss="modal">Cancelar</button>
                            <asp:Button ID="btnGuardar" runat="server" Text="Guardar"
                                        CssClass="btn btn-primary" OnClick="btnGuardar_Click" />
=======
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
                                <label class="form-label">Nombre <span class="text-danger">*</span></label>
                                <asp:TextBox ID="txtNombre" runat="server" CssClass="form-control" placeholder="Nombre" />
                            </div>

                            <div class="mb-3">
                                <label class="form-label">Descripción<span class="text-danger">*</span></label>
                                <asp:TextBox ID="txtDescripcion" runat="server" CssClass="form-control" placeholder="Descripción" />
                            </div>

                            <div class="mb-3">
                                <label class="form-label">Tipo<span class="text-danger">*</span></label>
                                <asp:DropDownList ID="ddlTipo" runat="server" CssClass="form-select" />
                            </div>

                            <div class="mb-3">
                                <label class="form-label">Valor<span class="text-danger">*</span></label>
                                <asp:TextBox ID="txtValor" runat="server" CssClass="form-control" placeholder="Valor" />
                            </div>

                            <asp:Label ID="lblError" runat="server" CssClass="text-danger" Visible="false"></asp:Label>
                        </div>
                        <div class="modal-footer border-secondary">
                            <asp:Button ID="btnGuardar" runat="server" CssClass="btn btn-primary"
                                Text="Guardar" OnClick="btnGuardar_Click" />
>>>>>>> 72e72ce (Ignorar archivos temporales de Visual Studio y build)
                        </div>
                    </ContentTemplate>
                </asp:UpdatePanel>
            </div>
        </div>
    </div>
<<<<<<< HEAD
=======

    <!-- Bootstrap 5 y SweetAlert -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
>>>>>>> 72e72ce (Ignorar archivos temporales de Visual Studio y build)
</asp:Content>
