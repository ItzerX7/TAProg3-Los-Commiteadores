<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Login.aspx.cs" Inherits="FrontVR.Login" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>Iniciar sesión</title>
    <link href="Content/bootstrap.min.css" rel="stylesheet" />
</head>
<body class="bg-dark text-white">
    <form id="form1" runat="server" class="container mt-5">
        <div class="card bg-secondary p-4">
            <h2 class="mb-4">Login</h2>
            <div class="mb-3">
                <asp:Label ID="lblError" runat="server" CssClass="text-danger" Visible="false" />
                <asp:TextBox ID="txtUsuario" runat="server" CssClass="form-control mb-2" Placeholder="Usuario" />
                <asp:TextBox ID="txtPassword" runat="server" CssClass="form-control mb-3" TextMode="Password" Placeholder="Contraseña" />
                <asp:Button ID="btnLogin" runat="server" CssClass="btn btn-primary" Text="Entrar" OnClick="btnLogin_Click" />
            </div>
        </div>
    </form>
</body>
</html>
