<%@ Page Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true"
         CodeBehind="Dispositivos.aspx.cs" Inherits="FrontVR.Vistas.Dispositivos" %>

<asp:Content ID="Main" ContentPlaceHolderID="MainContent" runat="server">
    <h2 class="page-title mb-3">Gestión de Dispositivos</h2>

    <button type="button" class="btn btn-primary mb-3" data-bs-toggle="modal" data-bs-target="#modalDispositivo">
        <i class="fa fa-plus"></i> Nuevo dispositivo
    </button>

    <asp:GridView ID="gvDispositivos" runat="server"
                  AutoGenerateColumns="False" CssClass="table table-striped"
                  DataKeyNames="dispositivoId"
                  OnRowCommand="gvDispositivos_RowCommand">
        <Columns>
            <asp:BoundField DataField="nombre" HeaderText="Nombre" />
            <asp:BoundField DataField="modelo" HeaderText="Modelo" />
            <asp:BoundField DataField="numeroSerie" HeaderText="Serie" />
            <asp:BoundField DataField="fechaRegistro" HeaderText="Fecha" DataFormatString="{0:yyyy-MM-dd}" />
            <asp:BoundField DataField="ubicacion" HeaderText="Ubicación" />
            <asp:TemplateField HeaderText="Acción" ItemStyle-HorizontalAlign="Center">
                <ItemTemplate>
                    <asp:LinkButton ID="lnkAccion" runat="server"
                                    CommandName="EditarDispositivo"
                                    CommandArgument='<%# Eval("dispositivoId") %>'
                                    Text="Editar"
                                    CssClass="btn btn-sm btn-warning me-1" />
                    <asp:LinkButton ID="lnkEliminar" runat="server"
                                    CommandName="Eliminar"
                                    CommandArgument='<%# Eval("dispositivoId") %>'
                                    Text="Eliminar"
                                    CssClass="btn btn-sm btn-danger" />
                </ItemTemplate>
            </asp:TemplateField>
        </Columns>
    </asp:GridView>

    <!-- MODAL NUEVO / EDITAR DISPOSITIVO -->
    <div class="modal fade" id="modalDispositivo" tabindex="-1"
         aria-labelledby="modalDispositivoLabel" aria-hidden="true"
         data-bs-theme="dark">
        <div class="modal-dialog">
            <div class="modal-content bg-dark text-white">
                <div class="modal-header border-secondary">
                    <h5 class="modal-title" id="modalDispositivoLabel">Nuevo dispositivo</h5>
                    <button type="button" class="btn-close btn-close-white"
                            data-bs-dismiss="modal" aria-label="Cerrar"></button>
                </div>

                <asp:UpdatePanel ID="upModalDispositivo" runat="server">
                    <ContentTemplate>
                        <div class="modal-body">
                            <asp:HiddenField ID="hfIdDispositivo" runat="server" />

                            <div class="mb-3">
                                <label for="txtNombre" class="form-label">Nombre</label>
                                <asp:TextBox ID="txtNombre" runat="server"
                                             CssClass="form-control bg-dark text-white border-secondary" />
                            </div>

                            <div class="mb-3">
                                <label for="txtModelo" class="form-label">Modelo</label>
                                <asp:TextBox ID="txtModelo" runat="server"
                                             CssClass="form-control bg-dark text-white border-secondary" />
                            </div>

                            <div class="mb-3">
                                <label for="txtSerie" class="form-label">Número de Serie</label>
                                <asp:TextBox ID="txtSerie" runat="server"
                                             CssClass="form-control bg-dark text-white border-secondary" />
                            </div>

                            <div class="mb-3">
                                <label for="txtFecha" class="form-label">Fecha de Registro</label>
                                <asp:TextBox ID="txtFecha" runat="server"
                                             TextMode="Date"
                                             CssClass="form-control bg-dark text-white border-secondary" />
                            </div>

                            <div class="mb-3">
                                <label for="txtUbicacion" class="form-label">Ubicación</label>
                                <asp:TextBox ID="txtUbicacion" runat="server"
                                             CssClass="form-control bg-dark text-white border-secondary" />
                            </div>

                            <asp:Label ID="lblError" runat="server" CssClass="text-danger" Visible="false" />
                        </div>

                        <div class="modal-footer border-secondary">
                            <button type="button" class="btn btn-outline-light"
                                    data-bs-dismiss="modal">Cancelar</button>
                            <asp:Button ID="btnGuardar" runat="server" Text="Guardar"
                                        CssClass="btn btn-primary" OnClick="btnGuardar_Click" />
                        </div>
                    </ContentTemplate>
                </asp:UpdatePanel>
            </div>
        </div>
    </div>
</asp:Content>
