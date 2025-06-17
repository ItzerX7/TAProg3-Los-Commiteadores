package com.pucp.gestionlentesvr.ws;

import com.pucp.gestionlentesvr.dominio.dispositivo.Grupo;
import com.pucp.gestionlentesvr.negocio.dispositivo.GrupoService;
import com.pucp.gestionlentesvr.negocio.dispositivo.GrupoServiceImpl;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.xml.ws.WebServiceException;
import java.util.List;

@WebService(serviceName = "GrupoWS", targetNamespace = "com.pucp.gestionlentesvr")
public class GrupoWS {


    private final GrupoService service;

    public GrupoWS() {
        this.service = new GrupoServiceImpl();
    }

    @WebMethod(operationName = "registrarGrupo")
    public void registrarGrupo(@WebParam(name = "elemento") Grupo elemento) throws Exception {
        service.registrarGrupo(elemento);
    }

    @WebMethod(operationName = "actualizarGrupo")
    public void actualizarGrupo(@WebParam(name = "elemento") Grupo elemento) throws Exception {
        service.actualizarGrupo(elemento);
    }
    
    @WebMethod(operationName = "eliminarGrupo")
    public void eliminarGrupo(@WebParam(name = "id") int id) throws Exception {
        service.eliminarGrupo(id);
    }

    @WebMethod(operationName = "obtenerGrupo")
    public Grupo obtenerGrupo(@WebParam(name = "id") int id) throws Exception {
        return service.obtenerGrupo(id);
    }

    @WebMethod(operationName = "listarGrupo")
    public List<Grupo> listarGrupo() throws Exception {
        return service.listarGrupo();
    }
}
