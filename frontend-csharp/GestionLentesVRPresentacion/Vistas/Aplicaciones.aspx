<%@ Page Language="C#"
    MasterPageFile="~/Site.Master"
    AutoEventWireup="true"
    CodeBehind="Aplicaciones.aspx.cs"
    Inherits="FrontVR.Vistas.Aplicaciones" %>

<asp:Content ID="Main" ContentPlaceHolderID="MainContent" runat="server">
    <h2 class="page-title mb-3">Aplicaciones disponibles</h2>

    <asp:LinkButton ID="btnNuevaAplicacion" runat="server" CssClass="btn btn-primary mb-3"
        OnClientClick="limpiarModal(); return false;"
        data-bs-toggle="modal" data-bs-target="#modalAplicacion">
        <i class="fa fa-plus"></i> Nueva aplicación
    </asp:LinkButton>
        <!-- Botón descargar reporte de aplicaciones -->
    <asp:LinkButton ID="btnDescargarRepApp" runat="server" CssClass="btn btn-primary mb-3" OnClick="ClickBotonDescargaRepApp">
    <i class="bi bi-file-earmark-pdf"></i> Descargar reporte de aplicaciones (.pdf)
    </asp:LinkButton>
    <!-- Tabla -->
<div style="max-height: 50vh; overflow-y: auto;">
    <asp:GridView ID="gvAplicaciones" runat="server"
        AutoGenerateColumns="False" CssClass="table table-bordered table-hover align-middle text-center"
        DataKeyNames="id" OnRowCommand="gvAplicaciones_RowCommand">
        <HeaderStyle CssClass="table-dark" />
        <Columns>
            <asp:BoundField DataField="id" HeaderText="ID" />
            <asp:BoundField DataField="nombre" HeaderText="Nombre" />
            <asp:BoundField DataField="version" HeaderText="Versión" />
            <asp:BoundField DataField="tamanoMb" HeaderText="Tamaño&nbsp;(MB)" DataFormatString="{0:N1}" />
            <asp:BoundField DataField="desarrollador" HeaderText="Desarrollador" />
            <asp:TemplateField HeaderText="Estado">
                <ItemTemplate>
                    <asp:Label ID="lblEstado" runat="server"
                        Text='<%# GetEstadoTexto(Eval("activo")) %>'
                        CssClass='<%# GetBadgeCss(Eval("activo")) %>' />
                </ItemTemplate>
            </asp:TemplateField>

            <asp:TemplateField HeaderText="Acciones" ItemStyle-HorizontalAlign="Center">
                <ItemTemplate>
                    <asp:LinkButton ID="btnVerDispositivos" runat="server"
                        CommandName="VerDispApp"
                        CommandArgument='<%# Eval("id") %>'
                        Text="Ver Disp."
                        CssClass="btn btn-sm btn-outline-primary me-1" />
                    <asp:LinkButton ID="btnEditar" runat="server"
                        CommandName="EditarApp"
                        CommandArgument='<%# Eval("id") %>'
                        Text="Editar"
                        CssClass="btn btn-sm btn-warning me-1" />
                    <asp:LinkButton ID="btnEliminar" runat="server"
                        CommandName="EliminarApp"
                        CommandArgument='<%# Eval("id") %>'
                        Text="Eliminar"
                        CssClass="btn btn-sm btn-danger" />
                </ItemTemplate>
            </asp:TemplateField>
        </Columns>
    </asp:GridView>
