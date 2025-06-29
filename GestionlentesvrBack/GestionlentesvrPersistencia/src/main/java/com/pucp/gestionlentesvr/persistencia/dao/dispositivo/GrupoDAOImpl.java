package com.pucp.gestionlentesvr.persistencia.dao.dispositivo;

import com.pucp.gestionlentesvr.dominio.dispositivo.Dispositivo;
import com.pucp.gestionlentesvr.dominio.dispositivo.Grupo;
import com.pucp.gestionlentesvr.persistencia.BaseDAOImpl;
import com.pucp.gestionlentesvr.persistencia.DBManager;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class GrupoDAOImpl extends BaseDAOImpl<Grupo> implements GrupoDAO {
    
    private DispositivoDAOImpl dao= new DispositivoDAOImpl();

    @Override
    protected CallableStatement getInsertPS(Connection conn, Grupo entity) throws SQLException {
        String query = "{CALL insertar_grupo(?, ?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(query);
        cs.registerOutParameter(1, Types.INTEGER);
        cs.setString(2, entity.getNombre());
        cs.setString(3, entity.getDescripcion());
        cs.setString(4, entity.getUbicacion());
    return cs;
    }

    @Override
    protected CallableStatement getUpdatePS(Connection conn, Grupo entity) throws SQLException {
        String query = "{CALL actualizar_grupo(?, ?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(query);
        cs.setInt(1, entity.getId());
        cs.setString(2, entity.getNombre());
        cs.setString(3, entity.getDescripcion());
        cs.setString(4, entity.getUbicacion());
        return cs;
    }

    @Override
    protected CallableStatement getDeletePS(Connection conn, Integer id) throws SQLException {
        String query = "{CALL eliminar_grupo(?)}";
        CallableStatement cs = conn.prepareCall(query);
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement getSelectByIdPS(Connection conn, Integer id) throws SQLException {
        String query = "{CALL obtener_grupo(?)}";
        CallableStatement cs = conn.prepareCall(query);
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement getSelectAllPS(Connection conn) throws SQLException {
        String query = "{CALL listar_grupo()}";
        CallableStatement cs = conn.prepareCall(query);
        return cs;
    }

    @Override
    protected Grupo createFromResultSet(ResultSet rs) throws SQLException {
        Grupo grupo = new Grupo();
        grupo.setId(rs.getInt("grupoid"));
        grupo.setNombre(rs.getString("nombre"));
        grupo.setDescripcion(rs.getString("descripcion"));
        grupo.setFechaCreacion(rs.getTimestamp("fechacreacion"));
        grupo.setUbicacion(rs.getString("ubicacion"));
        grupo.setActivo((rs.getString("activo")).toCharArray()[0]);
        return grupo;
    }

    @Override
    protected void setId(Grupo entity, Integer id) {
        entity.setId(id);
    }

    @Override
    public List<Dispositivo> listarDispositivoPorGrupo(int id) {
        List<Dispositivo> entities = new ArrayList<>();
        try (Connection conn = DBManager.getInstance().obtenerConexion()) {
            String query="CALL listar_Dispositivo_Grupo(?)";
            CallableStatement cs = conn.prepareCall(query);
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                entities.add(dao.createFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar dispositivos del grupo", e);
        }
        return entities;
    }
    
}
