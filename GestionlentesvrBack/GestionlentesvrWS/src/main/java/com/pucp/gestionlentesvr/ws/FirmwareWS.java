package com.pucp.gestionlentesvr.ws;

import com.pucp.gestionlentesvr.dominio.dispositivo.Firmware;
import com.pucp.gestionlentesvr.negocio.dispositivo.FirmwareService;
import com.pucp.gestionlentesvr.negocio.dispositivo.FirmwareServiceImpl;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.List;

@WebService(serviceName = "FirmwareWS", targetNamespace = "com.pucp.gestionlentesvr")
public class FirmwareWS {

    private final FirmwareService service;

    public FirmwareWS() {
        this.service = new FirmwareServiceImpl();
    }

    @WebMethod(operationName = "registrarFirmware")
    public void registrarFirmware(@WebParam(name = "elemento") Firmware elemento) throws Exception {
        service.registrarFirmware(elemento);
    }

    @WebMethod(operationName = "actualizarFirmware")
    public void actualizarFirmware(@WebParam(name = "elemento") Firmware elemento) throws Exception {
        service.actualizarFirmware(elemento);
    }

    @WebMethod(operationName = "eliminarFirmware")
    public void eliminarFirmware(@WebParam(name = "id") int id) throws Exception {
        service.eliminarFirmware(id);
    }

    @WebMethod(operationName = "obtenerFirmware")
    public Firmware obtenerFirmware(@WebParam(name = "id") int id) throws Exception {
        return service.obtenerFirmware(id);
    }

    @WebMethod(operationName = "listarFirmware")
    public List<Firmware> listarFirmware() throws Exception {
        return service.listarFirmware();
    }
}
