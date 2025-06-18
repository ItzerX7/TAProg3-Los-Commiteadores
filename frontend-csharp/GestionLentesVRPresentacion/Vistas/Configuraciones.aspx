<%@ Page Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true"
         CodeBehind="Configuracion.aspx.cs" Inherits="FrontVR.Vistas.Configuracion" %>

<asp:Content ID="Main" ContentPlaceHolderID="MainContent" runat="server">
    <h2 class="page-title mb-3">Configuración de Usuarios</h2>

    <button type="button" class="btn btn-primary mb-3" data-bs-toggle="modal" data-bs-target="#modalUsuario">
        <i class="fa fa-user-plus"></i> Nuevo usuario
    </button>

    <asp:GridView ID="gvUsuarios" runat="server"
                  AutoGenerateColumns="False"
                  CssClass="table table-striped"
                  DataKeyNames="usuarioId"
                  OnRowCommand="gvUsuarios_RowCommand"
                  EmptyDataText="No hay usuarios registrados aún.">
        <Columns>
            <asp:BoundField DataField="nombre" HeaderText="Nombres" />
            <asp:BoundField DataField="correo" HeaderText="Correo" />
            <asp:BoundField DataField="rol.nombre" HeaderText="Rol" />
            <asp:TemplateField HeaderText="Acción" ItemStyle-HorizontalAlign="Center">
                <ItemTemplate>
                    <asp:LinkButton ID="lnkEditar" runat="server" CommandName="EditarUsuario"
                                    CommandArgument='<%# Eval("id") %>'
                                    CssClass="btn btn-sm btn-warning me-1" Text="Editar" />
                    <asp:LinkButton ID="lnkEliminar" runat="server" CommandName="Eliminar"
                                    CommandArgument='<%# Eval("id") %>'
                                    CssClass="btn btn-sm btn-danger" Text="Eliminar" />
                </ItemTemplate>
            </asp:TemplateField>
        </Columns>
    </asp:GridView>

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
                        </div>
                    </ContentTemplate>
                </asp:UpdatePanel>
            </div>
        </div>
    </div>
</asp:Content>