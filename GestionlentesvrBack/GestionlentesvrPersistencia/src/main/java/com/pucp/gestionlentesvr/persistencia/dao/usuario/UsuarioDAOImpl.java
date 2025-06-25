package com.pucp.gestionlentesvr.persistencia.dao.usuario;

import com.pucp.gestionlentesvr.dominio.usuario.Rol;
import com.pucp.gestionlentesvr.dominio.usuario.Usuario;
import com.pucp.gestionlentesvr.persistencia.BaseDAOImpl;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class UsuarioDAOImpl extends BaseDAOImpl<Usuario> implements UsuarioDAO {

    @Override
    protected CallableStatement getInsertPS(Connection conn, Usuario entity) throws SQLException {
        String query = "{CALL insertar_usuario(?, ?, ?, ?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(query);
        cs.registerOutParameter(1, Types.INTEGER);
        cs.setString(2, entity.getNombre());
        cs.setString(3, entity.getApellido());
        cs.setString(4, entity.getCorreo());
        cs.setString(5, entity.getContrasena());
        cs.setInt(6, entity.getRol().getId());
        return cs;
    }

    @Override
    protected CallableStatement getUpdatePS(Connection conn, Usuario entity) throws SQLException {
        String query = "{CALL actualizar_usuario(?, ?, ?, ?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(query);
        cs.setInt(1, entity.getId());
        cs.setString(2, entity.getNombre());
        cs.setString(3, entity.getApellido());
        cs.setString(4, entity.getCorreo());
        cs.setString(5, entity.getContrasena());
        cs.setInt(6, entity.getRol().getId());
        return cs;
    }

    @Override
    protected CallableStatement getDeletePS(Connection conn, Integer id) throws SQLException {
        String query = "{CALL eliminar_usuario(?)}";
        CallableStatement cs = conn.prepareCall(query);
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement getSelectByIdPS(Connection conn, Integer id) throws SQLException {
        String query = "{CALL obtener_usuario(?)}";
        CallableStatement cs = conn.prepareCall(query);
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement getSelectAllPS(Connection conn) throws SQLException {
        String query = "{CALL listar_usuarios()}";
        CallableStatement cs = conn.prepareCall(query);
        return cs;
    }

    @Override
    protected Usuario createFromResultSet(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setRol(new Rol());
        usuario.setId(rs.getInt("usuarioid"));
        usuario.setNombre(rs.getString("nombre"));
        usuario.setApellido(rs.getString("apellido"));
        usuario.setCorreo(rs.getString("correo"));
        usuario.setContrasena(rs.getString("contrasena"));
        usuario.setFechaCreacion(rs.getTimestamp("fechacreacion"));
        usuario.getRol().setId(rs.getInt("rol_rolid"));
        usuario.setActivo(rs.getString("activo").toCharArray()[0]);
        return usuario;
    }

    @Override
    protected void setId(Usuario entity, Integer id) {
        entity.setId(id);
    }
}
