package com.pucp.gestionlentesvr.ws;

import com.pucp.gestionlentesvr.dominio.actividad.Actividad;
import com.pucp.gestionlentesvr.negocio.actividad.ActividadService;
import com.pucp.gestionlentesvr.negocio.actividad.ActividadServiceImpl;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.xml.ws.WebServiceException;
import java.util.List;

@WebService(serviceName = "ActividadWS")
public class ActividadWS {

    private final ActividadService service;

    public ActividadWS() {
        this.service = new ActividadServiceImpl();
    }

    @WebMethod(operationName = "registrarActividad")
    public void registrarActividad(@WebParam(name = "actividad") Actividad actividad) throws Exception {

        try {
            service.registrarActividad(actividad);
        } catch (Exception ex) {
            throw new WebServiceException("Error al registrar" + ex.getMessage());
        }
    }

    @WebMethod(operationName = "actualizarActividad")
    public void actualizarActividad(@WebParam(name = "actividad") Actividad actividad) throws Exception {

        try {
            service.actualizarActividad(actividad);
        } catch (Exception ex) {
            throw new WebServiceException("Error al actualizar" + ex.getMessage());
        }
    }

    @WebMethod(operationName = "eliminarActividad")
    public void eliminarActividad(@WebParam(name = "id") int id) throws Exception {

        try {
            service.eliminarActividad(id);
        } catch (Exception ex) {
            throw new WebServiceException("Error al eliminar" + ex.getMessage());
        }
    }

    @WebMethod(operationName = "obtenerActividad")
    public Actividad obtenerActividad(@WebParam(name = "id") int id) throws Exception {

        try {
            return service.obtenerActividad(id);
        } catch (Exception ex) {
            throw new WebServiceException("Error al obtener" + ex.getMessage());
        }
    }

    @WebMethod(operationName = "listarActividad")
    public List<Actividad> listarActividad() throws Exception {

        try {
            return service.listarActividad();
        } catch (Exception ex) {
            throw new WebServiceException("Error al listar" + ex.getMessage());
        }
    }
}
