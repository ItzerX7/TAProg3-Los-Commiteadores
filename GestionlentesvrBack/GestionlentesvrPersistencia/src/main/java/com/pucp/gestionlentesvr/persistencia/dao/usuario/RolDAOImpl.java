package com.pucp.gestionlentesvr.persistencia.dao.usuario;

import com.pucp.gestionlentesvr.dominio.usuario.Rol;
import com.pucp.gestionlentesvr.persistencia.BaseDAOImpl;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class RolDAOImpl extends BaseDAOImpl<Rol> implements RolDAO {

    @Override
    protected CallableStatement getInsertPS(Connection conn, Rol entity) throws SQLException {
        String query = "{CALL insertar_rol(?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(query);
        cs.registerOutParameter(1, Types.INTEGER);
        cs.setString(2, entity.getNombre());
        cs.setString(3, entity.getDescripcion());
        return cs;
    }

    @Override
    protected CallableStatement getUpdatePS(Connection conn, Rol entity) throws SQLException {
        String query = "{CALL actualizar_rol(?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(query);
        cs.setInt(1, entity.getId());
        cs.setString(2, entity.getNombre());
        cs.setString(3, entity.getDescripcion());
        return cs;
    }

    @Override
    protected CallableStatement getDeletePS(Connection conn, Integer id) throws SQLException {
        String query = "{CALL eliminar_rol(?)}";
        CallableStatement cs = conn.prepareCall(query);
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement getSelectByIdPS(Connection conn, Integer id) throws SQLException {
        String query = "{CALL obtener_rol(?)}";
        CallableStatement cs = conn.prepareCall(query);
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement getSelectAllPS(Connection conn) throws SQLException {
        String query = "{CALL listar_rol()}";
        CallableStatement cs = conn.prepareCall(query);
        return cs;
    }

    @Override
    protected Rol createFromResultSet(ResultSet rs) throws SQLException {
        Rol rol = new Rol();
        rol.setId(rs.getInt("rolid"));
        rol.setNombre(rs.getString("nombre"));
        rol.setDescripcion(rs.getString("descripcion"));
        rol.setActivo(rs.getString("activo").toCharArray()[0]);
        return rol;
    }

    @Override
    protected void setId(Rol entity, Integer id) {
        entity.setId(id);
    }

}
