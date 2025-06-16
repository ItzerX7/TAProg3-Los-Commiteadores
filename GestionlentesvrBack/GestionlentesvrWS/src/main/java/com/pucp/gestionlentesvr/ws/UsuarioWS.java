package com.pucp.gestionlentesvr.ws;

import com.pucp.gestionlentesvr.dominio.usuario.Usuario;
import com.pucp.gestionlentesvr.negocio.usuario.UsuarioService;
import com.pucp.gestionlentesvr.negocio.usuario.UsuarioServiceImpl;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.List;

@WebService(serviceName = "UsuarioWS", targetNamespace = "com.pucp.gestionlentesvr")
public class UsuarioWS {

    private final UsuarioService service;

    public UsuarioWS() {
        this.service = new UsuarioServiceImpl();
    }

    @WebMethod(operationName = "registrarUsuario")
    public void registrarUsuario(@WebParam(name = "elemento") Usuario elemento) throws Exception {
        service.registrarUsuario(elemento);
    }

    @WebMethod(operationName = "actualizarUsuario")
    public void actualizarUsuario(@WebParam(name = "elemento") Usuario elemento) throws Exception {
        service.actualizarUsuario(elemento);
    }

    @WebMethod(operationName = "eliminarUsuario")
    public void eliminarUsuario(@WebParam(name = "id") int id) throws Exception {
        service.eliminarUsuario(id);
    }

    @WebMethod(operationName = "obtenerUsuario")
    public Usuario obtenerUsuario(@WebParam(name = "id") int id) throws Exception {
        return service.obtenerUsuario(id);
    }

    @WebMethod(operationName = "listarUsuario")
    public List<Usuario> listarUsuario() throws Exception {
        return service.listarUsuario();
    }
}
