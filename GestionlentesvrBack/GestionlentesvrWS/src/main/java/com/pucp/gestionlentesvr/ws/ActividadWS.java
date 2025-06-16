package com.pucp.gestionlentesvr.ws;

import com.pucp.gestionlentesvr.dominio.actividad.Actividad;
import com.pucp.gestionlentesvr.negocio.actividad.ActividadService;
import com.pucp.gestionlentesvr.negocio.actividad.ActividadServiceImpl;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.List;

@WebService(serviceName = "ActividadWS", targetNamespace = "com.pucp.gestionlentesvr")
public class ActividadWS {

    private final ActividadService service;

    public ActividadWS() {
        this.service = new ActividadServiceImpl();
    }

    @WebMethod(operationName = "registrarActividad")
    public void registrarActividad(@WebParam(name = "actividad") Actividad actividad) throws Exception {
        service.registrarActividad(actividad);
    }

    @WebMethod(operationName = "actualizarActividad")
    public void actualizarActividad(@WebParam(name = "actividad") Actividad actividad) throws Exception {
        service.actualizarActividad(actividad);
    }

    @WebMethod(operationName = "eliminarActividad")
    public void eliminarActividad(@WebParam(name = "id") int id) throws Exception {
        service.eliminarActividad(id);
    }

    @WebMethod(operationName = "obtenerActividad")
    public Actividad obtenerActividad(@WebParam(name = "id") int id) throws Exception {
        return service.obtenerActividad(id);
    }

    @WebMethod(operationName = "listarActividad")
    public List<Actividad> listarActividad() throws Exception {
        return service.listarActividad();
    }
}
