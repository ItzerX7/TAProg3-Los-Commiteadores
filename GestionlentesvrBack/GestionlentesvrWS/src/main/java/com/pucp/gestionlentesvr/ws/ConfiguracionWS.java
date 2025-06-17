package com.pucp.gestionlentesvr.ws;

import com.pucp.gestionlentesvr.dominio.configuracion.Configuracion;
import com.pucp.gestionlentesvr.negocio.configuracion.ConfiguracionService;
import com.pucp.gestionlentesvr.negocio.configuracion.ConfiguracionServiceImpl;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.xml.ws.WebServiceException;
import java.util.List;

@WebService(serviceName = "ConfiguracionWS", targetNamespace = "com.pucp.gestionlentesvr")
public class ConfiguracionWS {

    private final ConfiguracionService service;

    public ConfiguracionWS() {
        this.service = new ConfiguracionServiceImpl();
    }

    @WebMethod(operationName = "registrarConfiguracion")
    public void registrarConfiguracion(@WebParam(name = "elemento") Configuracion elemento) throws Exception {

        try {
            service.registrarConfiguracion(elemento);
        } catch (Exception ex) {
            throw new WebServiceException("Error al registrar" + ex.getMessage());
        }
    }

    @WebMethod(operationName = "actualizarConfiguracion")
    public void actualizarConfiguracion(@WebParam(name = "elemento") Configuracion elemento) throws Exception {

        try {
            service.actualizarConfiguracion(elemento);
        } catch (Exception ex) {
            throw new WebServiceException("Error al actualizar" + ex.getMessage());
        }
    }

    @WebMethod(operationName = "eliminarConfiguracion")
    public void eliminarConfiguracion(@WebParam(name = "id") int id) throws Exception {

        try {
            service.eliminarConfiguracion(id);
        } catch (Exception ex) {
            throw new WebServiceException("Error al eliminar" + ex.getMessage());
        }
    }

    @WebMethod(operationName = "obtenerConfiguracion")
    public Configuracion obtenerConfiguracion(@WebParam(name = "id") int id) throws Exception {

        try {
            return service.obtenerConfiguracion(id);
        } catch (Exception ex) {
            throw new WebServiceException("Error al obtener" + ex.getMessage());
        }
    }

    @WebMethod(operationName = "listarConfiguracion")
    public List<Configuracion> listarConfiguracion() throws Exception {

        try {
            return service.listarConfiguracion();
        } catch (Exception ex) {
            throw new WebServiceException("Error al listar" + ex.getMessage());
        }
    }
}
