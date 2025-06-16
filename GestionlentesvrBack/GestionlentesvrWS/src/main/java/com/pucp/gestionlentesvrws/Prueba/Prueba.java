/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package com.pucp.gestionlentesvrws.Prueba;

import com.pucp.gestionlentesvr.dominio.dispositivo.Dispositivo;
import com.pucp.gestionlentesvr.dominio.dispositivo.EstadoConexion;
import com.pucp.gestionlentesvr.dominio.dispositivo.Grupo;
import com.pucp.gestionlentesvr.persistencia.dao.dispositivo.DispositivoDAO;
import com.pucp.gestionlentesvr.persistencia.dao.dispositivo.DispositivoDAOImpl;
import com.pucp.gestionlentesvr.persistencia.dao.dispositivo.GrupoDAO;
import com.pucp.gestionlentesvr.persistencia.dao.dispositivo.GrupoDAOImpl;
import com.pucp.gestionlentesvr.ws.GrupoWS;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.sql.Timestamp;

/**
 *
 * @author HP
 */
@WebService(serviceName = "Prueba")
public class Prueba {

    public final DispositivoDAO pruebaDAO; 

    public Prueba() {
        pruebaDAO= new DispositivoDAOImpl();
    }
    
    
    @WebMethod(operationName = "hello")
    public void hello() throws Exception {
        Dispositivo dispositivo = new Dispositivo();
        dispositivo.setEstado(EstadoConexion.CONECTADO);
        dispositivo.setUltimaConexion(new Timestamp(2022023005));
        dispositivo.setUbicacion("Sin ubicacion");
        dispositivo.setModelo("Nuevo modelo");
        dispositivo.setNivelBateria(100);
        dispositivo.setNumeroSerie("1254441");
        dispositivo.setNombre("Dispositivo prueba");
        GrupoWS ws= new GrupoWS();
        dispositivo.setGrupo(ws.obtenerGrupo(1));
        pruebaDAO.agregar(dispositivo);
    }
}
