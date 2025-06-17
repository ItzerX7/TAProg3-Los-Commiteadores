/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package com.pucp.gestionlentesvr.ws;

import com.pucp.gestionlentesvr.dominio.dispositivo.Firmware;
import com.pucp.gestionlentesvr.negocio.dispositivo.FirmwareService;
import com.pucp.gestionlentesvr.negocio.dispositivo.FirmwareServiceImpl;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.xml.ws.WebServiceException;
import java.util.List;

/**
 *
 * @author oscar
 */
@WebService(serviceName = "FirmwareWS", targetNamespace = "com.pucp.gestionlentesvr")
public class FirmwareWS {

    private final FirmwareService service;

    public FirmwareWS() {
        this.service = new FirmwareServiceImpl();
    }

    @WebMethod(operationName = "registrarFirmware")
    public void registrarFirmware(@WebParam(name = "elemento") Firmware elemento) throws Exception {

        try {
            service.registrarFirmware(elemento);
        } catch (Exception ex) {
            throw new WebServiceException("Error al registrar" + ex.getMessage());
        }
    }

    @WebMethod(operationName = "actualizarFirmware")
    public void actualizarFirmware(@WebParam(name = "elemento") Firmware elemento) throws Exception {

        try {
            service.actualizarFirmware(elemento);
        } catch (Exception ex) {
            throw new WebServiceException("Error al actualizar" + ex.getMessage());
        }
    }

    @WebMethod(operationName = "eliminarFirmware")
    public void eliminarFirmware(@WebParam(name = "id") int id) throws Exception {

        try {
            service.eliminarFirmware(id);
        } catch (Exception ex) {
            throw new WebServiceException("Error al eliminar" + ex.getMessage());
        }
    }

    @WebMethod(operationName = "obtenerFirmware")
    public Firmware obtenerFirmware(@WebParam(name = "id") int id) throws Exception {

        try {
            return service.obtenerFirmware(id);
        } catch (Exception ex) {
            throw new WebServiceException("Error al obtener" + ex.getMessage());
        }
    }

    @WebMethod(operationName = "listarFirmware")
    public List<Firmware> listarFirmware() throws Exception {

        try {
            return service.listarFirmware();
        } catch (Exception ex) {
            throw new WebServiceException("Error al listar" + ex.getMessage());
        }
    }
}
