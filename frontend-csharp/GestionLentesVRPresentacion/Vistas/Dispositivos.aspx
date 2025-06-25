<%@ Page Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true"
    CodeBehind="Dispositivos.aspx.cs" Inherits="FrontVR.Vistas.Dispositivos" %>

<asp:Content ID="Main" ContentPlaceHolderID="MainContent" runat="server">

    <h2 class="page-title mb-3">Gestión de Dispositivos</h2>

    <button type="button" class="btn btn-primary mb-3" data-bs-toggle="modal" data-bs-target="#modalDispositivo">
        <i class="fa fa-plus"></i>Nuevo dispositivo
    </button>

    <!-- Campos de búsqueda -->
    <div class="row mb-3">
        <div class="col-md-3">
            <input type="text" id="searchNombre" class="form-control" placeholder="Buscar por nombre" onkeyup="filtrarTabla()" />
        </div>
        <div class="col-md-3">
            <input type="text" id="searchModelo" class="form-control" placeholder="Buscar por modelo" onkeyup="filtrarTabla()" />
        </div>
        <div class="col-md-3">
            <input type="text" id="searchUbicacion" class="form-control" placeholder="Buscar por ubicación" onkeyup="filtrarTabla()" />
        </div>
        <div class="col-md-3">
            <select id="searchEstado" class="form-select" onchange="filtrarTabla()">
                <option value="">Todos los estados</option>
                <option value="CONECTADO">CONECTADO</option>
                <option value="DESCONECTADO">DESCONECTADO</option>
                <option value="EN_USO">EN_USO</option>
                <option value="EN_MANTENIMIENTO">EN_MANTENIMIENTO</option>
            </select>
        </div>
    </div>

    <!-- Tabla -->
    <asp:GridView ID="gvDispositivos" runat="server"
        AutoGenerateColumns="False" CssClass="table table-bordered table-hover align-middle text-center"
        DataKeyNames="id" OnRowCommand="gvDispositivos_RowCommand">
        <HeaderStyle CssClass="table-dark" />
        <Columns>
            <asp:BoundField DataField="nombre" HeaderText="Nombre" />
            <asp:BoundField DataField="modelo" HeaderText="Modelo" />
            <asp:BoundField DataField="numeroSerie" HeaderText="Serie" />
            <asp:BoundField DataField="fechaRegistro" HeaderText="Fecha" DataFormatString="{0:yyyy-MM-dd HH:mm:ss}" />
            <asp:BoundField DataField="ubicacion" HeaderText="Ubicación" />
            <asp:BoundField DataField="estado" HeaderText="Estado" />
            <asp:BoundField DataField="ultimaConexion" HeaderText="Última Conexión" DataFormatString="{0:yyyy-MM-dd HH:mm:ss}" />
            <asp:BoundField DataField="nivelBateria" HeaderText="Nivel de Batería (%)" />
            <asp:TemplateField HeaderText="Acción">
                <ItemTemplate>
                    <div class="d-flex justify-content-center gap-2">
                        <asp:LinkButton ID="lnkEditar" runat="server"
                            CommandName="EditarDispositivo"
                            CommandArgument='<%# Eval("id") %>'
                            Text="Editar"
                            CssClass="btn btn-sm btn-warning" />
                        <asp:LinkButton ID="lnkEliminar" runat="server"
                            CommandName="Eliminar"
                            CommandArgument='<%# Eval("id") %>'
                            Text="Eliminar"
                            CssClass="btn btn-sm btn-danger"
                            OnClientClick="return confirm('¿Está seguro de eliminar este dispositivo?');" />
                    </div>
                </ItemTemplate>
            </asp:TemplateField>
        </Columns>
    </asp:GridView>

    <!-- Modal NUEVO / EDITAR -->
    <div class="modal fade" id="modalDispositivo" tabindex="-1" aria-labelledby="modalDispositivoLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content bg-dark text-white">
                <div class="modal-header border-secondary">
                    <h5 class="modal-title" id="modalDispositivoLabel">Nuevo dispositivo</h5>
                    <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                </div>
                <asp:UpdatePanel ID="upModalDispositivo" runat="server">
                    <ContentTemplate>
                        <div class="modal-body">
                            <asp:HiddenField ID="hfIdDispositivo" runat="server" />

                            <div class="mb-3">
                                <label for="txtNombre" class="form-label">Nombre <span class="text-danger">*</span></label>
                                <asp:TextBox ID="txtNombre" runat="server" CssClass="form-control" />
                            </div>

                            <div class="mb-3">
                                <label for="txtModelo" class="form-label">Modelo <span class="text-danger">*</span></label>
                                <asp:TextBox ID="txtModelo" runat="server" CssClass="form-control" />
                            </div>

                            <div class="mb-3">
                                <label for="txtSerie" class="form-label">Número de Serie <span class="text-danger">*</span></label>
                                <asp:TextBox ID="txtSerie" runat="server" CssClass="form-control" />
                            </div>

                            <div class="mb-3">
                                <label for="txtUbicacion" class="form-label">Ubicación <span class="text-danger">*</span></label>
                                <asp:TextBox ID="txtUbicacion" runat="server" CssClass="form-control" />
                            </div>

                            <div class="mb-3">
                                <label for="txtFecha" class="form-label">Ultima Conexion</label>
                                <asp:TextBox ID="txtFecha" runat="server" TextMode="Date" CssClass="form-control" />
                            </div>

                            <div class="mb-3">
                                <label for="ddlGrupo" class="form-label">Grupo <span class="text-danger">*</span></label>
                                <asp:DropDownList ID="ddlGrupo" runat="server" CssClass="form-select bg-dark text-white border-secondary" />
                            </div>

                            <div class="mb-3">
                                <label for="ddlEstado" class="form-label">Estado de conexión <span class="text-danger">*</span></label>
                                <asp:DropDownList ID="ddlEstado" runat="server" CssClass="form-select bg-dark text-white border-secondary">
                                    <asp:ListItem Text="Seleccione..." Value="" />
                                    <asp:ListItem Text="CONECTADO" Value="CONECTADO" />
                                    <asp:ListItem Text="DESCONECTADO" Value="DESCONECTADO" />
                                    <asp:ListItem Text="EN_USO" Value="EN_USO" />
                                    <asp:ListItem Text="EN_MANTENIMIENTO" Value="EN_MANTENIMIENTO" />
                                </asp:DropDownList>
                            </div>

                            <asp:Label ID="lblError" runat="server" CssClass="text-danger" Visible="false" />
                        </div>

                        <div class="modal-footer border-secondary">
                            <button type="button" class="btn btn-outline-light" data-bs-dismiss="modal">Cancelar</button>
                            <asp:Button ID="btnGuardar" runat="server" Text="Guardar" CssClass="btn btn-primary" UseSubmitBehavior="false" OnClick="btnGuardar_Click" />
                        </div>
                    </ContentTemplate>
                </asp:UpdatePanel>
            </div>
        </div>
    </div>

    <!-- Scripts -->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script>
        function filtrarTabla() {
            var inputNombre = document.getElementById("searchNombre").value.toLowerCase();
            var inputModelo = document.getElementById("searchModelo").value.toLowerCase();
            var inputUbicacion = document.getElementById("searchUbicacion").value.toLowerCase();
            var inputEstado = document.getElementById("searchEstado").value.toLowerCase();
            var tabla = document.getElementById("<%= gvDispositivos.ClientID %>");
            var filas = tabla.getElementsByTagName("tr");

            for (var i = 1; i < filas.length; i++) {
                var celdas = filas[i].getElementsByTagName("td");
                if (celdas.length > 0) {
                    var nombre = celdas[0].textContent.toLowerCase();
                    var modelo = celdas[1].textContent.toLowerCase();
                    var ubicacion = celdas[4].textContent.toLowerCase();
                    var estado = celdas[5].textContent.toLowerCase();

                    var coincideNombre = nombre.includes(inputNombre);
                    var coincideModelo = modelo.includes(inputModelo);
                    var coincideUbicacion = ubicacion.includes(inputUbicacion);
                    var coincideEstado = inputEstado === "" || estado === inputEstado;

                    filas[i].style.display = (coincideNombre && coincideModelo && coincideUbicacion && coincideEstado) ? "" : "none";
                }
            }
        }

        document.getElementById('modalDispositivo')?.addEventListener('hidden.bs.modal', function () {
            document.getElementById('modalDispositivoLabel').textContent = 'Nuevo dispositivo';
        });
    </script>

</asp:Content>
