package com.pucp.gestionlentesvr.negocio.usuario;

import com.pucp.gestionlentesvr.dominio.usuario.Usuario;
import com.pucp.gestionlentesvr.negocio.usuario.UsuarioService;
import com.pucp.gestionlentesvr.persistencia.dao.usuario.UsuarioDAOImpl;
import com.pucp.gestionlentesvr.persistencia.dao.usuario.UsuarioDAO;
import java.util.List;

public class UsuarioServiceImpl implements UsuarioService {
    
    private final UsuarioDAO usuarioDAO;
    
    public UsuarioServiceImpl() {
        this.usuarioDAO = new UsuarioDAOImpl();
    }

    @Override
    public void registrarUsuario(Usuario usuario) throws Exception {
        if(usuario.getNombre() == null || usuario.getNombre().trim().isEmpty()) {
            throw new Exception("Debe colocar un nombre");
        }
        if(usuario.getApellido()== null || usuario.getApellido().trim().isEmpty()) {
            throw new Exception("Debe colocar un apellido");
        }
        if(usuario.getCorreo()== null || usuario.getCorreo().trim().isEmpty()) {
            throw new Exception("Debe colocar un correo");
        }
        if(usuario.getContrasena()== null || usuario.getContrasena().trim().isEmpty()) {
            throw new Exception("Debe colocar una contraseña");
        }
        if(usuarioDAO.obtener(usuario.getRol().getId()) == null) {
            throw new Exception("El Rol no existe");
        }
        
        usuarioDAO.agregar(usuario);
    }

    @Override
    public void actualizarUsuario(Usuario usuario) throws Exception {
        if(usuarioDAO.obtener(usuario.getId()) == null) {
            throw new Exception("El usuario no existe");
        }
        if(usuario.getNombre() == null || usuario.getNombre().trim().isEmpty()) {
            throw new Exception("Debe colocar un nombre");
        }
        if(usuario.getApellido()== null || usuario.getApellido().trim().isEmpty()) {
            throw new Exception("Debe colocar un apellido");
        }
        if(usuario.getCorreo()== null || usuario.getCorreo().trim().isEmpty()) {
            throw new Exception("Debe colocar un correo");
        }
        if(usuario.getContrasena()== null || usuario.getContrasena().trim().isEmpty()) {
            throw new Exception("Debe colocar una contraseña");
        }
        if(usuarioDAO.obtener(usuario.getRol().getId()) == null) {
            throw new Exception("El Rol no existe");
        }
        
        usuarioDAO.actualizar(usuario);
    }

    @Override
    public void eliminarUsuario(Integer idUsuario) throws Exception {
        if (idUsuario < 1 || idUsuario == null) {
            throw new Exception("El usuario no existe");
        }
        
        usuarioDAO.eliminar(idUsuario);
    }

    @Override
    public Usuario obtenerUsuario(int idUsuario) throws Exception {
        Usuario usuario = usuarioDAO.obtener(idUsuario);
        if (usuario == null) {
            throw new Exception("La actividad no existe");
        }
        return usuario;
    }

    @Override
    public List<Usuario> listarUsuario() throws Exception {
        return usuarioDAO.listarTodos();
    }
    
}
