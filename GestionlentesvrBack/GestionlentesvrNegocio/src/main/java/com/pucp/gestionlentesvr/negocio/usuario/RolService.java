package com.pucp.gestionlentesvr.negocio.usuario;

import com.pucp.gestionlentesvr.dominio.usuario.Rol;
import java.util.List;

public interface RolService {

    void registrarRol(Rol rol) throws Exception;

    void actualizarRol(Rol rol) throws Exception;

    void eliminarRol(Integer idRol) throws Exception;

    Rol obtenerRol(int idRol) throws Exception;

    List<Rol> listarRol() throws Exception;

}
