package com.pucp.gestionlentesvr.ws;

import com.pucp.gestionlentesvr.dominio.usuario.Rol;
import com.pucp.gestionlentesvr.negocio.usuario.RolService;
import com.pucp.gestionlentesvr.negocio.usuario.RolServiceImpl;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.xml.ws.WebServiceException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebService(serviceName = "RolWS")
public class RolWS {

    private final RolService service;

    public RolWS() {
        this.service = new RolServiceImpl();
    }

    @WebMethod(operationName = "registrarRol")
    public void registrarRol(@WebParam(name = "elemento") Rol elemento) throws Exception {

        try {
            service.registrarRol(elemento);
        } catch (Exception ex) {
            throw new WebServiceException("Error al registrar" + ex.getMessage());
        }
    }

    @WebMethod(operationName = "actualizarRol")
    public void actualizarRol(@WebParam(name = "elemento") Rol elemento) throws Exception {

        try {
            service.actualizarRol(elemento);
        } catch (Exception ex) {
            throw new WebServiceException("Error al actualizar" + ex.getMessage());
        }
    }

    @WebMethod(operationName = "eliminarRol")
    public void eliminarRol(@WebParam(name = "id") int id) throws Exception {

        try {
            service.eliminarRol(id);
        } catch (Exception ex) {
            throw new WebServiceException("Error al eliminar" + ex.getMessage());
        }
    }

    @WebMethod(operationName = "obtenerRol")
    public Rol obtenerRol(@WebParam(name = "id") int id) throws Exception {

        try {
            return service.obtenerRol(id);
        } catch (Exception ex) {
            throw new WebServiceException("Error al obtener" + ex.getMessage());
        }
    }

    @WebMethod(operationName = "listarRol")
    public List<Rol> listarRol() throws Exception {

        try {
            return service.listarRol();
        } catch (Exception ex) {
            throw new WebServiceException("Error al listar" + ex.getMessage());
        }
    }
}
