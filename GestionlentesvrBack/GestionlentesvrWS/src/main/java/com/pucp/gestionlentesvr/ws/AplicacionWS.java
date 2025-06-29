package com.pucp.gestionlentesvr.ws;

import com.pucp.gestionlentesvr.dominio.aplicacion.Aplicacion;
import com.pucp.gestionlentesvr.dominio.dispositivo.Dispositivo;
import com.pucp.gestionlentesvr.negocio.aplicacion.AplicacionService;
import com.pucp.gestionlentesvr.negocio.aplicacion.AplicacionServiceImpl;
import com.pucp.gestionlentesvr.persistencia.DBManager;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.xml.ws.WebServiceException;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@WebService(serviceName = "AplicacionWS")
public class AplicacionWS {

    private final AplicacionService service;

    public AplicacionWS() {
        this.service = new AplicacionServiceImpl();
    }

    @WebMethod(operationName = "registrarAplicacion")
    public void registrarAplicacion(@WebParam(name = "elemento") Aplicacion elemento) throws Exception {
        try {
            service.registrarAplicacion(elemento);
        } catch (Exception ex) {
            throw new WebServiceException("Error al registrar" + ex.getMessage());
        }
    }

    @WebMethod(operationName = "actualizarAplicacion")
    public void actualizarAplicacion(@WebParam(name = "elemento") Aplicacion elemento) throws Exception {

        try {
            service.actualizarAplicacion(elemento);
        } catch (Exception ex) {
            throw new WebServiceException("Error al actualizar" + ex.getMessage());
        }
    }

    @WebMethod(operationName = "eliminarAplicacion")
    public void eliminarAplicacion(@WebParam(name = "id") int id) throws Exception {

        try {
            service.eliminarAplicacionesPorDispositivo();
            service.eliminarAplicacion(id);
        } catch (Exception ex) {
            throw new WebServiceException("Error al eliminar" + ex.getMessage());
        }
    }

    @WebMethod(operationName = "obtenerAplicacion")
    public Aplicacion obtenerAplicacion(@WebParam(name = "id") int id) throws Exception {

        try {
            return service.obtenerAplicacion(id);
        } catch (Exception ex) {
            throw new WebServiceException("Error al obtener" + ex.getMessage());
        }
    }

    @WebMethod(operationName = "listarAplicacionesConDispositivos")
    public List<Aplicacion> listarAplicacionesConDispositivos(@WebParam(name = "id") int id) throws Exception {

        try {
            return service.listarAplicacionesConDispositivos(id);
        } catch (Exception ex) {
            throw new WebServiceException("Error al obtener" + ex.getMessage());
        }
    }

    @WebMethod(operationName = "listarAplicacion")
    public List<Aplicacion> listarAplicacion() throws Exception {

        try {
            return service.listarAplicacion();
        } catch (Exception ex) {
            throw new WebServiceException("Error al listar" + ex.getMessage());
        }
    }

    @WebMethod(operationName = "listarDispositivosPorAplicaciones")
    public List<Dispositivo> listarDispositivosPorAplicaciones(@WebParam(name = "id") int id) throws Exception {

        try {
            return service.listarDispositivosPorAplicaciones(id);
        } catch (Exception ex) {
            throw new WebServiceException("Error al listar" + ex.getMessage());
        }
    }

    @WebMethod(operationName = "eliminarAplicacionesPorDispositivo")
    public void eliminarAplicacionesPorDispositivo() throws Exception {
        try {
            service.eliminarAplicacionesPorDispositivo();
        } catch (Exception ex) {
            throw new WebServiceException("Error al listar" + ex.getMessage());
        }
    }

    @WebMethod(operationName = "contarAplicacionesPorTipoEnMetricas")
    public List<Integer> contarAplicacionesPorTipoEnMetricas() throws Exception {
        try {
            return service.contarAplicacionesPorTipoEnMetricas();
        } catch (Exception ex) {
            throw new WebServiceException("Error al listar" + ex.getMessage());
        }
    }

    @WebMethod(operationName = "reporteCategoriaApp")
    public byte[] reporteCategoriaApp() {
        try {
            Map<String, Object> params = new HashMap<>();
            String fileXML = getFileResource("GraficoCircularTipoApp.jrxml");

            return generarBufferFromJP(fileXML, params);

        } catch (Exception ex) {
            throw new WebServiceException("Error al generar el reporte: " + ex.getMessage(), ex);
        }
    }

    private String getFileResource(String fileName) {
        URL resource = getClass().getClassLoader().getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("Archivo no encontrado: " + fileName);
        }
        try {
            URI uri = resource.toURI();
            return Paths.get(uri).toString();
        } catch (URISyntaxException e) {
            throw new RuntimeException("Error al convertir URL a URI", e);
        }
    }

    private byte[] generarBufferFromJP(String fileJRXML, Map<String, Object> params) throws JRException {
        // Forzar uso del compilador JDT si no está configurado
        System.setProperty("net.sf.jasperreports.compiler.class",
                "net.sf.jasperreports.engine.design.JRJdtCompiler");

        String fileJasper = fileJRXML.replace(".jrxml", ".jasper");

        if (!new File(fileJasper).exists()) {
            JasperCompileManager.compileReportToFile(fileJRXML, fileJasper);
        }

        JasperReport jr = (JasperReport) JRLoader.loadObjectFromFile(fileJasper);

        try (Connection conn = DBManager.getInstance().obtenerConexion()) {
            JasperPrint jp = JasperFillManager.fillReport(jr, params, conn);

            // Validar si el reporte tiene contenido antes de exportar
            if (jp.getPages() == null || jp.getPages().isEmpty()) {
                throw new JRException("El reporte fue generado pero no contiene páginas (no hay datos).");
            }

            return JasperExportManager.exportReportToPdf(jp);
        } catch (SQLException ex) {
            throw new JRException("Error obteniendo conexión a la BD: " + ex.getMessage(), ex);
        }
    }

}
