package com.pucp.gestionlentesvr.ws;

import com.pucp.gestionlentesvr.dominio.aplicacion.Aplicacion;
import com.pucp.gestionlentesvr.negocio.aplicacion.AplicacionService;
import com.pucp.gestionlentesvr.negocio.aplicacion.AplicacionServiceImpl;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.List;

@WebService(serviceName = "AplicacionWS", targetNamespace = "com.pucp.gestionlentesvr")
public class AplicacionWS {

    private final AplicacionService service;

    public AplicacionWS() {
        this.service = new AplicacionServiceImpl();
    }

    @WebMethod(operationName = "registrarAplicacion")
    public void registrarAplicacion(@WebParam(name = "elemento") Aplicacion elemento) throws Exception {
        service.registrarAplicacion(elemento);
    }

    @WebMethod(operationName = "actualizarAplicacion")
    public void actualizarAplicacion(@WebParam(name = "elemento") Aplicacion elemento) throws Exception {
        service.actualizarAplicacion(elemento);
    }

    @WebMethod(operationName = "eliminarAplicacion")
    public void eliminarAplicacion(@WebParam(name = "id") int id) throws Exception {
        service.eliminarAplicacion(id);
    }

    @WebMethod(operationName = "obtenerAplicacion")
    public Aplicacion obtenerAplicacion(@WebParam(name = "id") int id) throws Exception {
        return service.obtenerAplicacion(id);
    }

    @WebMethod(operationName = "listarAplicacion")
    public List<Aplicacion> listarAplicacion() throws Exception {
        return service.listarAplicacion();
    }
}
