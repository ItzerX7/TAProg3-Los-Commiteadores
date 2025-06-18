<%@ Page Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true"
         CodeBehind="Grupos.aspx.cs" Inherits="FrontVR.Vistas.Grupo" %>

<asp:Content ID="MainContent" ContentPlaceHolderID="MainContent" runat="server">
    <h2 class="page-title mb-3">Gestión de Grupos</h2>

    <!-- Botón para abrir modal -->
    <button type="button" class="btn btn-primary mb-3" data-bs-toggle="modal" data-bs-target="#modalGrupo">
        <i class="fa fa-plus"></i> Nuevo grupo
    </button>

    <!-- Buscadores -->
    <div class="row mb-3">
        <div class="col-md-4">
            <input type="text" id="searchNombre" class="form-control" placeholder="Buscar por nombre" onkeyup="filtrarTabla()" />
        </div>
        <div class="col-md-4">
            <input type="text" id="searchDescripcion" class="form-control" placeholder="Buscar por descripción" onkeyup="filtrarTabla()" />
        </div>
    </div>

    <!-- Tabla de grupos -->
    <asp:GridView ID="gvGrupos" runat="server"
                  AutoGenerateColumns="False"
                  CssClass="table table-bordered table-hover align-middle text-center"
                  DataKeyNames="id"
                  OnRowCommand="gvGrupos_RowCommand">
        <HeaderStyle CssClass="table-dark" />
        <Columns>
            <asp:BoundField DataField="nombre" HeaderText="Nombre" />
            <asp:BoundField DataField="descripcion" HeaderText="Descripción" />
            <asp:BoundField DataField="fechaCreacion" HeaderText="Fecha" DataFormatString="{0:yyyy-MM-dd}" />
            <asp:TemplateField HeaderText="Acción">
                <ItemTemplate>
                    <div class="d-flex justify-content-center gap-2">
                        <asp:LinkButton ID="btnEditar" runat="server" Text="Editar"
                                        CommandName="EditarGrupo"
                                        CommandArgument='<%# Eval("id") %>'
                                        CssClass="btn btn-sm btn-warning" />
                        <asp:LinkButton ID="btnEliminar" runat="server" Text="Eliminar"
                                        CommandName="EliminarGrupo"
                                        CommandArgument='<%# Eval("id") %>'
                                        CssClass="btn btn-sm btn-danger" />
                    </div>
                </ItemTemplate>
            </asp:TemplateField>
        </Columns>
    </asp:GridView>

    <!-- MODAL -->
    <div class="modal fade" id="modalGrupo" tabindex="-1" aria-labelledby="modalGrupoLabel" aria-hidden="true" data-bs-theme="dark">
        <div class="modal-dialog">
            <div class="modal-content bg-dark text-white">
                <div class="modal-header border-secondary">
                    <h5 class="modal-title" id="modalGrupoLabel">Nuevo grupo</h5>
                    <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal"></button>
                </div>

                <asp:UpdatePanel ID="upModalGrupo" runat="server">
                    <ContentTemplate>
                        <div class="modal-body">
                            <asp:HiddenField ID="hfIdGrupo" runat="server" />

                            <div class="mb-3">
                                <label for="txtNombreGrupo" class="form-label">Nombre</label>
                                <asp:TextBox ID="txtNombreGrupo" runat="server"
                                             CssClass="form-control bg-dark text-white border-secondary" />
                            </div>

                            <div class="mb-3">
                                <label for="txtDescripcionGrupo" class="form-label">Descripción</label>
                                <asp:TextBox ID="txtDescripcionGrupo" runat="server"
                                             CssClass="form-control bg-dark text-white border-secondary" />
                            </div>

                            <asp:Label ID="lblErrorGrupo" runat="server" CssClass="text-danger" Visible="false" />
                        </div>

                        <div class="modal-footer border-secondary">
                            <button type="button" class="btn btn-outline-light" data-bs-dismiss="modal">Cancelar</button>
                            <asp:Button ID="btnGuardarGrupo" runat="server" Text="Guardar"
                                        CssClass="btn btn-primary"
                                        OnClick="btnGuardarGrupo_Click" />
                        </div>
                    </ContentTemplate>
                </asp:UpdatePanel>
            </div>
        </div>
    </div>

    <!-- Script buscador -->
    <script>
        function filtrarTabla() {
            var inputNombre = document.getElementById("searchNombre").value.toLowerCase();
            var inputDesc = document.getElementById("searchDescripcion").value.toLowerCase();
            var tabla = document.getElementById("<%= gvGrupos.ClientID %>");
            var filas = tabla.getElementsByTagName("tr");

            for (var i = 1; i < filas.length; i++) {
                var celdas = filas[i].getElementsByTagName("td");
                if (celdas.length > 0) {
                    var nombre = celdas[0].textContent.toLowerCase();
                    var descripcion = celdas[1].textContent.toLowerCase();

                    var visible = nombre.includes(inputNombre) && descripcion.includes(inputDesc);
                    filas[i].style.display = visible ? "" : "none";
                }
            }
        }

        const modalGrupo = document.getElementById('modalGrupo');
        modalGrupo.addEventListener('hidden.bs.modal', function () {
            document.getElementById('modalGrupoLabel').textContent = 'Nuevo grupo';
        });
    </script>
</asp:Content>
