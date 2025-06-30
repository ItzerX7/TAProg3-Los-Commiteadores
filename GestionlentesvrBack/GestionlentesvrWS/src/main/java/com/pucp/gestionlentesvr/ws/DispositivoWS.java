package com.pucp.gestionlentesvr.ws;

import com.pucp.gestionlentesvr.dominio.dispositivo.Dispositivo;
import com.pucp.gestionlentesvr.dominio.dispositivo.Firmware;
import com.pucp.gestionlentesvr.negocio.dispositivo.DispositivoService;
import com.pucp.gestionlentesvr.negocio.dispositivo.DispositivoServiceImpl;
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
    public boolean insertarDisFirmware(@WebParam(name = "idDis") int idDis, @WebParam(name = "idFirm") int idFirm) throws Exception {

        try {
            return service.insertarDisFirmware(idDis, idFirm);
        } catch (Exception ex) {
            throw new WebServiceException("Error al listar" + ex.getMessage());
        }
    }

    @WebMethod(operationName = "reporteDispositivos")
    public byte[] reporteDispositivos(@WebParam(name = "idGrupo") int idGrupo) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("InputGroupID", idGrupo);
            String fileXML = getFileResource("ReporteDispositivos.jrxml");

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
