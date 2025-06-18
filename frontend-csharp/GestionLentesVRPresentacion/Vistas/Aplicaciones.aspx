<%@ Page Language="C#"
         MasterPageFile="~/Site.Master"
         AutoEventWireup="true"
         CodeBehind="Aplicaciones.aspx.cs"
         Inherits="FrontVR.Vistas.Aplicaciones" %>

<asp:Content ID="Main" ContentPlaceHolderID="MainContent" runat="server">
    <h2 class="page-title mb-3">Aplicaciones disponibles</h2>

    <button type="button" class="btn btn-primary mb-3" data-bs-toggle="modal" data-bs-target="#modalNuevaApp">
        <i class="fa fa-plus"></i> Nueva aplicación
    </button>

    <asp:GridView ID="gvAplicaciones" runat="server"
                  AutoGenerateColumns="False" CssClass="table table-striped"
                  DataKeyNames="Id"
                  OnRowCommand="gvAplicaciones_RowCommand">
        <Columns>
            <asp:BoundField DataField="nombre" HeaderText="Nombre" />
            <asp:BoundField DataField="version" HeaderText="Versión" />
            <asp:BoundField DataField="tamanoMb" HeaderText="Tamaño&nbsp;(MB)" DataFormatString="{0:N1}" />
            <asp:TemplateField HeaderText="Estado">
            </asp:TemplateField>
            <asp:TemplateField HeaderText="Acción" ItemStyle-HorizontalAlign="Center">
            </asp:TemplateField>
        </Columns>
    </asp:GridView>

    <!-- Modal NUEVA APLICACIÓN -->
    <div class="modal fade" id="modalNuevaApp" tabindex="-1"
         aria-labelledby="modalNuevaAppLabel" aria-hidden="true"
         data-bs-theme="dark">
        <div class="modal-dialog">
            <div class="modal-content bg-dark text-white">
                <div class="modal-header border-secondary">
                    <h5 class="modal-title" id="modalNuevaAppLabel">Nueva aplicación</h5>
                    <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                </div>

                <asp:UpdatePanel ID="upModal" runat="server">
                    <ContentTemplate>
                        <div class="modal-body">
                            <div class="mb-3">
                                <label for="txtNombre" class="form-label">Nombre</label>
                                <asp:TextBox ID="txtNombre" runat="server"
                                             CssClass="form-control bg-dark text-white border-secondary" />
                            </div>
                            <div class="mb-3">
                                <label for="txtVersion" class="form-label">Versión</label>
                                <asp:TextBox ID="txtVersion" runat="server"
                                             CssClass="form-control bg-dark text-white border-secondary" />
                            </div>
                            <div class="mb-3">
                                <label for="txtTamano" class="form-label">Tamaño&nbsp;(MB)</label>
                                <asp:TextBox ID="txtTamano" runat="server" TextMode="Number"
                                             CssClass="form-control bg-dark text-white border-secondary" />
                            </div>
                            <asp:Label ID="lblError" runat="server"
                                       CssClass="text-danger" Visible="false" />
                            <div class="mb-3">
                                <label for="txtDescripcion" class="form-label">Descripción</label>
                                <asp:TextBox ID="txtDescripcion" runat="server"
                                             TextMode="MultiLine" Rows="3"
                                             CssClass="form-control bg-dark text-white border-secondary" />
                            </div>

                            <div class="mb-3">
                                <label for="txtDesarrollador" class="form-label">Desarrollador</label>
                                <asp:TextBox ID="txtDesarrollador" runat="server"
                                             CssClass="form-control bg-dark text-white border-secondary" />
                            </div>

                            <div class="mb-3">
                                <label for="txtRutaInstalador" class="form-label">Ruta del Instalador</label>
                                <asp:TextBox ID="txtRutaInstalador" runat="server"
                                             CssClass="form-control bg-dark text-white border-secondary" />
                            </div>
                                           <div class="mb-3">
                                               <label for="txtFechaLanzamiento" class="form-label">Fecha de Lanzamiento</label>
                                               <asp:TextBox ID="txtFechaLanzamiento" runat="server"
                                                    TextMode="Date"
                                                    CssClass="form-control bg-dark text-white border-secondary" />

                                           </div>
                            

                            <div class="mb-3">
                                <label for="ddlCategoria" class="form-label">Categoría</label>
                                <asp:DropDownList ID="ddlCategoria" runat="server"
                                                  CssClass="form-select bg-dark text-white border-secondary">
                                    <asp:ListItem Text="EDUCATIVA" Value="EDUCATIVA" />
                                    <asp:ListItem Text="ENTRETENIMIENTO" Value="ENTRETENIMIENTO" />
                                    <asp:ListItem Text="TERAPEUTICA" Value="TERAPEUTICA" />
                                    <asp:ListItem Text="ENTRENAMIENTO" Value="ENTRENAMIENTO" />
                                    <asp:ListItem Text="PRODUCTIVIDAD" Value="PRODUCTIVIDAD" />
                                    <asp:ListItem Text="SIMULACION" Value="SIMULACION" />
                                    <asp:ListItem Text="MULTIMEDIA" Value="MULTIMEDIA" />
                                </asp:DropDownList>
                            </div>

                            <div class="form-check mb-3">
                                <asp:CheckBox ID="chkActivo" runat="server" CssClass="form-check-input" />
                                <label class="form-check-label" for="chkActivo">Activo</label>
                            </div>

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
