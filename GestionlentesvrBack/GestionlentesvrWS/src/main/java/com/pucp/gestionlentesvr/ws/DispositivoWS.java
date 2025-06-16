/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package com.pucp.gestionlentesvr.ws;

import com.pucp.gestionlentesvr.dominio.dispositivo.Dispositivo;
import com.pucp.gestionlentesvr.negocio.dispositivo.DispositivoService;
import com.pucp.gestionlentesvr.negocio.dispositivo.DispositivoServiceImpl;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.xml.ws.WebServiceException;
import java.util.List;

/**
 *
 * @author oscar
 */
@WebService(serviceName = "DispositivoWS", targetNamespace = "com.pucp.gestionlentesvr")
public class DispositivoWS {

    private final DispositivoService service;

    public DispositivoWS() {
        this.service = new DispositivoServiceImpl();
    }

    @WebMethod(operationName = "registrarDispositivo")
    public void registrarDispositivo(@WebParam(name = "elemento") Dispositivo elemento) throws Exception {

        try {
            service.registrarDispositivo(elemento);
        } catch (Exception ex) {
            throw new WebServiceException("Error al registrar" + ex.getMessage());
        }
    }

    @WebMethod(operationName = "actualizarDispositivo")
    public void actualizarDispositivo(@WebParam(name = "elemento") Dispositivo elemento) throws Exception {

        try {
            service.actualizarDispositivo(elemento);
        } catch (Exception ex) {
            throw new WebServiceException("Error al actualizar" + ex.getMessage());
        }
    }

    @WebMethod(operationName = "eliminarDispositivo")
    public void eliminarDispositivo(@WebParam(name = "id") int id) throws Exception {

        try {
            service.eliminarDispositivo(id);
        } catch (Exception ex) {
            throw new WebServiceException("Error al eliminar" + ex.getMessage());
        }
    }

    @WebMethod(operationName = "obtenerDispositivo")
    public Dispositivo obtenerDispositivo(@WebParam(name = "id") int id) throws Exception {

        try {
            return service.obtenerDispositivo(id);
        } catch (Exception ex) {
            throw new WebServiceException("Error al obtener" + ex.getMessage());
        }
    }

    @WebMethod(operationName = "listarDispositivo")
    public List<Dispositivo> listarDispositivo() throws Exception {

        try {
            return service.listarDispositivo();
        } catch (Exception ex) {
            throw new WebServiceException("Error al listar" + ex.getMessage());
        }
    }
}
