package com.pucp.gestionlentesvr.persistencia.dao.actividad;

import com.pucp.gestionlentesvr.dominio.actividad.Actividad;
import com.pucp.gestionlentesvr.dominio.actividad.TipoActividad;
import com.pucp.gestionlentesvr.dominio.dispositivo.Dispositivo;
import com.pucp.gestionlentesvr.dominio.usuario.Usuario;
import com.pucp.gestionlentesvr.persistencia.BaseDAOImpl;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class ActividadDAOImpl extends BaseDAOImpl<Actividad> implements ActividadDAO {

    @Override
    protected CallableStatement getInsertPS(Connection conn, Actividad entity) throws SQLException {
        String query = "{CALL insertar_actividad(?, ?, ?, ?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(query);
        cs.registerOutParameter(1, Types.INTEGER);
        cs.setString(2, entity.getDescripcion());
        cs.setString(3, entity.getDetallesTecnicos());
        cs.setString(4, entity.getTipo().name());
        cs.setInt(5, entity.getUsuario().getId());
        cs.setInt(6, entity.getDispositivo().getId());
        return cs;
    }

    @Override
    protected CallableStatement getUpdatePS(Connection conn, Actividad entity) throws SQLException {
        String query = "{CALL actualizar_actvidad(?, ?, ?, ?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(query);
        cs.setInt(1, entity.getId());
        cs.setString(2, entity.getDescripcion());
        cs.setString(3, entity.getDetallesTecnicos());
        cs.setString(4, entity.getTipo().name());
        cs.setInt(5, entity.getUsuario().getId());
        cs.setInt(6, entity.getDispositivo().getId());
        return cs;
    }

    @Override
    protected CallableStatement getDeletePS(Connection conn, Integer id) throws SQLException {
        String query = "{CALL eliminar_actividad(?)}";
        CallableStatement cs = conn.prepareCall(query);
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement getSelectByIdPS(Connection conn, Integer id) throws SQLException {
        String query = "{CALL obtener_actividad(?)}";
        CallableStatement cs = conn.prepareCall(query);
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement getSelectAllPS(Connection conn) throws SQLException {
        String query = "{CALL listar_actividad()}";
        CallableStatement cs = conn.prepareCall(query);
        return cs;
    }

    @Override
    protected Actividad createFromResultSet(ResultSet rs) throws SQLException {
        Actividad actividad = new Actividad();
        Usuario usuario = new Usuario();
        actividad.setUsuario(usuario);
        Dispositivo dispositivo = new Dispositivo();
        actividad.setDispositivo(dispositivo);
        actividad.setId(rs.getInt("actividadid"));
        actividad.setFechaHora(rs.getTimestamp("fechahora"));
        actividad.setDetallesTecnicos(rs.getString("detallestecnicos"));
        actividad.setTipo(TipoActividad.valueOf(rs.getString("tipo")));
        actividad.getUsuario().setId(rs.getInt("usuario_usuarioid"));
        actividad.getDispositivo().setId(rs.getInt("dispositivo_dispositivoid"));
        actividad.setActivo((rs.getString("activo")).toCharArray()[0]);
        return actividad;
    }

    @Override
    protected void setId(Actividad entity, Integer id) {
        entity.setId(id);
    }

}
