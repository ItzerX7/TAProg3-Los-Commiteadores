package com.pucp.gestionlentesvr.ws;

import com.pucp.gestionlentesvr.dominio.actividad.Actividad;
import com.pucp.gestionlentesvr.negocio.actividad.ActividadService;
import com.pucp.gestionlentesvr.negocio.actividad.ActividadServiceImpl;
import com.pucp.gestionlentesvr.persistencia.DBManager;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.xml.ws.WebServiceException;
import java.io.File;
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
import java.net.URL;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;

@WebService(serviceName = "ActividadWS")
public class ActividadWS {

    private final ActividadService service;

    public ActividadWS() {
        this.service = new ActividadServiceImpl();
    }

    @WebMethod(operationName = "registrarActividad")
    public void registrarActividad(@WebParam(name = "actividad") Actividad actividad) throws Exception {

        try {
            service.registrarActividad(actividad);
        } catch (Exception ex) {
            throw new WebServiceException("Error al registrar" + ex.getMessage());
        }
    }

    @WebMethod(operationName = "actualizarActividad")
    public void actualizarActividad(@WebParam(name = "actividad") Actividad actividad) throws Exception {

        try {
            service.actualizarActividad(actividad);
        } catch (Exception ex) {
            throw new WebServiceException("Error al actualizar" + ex.getMessage());
        }
    }

    @WebMethod(operationName = "eliminarActividad")
    public void eliminarActividad(@WebParam(name = "id") int id) throws Exception {

        try {
            service.eliminarActividad(id);
        } catch (Exception ex) {
            throw new WebServiceException("Error al eliminar" + ex.getMessage());
        }
    }

    @WebMethod(operationName = "obtenerActividad")
    public Actividad obtenerActividad(@WebParam(name = "id") int id) throws Exception {

        try {
            return service.obtenerActividad(id);
        } catch (Exception ex) {
            throw new WebServiceException("Error al obtener" + ex.getMessage());
        }
    }

    @WebMethod(operationName = "listarActividad")
    public List<Actividad> listarActividad() throws Exception {

        try {
            return service.listarActividad();
        } catch (Exception ex) {
            throw new WebServiceException("Error al listar" + ex.getMessage());
        }
    }

    @WebMethod(operationName = "reporteActividades")
    public byte[] reporteActividades() {
        try {
            Map<String, Object> params = new HashMap<>();
            String fileXML = getFileResource("Leaf_Green.jrxml");

            return generarBufferFromJP(fileXML, params);

        } catch (Exception ex) {
            throw new WebServiceException("Error al generar el reporte: " + ex.getMessage(), ex);
        }
    }
        @WebMethod(operationName = "reporteTipos")
    public byte[] reporteTipos() {
        try {
            Map<String, Object> params = new HashMap<>();
            String fileXML = getFileResource("GraficoCircularTipoAct.jrxml");

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
