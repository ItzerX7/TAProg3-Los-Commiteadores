package com.pucp.gestionlentesvr.negocio.dispositivo;

import com.pucp.gestionlentesvr.dominio.dispositivo.Dispositivo;
import com.pucp.gestionlentesvr.dominio.dispositivo.Grupo;
import com.pucp.gestionlentesvr.persistencia.dao.dispositivo.GrupoDAO;
import com.pucp.gestionlentesvr.persistencia.dao.dispositivo.GrupoDAOImpl;
import java.util.List;

public class GrupoServiceImpl implements GrupoService {

    private final GrupoDAO grupoDAO;
    
    public GrupoServiceImpl() {
        this.grupoDAO = new GrupoDAOImpl();
    }
    
    @Override
    public void registrarGrupo(Grupo grupo) throws Exception {
        if (grupo.getNombre() == null || grupo.getNombre().trim().isEmpty()) {
            throw new Exception("Debe colocar un nombre");
        }
        if (grupo.getDescripcion() == null || grupo.getDescripcion().trim().isEmpty()) {
            throw new Exception("Debe colocar una descripcion");
        }
        if (grupo.getUbicacion()== null || grupo.getUbicacion().trim().isEmpty()) {
            throw new Exception("Debe colocar la ubicacion del grupo");
        }
        
        grupoDAO.agregar(grupo);
    }

    @Override
    public void actualizarGrupo(Grupo grupo) throws Exception {
        if (grupoDAO.obtener(grupo.getId()) == null) {
            throw new Exception("El grupo no existe");
        }
        if (grupo.getNombre() == null || grupo.getNombre().trim().isEmpty()) {
            throw new Exception("Debe colocar un nombre");
        }
        if (grupo.getDescripcion() == null || grupo.getDescripcion().trim().isEmpty()) {
            throw new Exception("Debe colocar una descripcion");
        }
        if (grupo.getUbicacion()== null || grupo.getUbicacion().trim().isEmpty()) {
            throw new Exception("Debe colocar la ubicacion del grupo");
        }
        
        grupoDAO.actualizar(grupo);
    }

    @Override
    public void eliminarGrupo(Integer idGrupo) throws Exception {
        if (idGrupo < 1 || idGrupo == null) {
            throw new Exception("El grupo no existe");
        }
        
        grupoDAO.eliminar(idGrupo);
    }

    @Override
    public Grupo obtenerGrupo(int idGrupo) throws Exception {
        Grupo grupo = grupoDAO.obtener(idGrupo);
        if (grupo == null) {
            throw new Exception("El grupo no existe");
        }
        return grupo;
    }

    @Override
    public List<Grupo> listarGrupo() throws Exception {
        return grupoDAO.listarTodos();
    }

    @Override
    public List<Dispositivo> listarDispositivosGrupo(int id) throws Exception {
        return grupoDAO.listarDispositivoPorGrupo(id);
    }
    
}
