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
        service.registrarRol(elemento);
    }

    @WebMethod(operationName = "actualizarRol")
    public void actualizarRol(@WebParam(name = "elemento") Rol elemento) throws Exception {
        service.actualizarRol(elemento);
    }
    
    @WebMethod(operationName = "listarRol")
    public List<Rol> listarRol() {
        try {
            return service.listarRol();
        } catch (Exception ex) {
            throw new WebServiceException("Error al listar" + ex.getMessage());
        }
    }
}
