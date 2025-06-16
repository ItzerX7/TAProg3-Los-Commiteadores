package com.pucp.gestionlentesvr.ws;

import com.pucp.gestionlentesvr.dominio.dispositivo.Dispositivo;
import com.pucp.gestionlentesvr.negocio.dispositivo.DispositivoService;
import com.pucp.gestionlentesvr.negocio.dispositivo.DispositivoServiceImpl;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.List;

@WebService(serviceName = "DispositivoWS", targetNamespace = "com.pucp.gestionlentesvr")
public class DispositivoWS {

    private final DispositivoService service;

    public DispositivoWS() {
        this.service = new DispositivoServiceImpl();
    }

    @WebMethod(operationName = "registrarDispositivo")
    public void registrarDispositivo(@WebParam(name = "elemento") Dispositivo elemento) throws Exception {
        service.registrarDispositivo(elemento);
    }

    @WebMethod(operationName = "actualizarDispositivo")
    public void actualizarDispositivo(@WebParam(name = "elemento") Dispositivo elemento) throws Exception {
        service.actualizarDispositivo(elemento);
    }

    @WebMethod(operationName = "eliminarDispositivo")
    public void eliminarDispositivo(@WebParam(name = "id") int id) throws Exception {
        service.eliminarDispositivo(id);
    }

    @WebMethod(operationName = "obtenerDispositivo")
    public Dispositivo obtenerDispositivo(@WebParam(name = "id") int id) throws Exception {
        return service.obtenerDispositivo(id);
    }

    @WebMethod(operationName = "listarDispositivo")
    public List<Dispositivo> listarDispositivo() throws Exception {
        return service.listarDispositivo();
    }
}
