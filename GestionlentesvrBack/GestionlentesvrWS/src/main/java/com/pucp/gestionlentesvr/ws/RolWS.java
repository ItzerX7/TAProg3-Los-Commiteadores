package com.pucp.gestionlentesvr.ws;

import com.pucp.gestionlentesvr.dominio.usuario.Rol;
import com.pucp.gestionlentesvr.negocio.usuario.RolService;
import com.pucp.gestionlentesvr.negocio.usuario.RolServiceImpl;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.List;
/**
 *
 * @author oscar
 */
@WebService(serviceName = "RolWS", targetNamespace = "com.pucp.gestionlentesvr")
public class RolWS {


    private final RolService service;

    public RolWS() {
        this.service = new RolServiceImpl();
    }

    @WebMethod(operationName = "registrarRol")
    public void registrarRol(@WebParam(name = "elemento") Rol elemento) throws Exception {
        service.registrarRol(elemento);
    }

    @WebMethod(operationName = "actualizarRol")
    public void actualizarRol(@WebParam(name = "elemento") Rol elemento) throws Exception {
        service.actualizarRol(elemento);
    }
    
        
    @WebMethod(operationName = "eliminarRol")
    public void eliminarRol(@WebParam(name = "id") int id) throws Exception {
        service.eliminarRol(id);
    }

    @WebMethod(operationName = "obtenerRol")
    public Rol obtenerRol(@WebParam(name = "id") int id) throws Exception {
        return service.obtenerRol(id);
    }

    @WebMethod(operationName = "listarRol")
    public List<Rol> listarRol() throws Exception {
        return service.listarRol();
    }
}
