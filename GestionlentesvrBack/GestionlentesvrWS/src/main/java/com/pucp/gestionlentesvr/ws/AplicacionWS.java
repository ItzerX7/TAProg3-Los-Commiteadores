package com.pucp.gestionlentesvr.ws;

import com.pucp.gestionlentesvr.dominio.aplicacion.Aplicacion;
import com.pucp.gestionlentesvr.negocio.aplicacion.AplicacionService;
import com.pucp.gestionlentesvr.negocio.aplicacion.AplicacionServiceImpl;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.xml.ws.WebServiceException;
import java.util.List;

@WebService(serviceName = "AplicacionWS")
public class AplicacionWS {

    private final AplicacionService service;

    public AplicacionWS() {
        this.service = new AplicacionServiceImpl();
    }

    @WebMethod(operationName = "registrarAplicacion")
    public void registrarAplicacion(@WebParam(name = "elemento") Aplicacion elemento) throws Exception {
        try {
            service.registrarAplicacion(elemento);
        } catch (Exception ex) {
            throw new WebServiceException("Error al registrar" + ex.getMessage());
        }
    }

    @WebMethod(operationName = "actualizarAplicacion")
    public void actualizarAplicacion(@WebParam(name = "elemento") Aplicacion elemento) throws Exception {

        try {
            service.actualizarAplicacion(elemento);
        } catch (Exception ex) {
            throw new WebServiceException("Error al actualizar" + ex.getMessage());
        }
    }

    @WebMethod(operationName = "eliminarAplicacion")
    public void eliminarAplicacion(@WebParam(name = "id") int id) throws Exception {

        try {
            service.eliminarAplicacion(id);
        } catch (Exception ex) {
            throw new WebServiceException("Error al eliminar" + ex.getMessage());
        }
    }

    @WebMethod(operationName = "obtenerAplicacion")
    public Aplicacion obtenerAplicacion(@WebParam(name = "id") int id) throws Exception {

        try {
            return service.obtenerAplicacion(id);
        } catch (Exception ex) {
            throw new WebServiceException("Error al obtener" + ex.getMessage());
        }
    }
    @WebMethod(operationName = "listarAplicacionesConDispositivos")
    public List<Aplicacion> listarAplicacionesConDispositivos(@WebParam(name = "id") int id) throws Exception {

        try {
            return service.listarAplicacionesConDispositivos(id);
        } catch (Exception ex) {
            throw new WebServiceException("Error al obtener" + ex.getMessage());
        }
    }

    @WebMethod(operationName = "listarAplicacion")
    public List<Aplicacion> listarAplicacion() throws Exception {

        try {
            return service.listarAplicacion();
        } catch (Exception ex) {
            throw new WebServiceException("Error al listar" + ex.getMessage());
        }
    }
    
    @WebMethod(operationName = "eliminarAplicacionesPorDispositivo")
    public void eliminarAplicacionesPorDispositivo() throws Exception {
        try {
            service.eliminarAplicacionesPorDispositivo();
        } catch (Exception ex) {
            throw new WebServiceException("Error al listar" + ex.getMessage());
        }
    }
}
