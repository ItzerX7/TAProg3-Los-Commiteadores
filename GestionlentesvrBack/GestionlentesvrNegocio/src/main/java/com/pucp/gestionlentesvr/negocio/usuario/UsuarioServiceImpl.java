package com.pucp.gestionlentesvr.negocio.usuario;

import com.pucp.gestionlentesvr.dominio.usuario.Usuario;
import com.pucp.gestionlentesvr.negocio.usuario.UsuarioService;
import com.pucp.gestionlentesvr.persistencia.dao.usuario.UsuarioDAOImpl;
import com.pucp.gestionlentesvr.persistencia.dao.usuario.UsuarioDAO;
import java.util.List;

public class UsuarioServiceImpl implements UsuarioService {
    
    private final UsuarioDAO boUsuario;

    public UsuarioServiceImpl() {
        this.boUsuario = new UsuarioDAOImpl();
    }

    @Override
    public void registrarUsuario(Usuario usuario) throws Exception {
        boUsuario.agregar(usuario);
    }

    @Override
    public void actualizarUsuario(Usuario usuario) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void activoUsuario(int idUsuario) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Usuario obtenerUsuario(int idUsuario) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Usuario> listarUsuarios() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
