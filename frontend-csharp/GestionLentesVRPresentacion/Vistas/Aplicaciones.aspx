<%@ Page Language="C#"
<<<<<<< HEAD
         MasterPageFile="~/Site.Master"
         AutoEventWireup="true"
         CodeBehind="Aplicaciones.aspx.cs"
         Inherits="FrontVR.Vistas.Aplicaciones" %>
=======
    MasterPageFile="~/Site.Master"
    AutoEventWireup="true"
    CodeBehind="Aplicaciones.aspx.cs"
    Inherits="FrontVR.Vistas.Aplicaciones" %>
>>>>>>> 72e72ce (Ignorar archivos temporales de Visual Studio y build)

<asp:Content ID="Main" ContentPlaceHolderID="MainContent" runat="server">
    <h2 class="page-title mb-3">Aplicaciones disponibles</h2>

<<<<<<< HEAD
    <button type="button" class="btn btn-primary mb-3" data-bs-toggle="modal" data-bs-target="#modalNuevaApp">
        <i class="fa fa-plus"></i> Nueva aplicación
    </button>

    <asp:GridView ID="gvAplicaciones" runat="server" 
            AutoGenerateColumns="False" CssClass="table table-striped"
            DataKeyNames="id"
            OnRowCommand="gvAplicaciones_RowCommand">
            <Columns>
=======
    <button type="button" class="btn btn-primary mb-3" data-bs-toggle="modal" data-bs-target="#modalAplicacion">
        <i class="fa fa-plus"></i>Nueva aplicación
    </button>

    <asp:GridView ID="gvAplicaciones" runat="server"
        AutoGenerateColumns="False" CssClass="table table-striped"
        DataKeyNames="id"
        OnRowCommand="gvAplicaciones_RowCommand">
        <Columns>
>>>>>>> 72e72ce (Ignorar archivos temporales de Visual Studio y build)
            <asp:BoundField DataField="nombre" HeaderText="Nombre" />
            <asp:BoundField DataField="version" HeaderText="Versión" />
            <asp:BoundField DataField="tamanoMb" HeaderText="Tamaño&nbsp;(MB)" DataFormatString="{0:N1}" />
            <asp:TemplateField HeaderText="Estado">
                <ItemTemplate>
                    <asp:Label ID="lblEstado" runat="server"
<<<<<<< HEAD
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

    <!-- Modal NUEVA APLICACIÓN -->
    <div class="modal fade" id="modalNuevaApp" tabindex="-1"
         aria-labelledby="modalNuevaAppLabel" aria-hidden="true"
         data-bs-theme="dark">
=======
                        Text='<%# GetEstadoTexto(Eval("activo")) %>'
                        CssClass='<%# GetBadgeCss(Eval("activo")) %>' />
                </ItemTemplate>
            </asp:TemplateField>


            <asp:TemplateField HeaderText="Acciones" ItemStyle-HorizontalAlign="Left">
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
    <!-- Aquí se renderizará la tabla de dispositivos vinculados -->
    <asp:PlaceHolder ID="phDispositivos" runat="server"></asp:PlaceHolder>


    <!-- Modal -->
    <div class="modal fade" id="modalAplicacion" tabindex="-1"
        aria-labelledby="modalNuevaAppLabel" aria-hidden="true"
        data-bs-theme="dark">
>>>>>>> 72e72ce (Ignorar archivos temporales de Visual Studio y build)
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
                                <label for="txtNombre" class="form-label">Nombre</label>
                                <asp:TextBox ID="txtNombre" runat="server"
<<<<<<< HEAD
                                             CssClass="form-control bg-dark text-white border-secondary" />
=======
                                    CssClass="form-control bg-dark text-white border-secondary" />
>>>>>>> 72e72ce (Ignorar archivos temporales de Visual Studio y build)
                            </div>
                            <div class="mb-3">
                                <label for="txtVersion" class="form-label">Versión</label>
                                <asp:TextBox ID="txtVersion" runat="server"
<<<<<<< HEAD
                                             CssClass="form-control bg-dark text-white border-secondary" />
=======
                                    CssClass="form-control bg-dark text-white border-secondary" />
>>>>>>> 72e72ce (Ignorar archivos temporales de Visual Studio y build)
                            </div>
                            <div class="mb-3">
                                <label for="txtTamano" class="form-label">Tamaño&nbsp;(MB)</label>
                                <asp:TextBox ID="txtTamano" runat="server" TextMode="Number"
<<<<<<< HEAD
                                             CssClass="form-control bg-dark text-white border-secondary" />
                            </div>
                            <asp:Label ID="lblError" runat="server"
                                       CssClass="text-danger" Visible="false" />
                            <div class="mb-3">
                                <label for="txtDescripcion" class="form-label">Descripción</label>
                                <asp:TextBox ID="txtDescripcion" runat="server"
                                             TextMode="MultiLine" Rows="3"
                                             CssClass="form-control bg-dark text-white border-secondary" />
=======
                                    CssClass="form-control bg-dark text-white border-secondary" />
                            </div>
                            <asp:Label ID="lblError" runat="server"
                                CssClass="text-danger" Visible="false" />
                            <div class="mb-3">
                                <label for="txtDescripcion" class="form-label">Descripción</label>
                                <asp:TextBox ID="txtDescripcion" runat="server"
                                    TextMode="MultiLine" Rows="3"
                                    CssClass="form-control bg-dark text-white border-secondary" />
>>>>>>> 72e72ce (Ignorar archivos temporales de Visual Studio y build)
                            </div>

                            <div class="mb-3">
                                <label for="txtDesarrollador" class="form-label">Desarrollador</label>
                                <asp:TextBox ID="txtDesarrollador" runat="server"
<<<<<<< HEAD
                                             CssClass="form-control bg-dark text-white border-secondary" />
                            </div>

                            
                                           
                            

                            <div class="mb-3">
                                <label for="ddlCategoria" class="form-label">Categoría</label>
                                <asp:DropDownList ID="ddlCategoria" runat="server"
                                                  CssClass="form-select bg-dark text-white border-secondary">
=======
                                    CssClass="form-control bg-dark text-white border-secondary" />
                            </div>

                            <div class="mb-3">
                                <label for="ddlCategoria" class="form-label">Categoría</label>
                                <asp:DropDownList ID="ddlCategoria" runat="server"
                                    CssClass="form-select bg-dark text-white border-secondary">
>>>>>>> 72e72ce (Ignorar archivos temporales de Visual Studio y build)
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
<<<<<<< HEAD
                                    data-bs-dismiss="modal">Cancelar</button>
                            <asp:Button ID="btnGuardar" runat="server" Text="Guardar"
                                        CssClass="btn btn-primary" OnClick="btnGuardar_Click" />
=======
                                data-bs-dismiss="modal">
                                Cancelar</button>
                            <asp:Button ID="btnGuardar" runat="server" Text="Guardar"
                                CssClass="btn btn-primary" OnClick="btnGuardar_Click" />
>>>>>>> 72e72ce (Ignorar archivos temporales de Visual Studio y build)
                        </div>
                    </ContentTemplate>
                </asp:UpdatePanel>
            </div>
        </div>
    </div>
<<<<<<< HEAD
=======
    <script>
        const modalDispositivo = document.getElementById('modalAplicacion');
        modalDispositivo.addEventListener('hidden.bs.modal', function () {
            document.getElementById('modalNuevaAppLabel').textContent = 'Nueva aplicación';
        });
    </script>
>>>>>>> 72e72ce (Ignorar archivos temporales de Visual Studio y build)
</asp:Content>