</div>
    <!-- Aquí se renderizará la tabla de dispositivos vinculados -->
    <asp:PlaceHolder ID="phDispositivos" runat="server"></asp:PlaceHolder>
    <!-- Campos ocultos y botón invisible para confirmación de eliminación -->
    <asp:HiddenField ID="hfEliminarIdApp" runat="server" />
    <asp:Button ID="btnConfirmarEliminar" runat="server" Style="display:none;" OnClick="btnConfirmarEliminar_Click" />

    <!-- Modal -->
    <div class="modal fade" id="modalAplicacion" tabindex="-1"
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
                            <asp:HiddenField ID="hfIdAplicacion" runat="server" />
                            
                            <div class="mb-3">
                                <label for="txtNombre" class="form-label">Nombre <span class="text-danger">*</span></label>
                                <asp:TextBox ID="txtNombre" runat="server"
                                    CssClass="form-control" />
                            </div>
                            <div class="mb-3">
                                <label for="txtVersion" class="form-label">Versión <span class="text-danger">*</span></label>
                                <asp:TextBox ID="txtVersion" runat="server"
                                    CssClass="form-control" />
                            </div>
                            <div class="mb-3">
                                <label for="txtTamano" class="form-label">Tamaño&nbsp;(MB) <span class="text-danger">*</span></label>
                                <asp:TextBox ID="txtTamano" runat="server" TextMode="Number"
                                    CssClass="form-control" />
                            </div>
                            <asp:Label ID="lblError" runat="server"
                                CssClass="text-danger" Visible="false" />
                            <div class="mb-3">
                                <label for="txtDescripcion" class="form-label">Descripción <span class="text-danger">*</span></label>
                                <asp:TextBox ID="txtDescripcion" runat="server"
                                    TextMode="MultiLine" Rows="3"
                                    CssClass="form-control bg-dark text-white border-secondary" />
                            </div>

                            <div class="mb-3">
                                <label for="txtDesarrollador" class="form-label">Desarrollador <span class="text-danger">*</span></label>
                                <asp:TextBox ID="txtDesarrollador" runat="server"
                                    CssClass="form-control bg-dark text-white border-secondary" />
                            </div>

                            <div class="mb-3">
                                <label for="ddlCategoria" class="form-label">Categoría <span class="text-danger">*</span></label>
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

                        </div>

                        <div class="modal-footer border-secondary">
                            <button type="button" class="btn btn-outline-light"
                                data-bs-dismiss="modal">
                                Cancelar</button>
                            <asp:Button ID="btnGuardar" runat="server" Text="Guardar"
                                CssClass="btn btn-primary" OnClick="btnGuardar_Click" />
                        </div>
                    </ContentTemplate>
                </asp:UpdatePanel>
            </div>
        </div>
        

    </div>
    
    <!-- Spinner visible en la esquina superior derecha -->
    <div id="spinnerOperacion" style="position: fixed; top: 10px; right: 10px; z-index: 9999; display: none;">
        <div class="spinner-border text-warning" role="status">
            <span class="visually-hidden">Eliminando...</span>
        </div>
    </div>


     
    <!-- Scripts -->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        function limpiarModal() {
            document.getElementById('modalNuevaAppLabel').textContent = 'Nueva aplicación';

            document.getElementById('<%= txtNombre.ClientID %>').value = '';
            document.getElementById('<%= txtVersion.ClientID %>').value = '';
            document.getElementById('<%= txtTamano.ClientID %>').value = '';
            document.getElementById('<%= txtDescripcion.ClientID %>').value = '';
            document.getElementById('<%= txtDesarrollador.ClientID %>').value = '';
            document.getElementById('<%= ddlCategoria.ClientID %>').selectedIndex = 0;
            document.getElementById('<%= hfIdAplicacion.ClientID %>').value = '';
        }

        function mostrarExitoYRefrescar(mensaje) {
            Swal.fire({
                icon: 'success',
                title: mensaje,
                confirmButtonText: 'OK'
            }).then(() => {
                window.location.href = window.location.href;
            });
        }

        const modalAplicacion = document.getElementById('modalAplicacion');
        modalAplicacion.addEventListener('hidden.bs.modal', function () {
            document.getElementById('modalNuevaAppLabel').textContent = 'Nueva aplicación';
        });
        
    </script>

   

</asp:Content>
