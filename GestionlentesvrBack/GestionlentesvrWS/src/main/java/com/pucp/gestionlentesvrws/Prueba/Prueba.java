/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package com.pucp.gestionlentesvrws.Prueba;

import com.pucp.gestionlentesvr.dominio.aplicacion.Aplicacion;
import com.pucp.gestionlentesvr.dominio.dispositivo.Dispositivo;
import com.pucp.gestionlentesvr.dominio.dispositivo.EstadoConexion;
import com.pucp.gestionlentesvr.dominio.dispositivo.Grupo;
import com.pucp.gestionlentesvr.dominio.usuario.MetricaUso;
import com.pucp.gestionlentesvr.persistencia.dao.dispositivo.DispositivoDAO;
import com.pucp.gestionlentesvr.persistencia.dao.dispositivo.DispositivoDAOImpl;
import com.pucp.gestionlentesvr.persistencia.dao.dispositivo.GrupoDAO;
import com.pucp.gestionlentesvr.persistencia.dao.dispositivo.GrupoDAOImpl;
import com.pucp.gestionlentesvr.persistencia.dao.usuario.MetricaUsoDAO;
import com.pucp.gestionlentesvr.persistencia.dao.usuario.MetricaUsoDAOImpl;
import com.pucp.gestionlentesvr.ws.ActividadWS;
import com.pucp.gestionlentesvr.ws.GrupoWS;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.io.FileOutputStream;
import java.sql.Timestamp;

/**
 *
 * @author HP
 */
@WebService(serviceName = "Prueba")
public class Prueba {

    @WebMethod(operationName = "hola")
    public String hola() throws Exception {
        return "hola";
    }
}
