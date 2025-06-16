package com.pucp.gestionlentesvrws.Prueba;

import com.pucp.gestionlentesvr.dominio.dispositivo.Grupo;
import com.pucp.gestionlentesvr.persistencia.dao.dispositivo.GrupoDAO;
import com.pucp.gestionlentesvr.persistencia.dao.dispositivo.GrupoDAOImpl;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.ws.rs.Produces;


@WebService(serviceName = "PruebaWS")
public class PruebaWS {

    private final GrupoDAO pruebaDAO;

    public PruebaWS() {
        this.pruebaDAO = new GrupoDAOImpl();
    }
    
    @WebMethod(operationName = "insertar")
    public boolean hello() {
        try{
            Grupo entidad = new Grupo();
            entidad.setNombre("Grupo Generico");
            entidad.setDescripcion("Grupo para los dispositivos aun sin grupo asignado");
            entidad.setUbicacion("Indefinido");
            pruebaDAO.agregar(entidad);
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
}