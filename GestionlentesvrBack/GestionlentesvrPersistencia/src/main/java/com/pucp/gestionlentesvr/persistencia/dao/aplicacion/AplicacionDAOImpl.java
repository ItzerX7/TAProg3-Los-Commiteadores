package com.pucp.gestionlentesvr.persistencia.dao.aplicacion;

import com.pucp.gestionlentesvr.dominio.aplicacion.Aplicacion;
import com.pucp.gestionlentesvr.dominio.aplicacion.CategoriaAplicacion;
import com.pucp.gestionlentesvr.persistencia.BaseDAOImpl;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class AplicacionDAOImpl extends BaseDAOImpl<Aplicacion> implements AplicacionDAO {

    @Override
    protected CallableStatement getInsertPS(Connection conn, Aplicacion entity) throws SQLException {
        String query = "{CALL insertar_aplicacion(?, ?, ?, ?, ?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(query);
        cs.registerOutParameter(1, Types.INTEGER);
        cs.setString(2, entity.getNombre());
        cs.setString(3, entity.getVersion());
        cs.setString(4, entity.getDesarrollador());
        cs.setString(5, entity.getDescripcion());
        cs.setDouble(6, entity.getTamanomb());
        cs.setString(7, entity.getCategoria().name());
        return cs;
    }

    @Override
    protected CallableStatement getUpdatePS(Connection conn, Aplicacion entity) throws SQLException {
        String query = "{CALL actualizar_aplicacion(?, ?, ?, ?, ?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(query);
        cs.setInt(1, entity.getId());
        cs.setString(2, entity.getNombre());
        cs.setString(3, entity.getVersion());
        cs.setString(4, entity.getDesarrollador());
        cs.setString(5, entity.getDescripcion());
        cs.setDouble(6, entity.getTamanomb());
        cs.setString(7, entity.getCategoria().name());
        return cs;
    }

    @Override
    protected CallableStatement getDeletePS(Connection conn, Integer id) throws SQLException {
        String query = "{CALL eliminar_aplicacion(?)}";
        CallableStatement cs = conn.prepareCall(query);
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement getSelectByIdPS(Connection conn, Integer id) throws SQLException {
        String query = "{CALL obtener_aplicacion(?)}";
        CallableStatement cs = conn.prepareCall(query);
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement getSelectAllPS(Connection conn) throws SQLException {
        String query = "{CALL listar_aplicacion()}";
        CallableStatement cs = conn.prepareCall(query);
        return cs;
    }

    @Override
    protected Aplicacion createFromResultSet(ResultSet rs) throws SQLException {
        Aplicacion app = new Aplicacion();
        app.setId(rs.getInt("aplicacionid"));
        app.setNombre(rs.getString("nombre"));
        app.setVersion(rs.getString("version"));
        app.setDesarrollador(rs.getString("desarrollador"));
        app.setDescripcion(rs.getString("descripcion"));
        app.setTamanomb(rs.getDouble("tamanio"));
        app.setCategoria(CategoriaAplicacion.valueOf(rs.getString("categoria")));
        app.setActivo(rs.getString("activo").toCharArray()[0]);
        return app;
    }

    @Override
    protected void setId(Aplicacion entity, Integer id) {
        entity.setId(id);
    }

}
