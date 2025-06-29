package com.pucp.gestionlentesvr.persistencia.dao.usuario;

import com.pucp.gestionlentesvr.dominio.usuario.Usuario;
import com.pucp.gestionlentesvr.persistencia.BaseDAO;

public interface UsuarioDAO extends BaseDAO<Usuario> {
    public Usuario obtener_usuario_por_correo(String correo);
}
