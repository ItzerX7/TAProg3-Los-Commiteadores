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

/**
 *
 * @author HP
 */
@WebService(serviceName = "Prueba")
public class Prueba {

    public final GrupoDAO pruebaDAO; 

    public Prueba() {
        pruebaDAO= new GrupoDAOImpl();
    }
    
    
    @WebMethod(operationName = "hello")
    public void hello() {
        Grupo grup = new Grupo();
        grup.setNombre("Sin grupo");
        grup.setDescripcion("Grupo para los dispositivos sin grupo especifico");
        grup.setUbicacion("Sin ubicacion");
        pruebaDAO.agregar(grup);
    }
}
