/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package com.pucp.gestionlentesvr.ws;

import com.pucp.gestionlentesvr.dominio.dispositivo.Grupo;
import com.pucp.gestionlentesvr.negocio.dispositivo.GrupoService;
import com.pucp.gestionlentesvr.negocio.dispositivo.GrupoServiceImpl;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.xml.ws.WebServiceException;
import java.util.List;

/**
 *
 * @author Itzer
 */
@WebService(serviceName = "GrupoWS")
public class GrupoWS {

    private final GrupoService service;

    public GrupoWS() {
        this.service = new GrupoServiceImpl();
    }

    @WebMethod(operationName = "registrarGrupo")
    public void registrarGrupo(@WebParam(name = "elemento") Grupo elemento) throws Exception {

        try {
            service.registrarGrupo(elemento);
        } catch (Exception ex) {
            throw new WebServiceException("Error al registrar" + ex.getMessage());
        }
    }

    @WebMethod(operationName = "actualizarGrupo")
    public void actualizarGrupo(@WebParam(name = "elemento") Grupo elemento) throws Exception {

        try {
            service.actualizarGrupo(elemento);
        } catch (Exception ex) {
            throw new WebServiceException("Error al actualizar" + ex.getMessage());
        }
    }

    @WebMethod(operationName = "eliminarGrupo")
    public void eliminarGrupo(@WebParam(name = "id") int id) throws Exception {

        try {
            service.eliminarGrupo(id);
        } catch (Exception ex) {
            throw new WebServiceException("Error al eliminar" + ex.getMessage());
        }
    }

    @WebMethod(operationName = "obtenerGrupo")
    public Grupo obtenerGrupo(@WebParam(name = "id") int id) throws Exception {

        try {
            return service.obtenerGrupo(id);
        } catch (Exception ex) {
            throw new WebServiceException("Error al obtener" + ex.getMessage());
        }
    }

    @WebMethod(operationName = "listarRol")
    public List<Grupo> listarGrupo() {
        try {
            return service.listarGrupo();
        } catch (Exception ex) {
            throw new WebServiceException("Error al listar" + ex.getMessage());
        }
    }
}
