/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package com.pucp.gestionlentesvrws.Prueba;

<<<<<<< HEAD
import com.pucp.gestionlentesvr.dominio.dispositivo.Dispositivo;
import com.pucp.gestionlentesvr.dominio.dispositivo.EstadoConexion;
import com.pucp.gestionlentesvr.dominio.dispositivo.Grupo;
=======
import com.pucp.gestionlentesvr.dominio.aplicacion.Aplicacion;
import com.pucp.gestionlentesvr.dominio.dispositivo.Dispositivo;
import com.pucp.gestionlentesvr.dominio.dispositivo.EstadoConexion;
import com.pucp.gestionlentesvr.dominio.dispositivo.Grupo;
import com.pucp.gestionlentesvr.dominio.usuario.MetricaUso;
>>>>>>> 72e72ce (Ignorar archivos temporales de Visual Studio y build)
import com.pucp.gestionlentesvr.persistencia.dao.dispositivo.DispositivoDAO;
import com.pucp.gestionlentesvr.persistencia.dao.dispositivo.DispositivoDAOImpl;
import com.pucp.gestionlentesvr.persistencia.dao.dispositivo.GrupoDAO;
import com.pucp.gestionlentesvr.persistencia.dao.dispositivo.GrupoDAOImpl;
<<<<<<< HEAD
=======
import com.pucp.gestionlentesvr.persistencia.dao.usuario.MetricaUsoDAO;
import com.pucp.gestionlentesvr.persistencia.dao.usuario.MetricaUsoDAOImpl;
>>>>>>> 72e72ce (Ignorar archivos temporales de Visual Studio y build)
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

<<<<<<< HEAD
    public final GrupoDAO pruebaDAO; 

    public Prueba() {
        pruebaDAO= new GrupoDAOImpl();
=======
    public final MetricaUsoDAO pruebaDAO; 

    public Prueba() {
        pruebaDAO= new MetricaUsoDAOImpl();
>>>>>>> 72e72ce (Ignorar archivos temporales de Visual Studio y build)
    }
    
    
    @WebMethod(operationName = "hello")
<<<<<<< HEAD
    public void hello() throws Exception {
        Grupo group = pruebaDAO.obtener(1);
        group.setNombre("nombre actualizado");
        pruebaDAO.actualizar(group);
=======
    public Aplicacion hello() throws Exception {
        Aplicacion elemento = pruebaDAO.obtenerAppMasUsada();
        return elemento;
>>>>>>> 72e72ce (Ignorar archivos temporales de Visual Studio y build)
    }
}
