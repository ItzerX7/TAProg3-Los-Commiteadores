package com.pucp.gestionlentesvr.persistencia.dao.configuracion;

import com.pucp.gestionlentesvr.dominio.configuracion.Configuracion;
import com.pucp.gestionlentesvr.dominio.configuracion.TipoConfiguracion;
import com.pucp.gestionlentesvr.persistencia.BaseDAOImpl;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class ConfiguracionDAOImpl extends BaseDAOImpl<Configuracion> implements ConfiguracionDAO {


    @Override
    protected CallableStatement getInsertPS(Connection conn, Configuracion entity) throws SQLException {
        String query = "{CALL insertar_configuracion(?, ?, ?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(query);
        cs.registerOutParameter(1, Types.INTEGER);
        cs.setString(2, entity.getNombre());
        cs.setString(3, entity.getDescripcion());
        cs.setString(4, entity.getValor());
        cs.setString(5, entity.getTipo().name());
        return cs;
    }

    @Override
    protected CallableStatement getUpdatePS(Connection conn, Configuracion entity) throws SQLException {
        String query = "{CALL actualizar_configuracion(?, ?, ?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(query);
        cs.setInt(1, entity.getId());
        cs.setString(2, entity.getNombre());
        cs.setString(3, entity.getDescripcion());
        cs.setString(4, entity.getValor());
        cs.setString(5, entity.getTipo().name());
        return cs;
    }

    @Override
    protected CallableStatement getDeletePS(Connection conn, Integer id) throws SQLException {
        String query = "{CALL eliminar_configuracion(?)}";
        CallableStatement cs = conn.prepareCall(query);
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement getSelectByIdPS(Connection conn, Integer id) throws SQLException {
        String query = "{CALL obtener_configuracion(?)}";
        CallableStatement cs = conn.prepareCall(query);
        cs.setInt(1, id);
        return cs; 
    }

    @Override
    protected CallableStatement getSelectAllPS(Connection conn) throws SQLException {
        String query = "{CALL listar_configuracion()}";
        CallableStatement cs = conn.prepareCall(query);
        return cs;
    }

    @Override
    protected Configuracion createFromResultSet(ResultSet rs) throws SQLException {
        Configuracion con = new Configuracion();
        con.setId(rs.getInt("configuracionid"));
        con.setNombre(rs.getString("nombre"));
        con.setDescripcion(rs.getString("descripcion"));
        con.setFechaCreacion(rs.getTimestamp("fechacreacion"));
        con.setValor(rs.getString("valor"));
        con.setTipo(TipoConfiguracion.valueOf(rs.getString("tipo")));
        con.setActivo(rs.getString("activo").toCharArray()[0]);
        return con;
    }

    @Override
    protected void setId(Configuracion entity, Integer id) {
        entity.setId(id);
    }

}
