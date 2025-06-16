/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package com.pucp.gestionlentesvrws.Prueba;

import com.pucp.gestionlentesvr.dominio.dispositivo.Grupo;
import com.pucp.gestionlentesvr.persistencia.dao.dispositivo.GrupoDAO;
import com.pucp.gestionlentesvr.persistencia.dao.dispositivo.GrupoDAOImpl;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.ws.rs.Produces;

/**
 *
 * @author HP
 */
@WebService(serviceName = "PruebaWS")
public class PruebaWS {

    private final GrupoDAO pruebaDAO;

    public PruebaWS() {
        this.pruebaDAO = new GrupoDAOImpl();
    }
    
    @WebMethod(operationName = "insertar")
    public void hello() {
        try{
            Grupo entidad = new Grupo();
            entidad.setNombre("Grupo Generico");
            entidad.setDescripcion("Grupo para los dispositivos aun sin grupo asignado");
            entidad.setUbicacion("Indefinido");
            pruebaDAO.agregar(entidad);
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
