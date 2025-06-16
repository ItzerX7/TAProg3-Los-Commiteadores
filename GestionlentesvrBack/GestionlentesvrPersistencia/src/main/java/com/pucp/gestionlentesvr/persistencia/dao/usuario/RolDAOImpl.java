package com.pucp.gestionlentesvr.persistencia.dao.usuario;

import com.pucp.gestionlentesvr.dominio.usuario.Rol;
import com.pucp.gestionlentesvr.persistencia.BaseDAOImpl;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RolDAOImpl extends BaseDAOImpl<Rol> implements RolDAO {

    @Override
    protected CallableStatement getInsertPS(Connection conn, Rol entity) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected CallableStatement getUpdatePS(Connection conn, Rol entity) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected CallableStatement getDeletePS(Connection conn, Integer id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected CallableStatement getSelectByIdPS(Connection conn, Integer id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected CallableStatement getSelectAllPS(Connection conn) throws SQLException {
        String query = "{CALL listar_rol(?)}";
        CallableStatement cs = conn.prepareCall(query);
        return cs;
    }

    @Override
    protected Rol createFromResultSet(ResultSet rs) throws SQLException {

        return null;

    }

    @Override
    protected void setId(Rol entity, Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
