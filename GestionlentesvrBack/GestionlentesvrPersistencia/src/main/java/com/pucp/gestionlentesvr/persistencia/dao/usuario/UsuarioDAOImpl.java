package com.pucp.gestionlentesvr.persistencia.dao.usuario;

import com.pucp.gestionlentesvr.dominio.usuario.Usuario;
import com.pucp.gestionlentesvr.persistencia.BaseDAOImpl;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UsuarioDAOImpl extends BaseDAOImpl<Usuario> implements UsuarioDAO {

    @Override
    protected PreparedStatement getInsertPS(Connection conn, Usuario usuario) throws SQLException {
        String query = "INSERT INTO usuario (nombre, apellido, correo, contrasena, fechacreacion)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, usuario.getNombre());
        ps.setString(2, usuario.getApellido());
        ps.setString(3, usuario.getCorreo());
        ps.setString(4, usuario.getContrasena());
        ps.setTimestamp(5, usuario.getFechaCreacion());
        ps.setInt(6, usuario.getRol().getId());
        return ps;
    }

    @Override
    protected PreparedStatement getUpdatePS(Connection conn, Usuario entity) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected PreparedStatement getSetActivePS(Connection conn, Integer id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected PreparedStatement getSelectByIdPS(Connection conn, Integer id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected PreparedStatement getSelectAllPS(Connection conn) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected Usuario createFromResultSet(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void setId(Usuario usuario, Integer id) {
        usuario.setId(id);
    }

    @Override
    protected CallableStatement getDeletePS(Connection conn, Integer id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
