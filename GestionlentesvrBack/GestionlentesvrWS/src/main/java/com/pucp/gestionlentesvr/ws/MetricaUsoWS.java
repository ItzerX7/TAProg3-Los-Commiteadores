package com.pucp.gestionlentesvr.ws;

import com.pucp.gestionlentesvr.dominio.usuario.MetricaUso;
import com.pucp.gestionlentesvr.negocio.usuario.MetricaUsoService;
import com.pucp.gestionlentesvr.negocio.usuario.MetricaUsoServiceImpl;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.xml.ws.WebServiceException;
import java.util.List;

@WebService(serviceName = "MetricaUsoWS")
public class MetricaUsoWS {

    private final MetricaUsoService service;

    public MetricaUsoWS() {
        this.service = new MetricaUsoServiceImpl();
    }

    @WebMethod(operationName = "registrarMetricaUso")
    public void registrarMetricaUso(@WebParam(name = "elemento") MetricaUso elemento) throws Exception {

        try {
            service.registrarMetricaUso(elemento);
        } catch (Exception ex) {
            throw new WebServiceException("Error al registrar" + ex.getMessage());
        }
    }

    @WebMethod(operationName = "actualizarMetricaUso")
    public void actualizarMetricaUso(@WebParam(name = "elemento") MetricaUso elemento) throws Exception {

        try {
            service.actualizarMetricaUso(elemento);
        } catch (Exception ex) {
            throw new WebServiceException("Error al actualizar" + ex.getMessage());
        }
    }

    @WebMethod(operationName = "eliminarMetricaUso")
    public void eliminarMetricaUso(@WebParam(name = "id") int id) throws Exception {

        try {
            service.eliminarMetricaUso(id);
        } catch (Exception ex) {
            throw new WebServiceException("Error al eliminar" + ex.getMessage());
        }
    }

    @WebMethod(operationName = "obtenerMetricaUso")
    public MetricaUso obtenerMetricaUso(@WebParam(name = "id") int id) throws Exception {

        try {
            return service.obtenerMetricaUso(id);
        } catch (Exception ex) {
            throw new WebServiceException("Error al obtener" + ex.getMessage());
        }
    }

    @WebMethod(operationName = "listarMetricaUso")
    public List<MetricaUso> listarMetricaUso() throws Exception {

        try {
            return service.listarMetricaUso();
        } catch (Exception ex) {
            throw new WebServiceException("Error al listar" + ex.getMessage());
        }
    }
}
