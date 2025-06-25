package com.pucp.gestionlentesvr.ws;

import com.pucp.gestionlentesvr.dominio.dispositivo.Dispositivo;
import com.pucp.gestionlentesvr.dominio.dispositivo.Firmware;
import com.pucp.gestionlentesvr.negocio.dispositivo.DispositivoService;
import com.pucp.gestionlentesvr.negocio.dispositivo.DispositivoServiceImpl;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.xml.ws.WebServiceException;
import java.util.List;

@WebService(serviceName = "DispositivoWS")
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
    @WebMethod(operationName = "obtenerUltimoFirm")
    public Firmware obtenerUltimoFirm(@WebParam(name = "idDis") int idDis) throws Exception {

        try {
            return service.obtenerUltimoFirm(idDis);
        } catch (Exception ex) {
            throw new WebServiceException("Error al listar" + ex.getMessage());
        }
    }
    @WebMethod(operationName = "insertarDisFirmware")
    public boolean insertarDisFirmware(@WebParam(name = "idDis") int idDis,@WebParam(name = "idFirm") int idFirm) throws Exception {

        try {
            return service.insertarDisFirmware(idDis, idFirm);
        } catch (Exception ex) {
            throw new WebServiceException("Error al listar" + ex.getMessage());
        }
    }
}
