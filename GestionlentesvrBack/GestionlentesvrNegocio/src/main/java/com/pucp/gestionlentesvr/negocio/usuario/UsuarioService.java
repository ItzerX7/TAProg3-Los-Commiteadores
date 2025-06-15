package com.pucp.gestionlentesvr.negocio.usuario;

import com.pucp.gestionlentesvr.dominio.usuario.Usuario;
import java.util.List;

public interface UsuarioService {
    void registrarUsuario(Usuario usuario) throws Exception;
    void actualizarUsuario(Usuario usuario) throws Exception;
    void activarUsuario(Usuario usuario) throws Exception;
    Usuario obtenerUsuario(int idUsuario) throws Exception;
    List<Usuario> listarUsuario() throws Exception;
}
