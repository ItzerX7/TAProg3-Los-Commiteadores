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
    @WebMethod(operationName = "listarRol")
    public List<Grupo> listarGrupo() {
        try {
            return service.listarGrupo();
        } catch (Exception ex) {
            throw new WebServiceException("Error al listar" + ex.getMessage());
        }
    }
}
