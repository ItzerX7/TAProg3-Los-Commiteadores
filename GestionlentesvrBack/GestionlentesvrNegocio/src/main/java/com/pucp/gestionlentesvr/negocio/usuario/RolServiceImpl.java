package com.pucp.gestionlentesvr.negocio.usuario;

import com.pucp.gestionlentesvr.dominio.usuario.Rol;
import com.pucp.gestionlentesvr.persistencia.dao.usuario.RolDAO;
import com.pucp.gestionlentesvr.persistencia.dao.usuario.RolDAOImpl;
import java.util.List;

public class RolServiceImpl implements RolService {

    private final RolDAO rolDAO;

    public RolServiceImpl() {
        this.rolDAO = new RolDAOImpl();
    }

    @Override
    public void registrarRol(Rol rol) throws Exception {
        if (rol.getNombre() == null || rol.getNombre().trim().isEmpty()) {
            throw new Exception("Debe colocar un nombre");
        }
        if (rol.getDescripcion() == null || rol.getDescripcion().trim().isEmpty()) {
            throw new Exception("Debe colocar una descripcion");
        }

        rolDAO.agregar(rol);
    }

    @Override
    public void actualizarRol(Rol rol) throws Exception {
        if (rolDAO.obtener(rol.getId()) == null) {
            throw new Exception("El rol no existe");
        }
        if (rol.getNombre() == null || rol.getNombre().trim().isEmpty()) {
            throw new Exception("Debe colocar un nombre");
        }
        if (rol.getDescripcion() == null || rol.getDescripcion().trim().isEmpty()) {
            throw new Exception("Debe colocar una descripcion");
        }
        
        rolDAO.actualizar(rol);
    }

    @Override
    public void eliminarRol(Integer idRol) throws Exception {
        if (idRol < 1 || idRol == null) {
            throw new Exception("El rol no existe");
        }
        
        rolDAO.eliminar(idRol);
    }

    @Override
    public Rol obtenerRol(int idRol) throws Exception {
        Rol rol = rolDAO.obtener(idRol);
        if (rol == null) {
            throw new Exception("El rol no existe");
        }
        return rol;
    }

    @Override
    public List<Rol> listarRol() throws Exception {
        return rolDAO.listarTodos();
    }

}
