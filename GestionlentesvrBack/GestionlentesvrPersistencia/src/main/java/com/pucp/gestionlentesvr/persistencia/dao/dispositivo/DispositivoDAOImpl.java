package com.pucp.gestionlentesvr.persistencia.dao.dispositivo;

import com.pucp.gestionlentesvr.dominio.dispositivo.Dispositivo;
import com.pucp.gestionlentesvr.dominio.dispositivo.EstadoConexion;
import com.pucp.gestionlentesvr.dominio.dispositivo.Grupo;
import com.pucp.gestionlentesvr.persistencia.BaseDAOImpl;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class DispositivoDAOImpl extends BaseDAOImpl<Dispositivo> implements DispositivoDAO {

    @Override
    protected CallableStatement getInsertPS(Connection conn, Dispositivo entity) throws SQLException {
        String query = "{CALL insertar_dispositivo(?, ?, ?, ?, ?, ?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(query);
        cs.registerOutParameter(1, Types.INTEGER);
        cs.setString(2, entity.getNombre());
        cs.setString(3, entity.getModelo());
        cs.setString(4, entity.getNumeroSerie());
        cs.setString(5, entity.getUbicacion());
        cs.setInt(6, entity.getNivelBateria());
        cs.setString(7, entity.getEstado().name());
        cs.setInt(8, entity.getGrupo().getId());
        return cs;
    }

    @Override
    protected CallableStatement getUpdatePS(Connection conn, Dispositivo entity) throws SQLException {
        String query = "{CALL actualizar_dispositivo(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(query);
        cs.setInt(1, entity.getId());
        cs.setString(2, entity.getNombre());
        cs.setString(3, entity.getModelo());
        cs.setString(4, entity.getNumeroSerie());
        cs.setString(5, entity.getUbicacion());
        cs.setInt(6, entity.getNivelBateria());
        cs.setDate(7, new java.sql.Date(entity.getUltimaConexion().getTime()));
        cs.setString(8, entity.getEstado().name());
        cs.setInt(9, entity.getGrupo().getId());
        return cs;
    }

    @Override
    protected CallableStatement getDeletePS(Connection conn, Integer id) throws SQLException {
        String query = "{CALL eliminar_dispositivo(?)}";
        CallableStatement cs = conn.prepareCall(query);
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement getSelectByIdPS(Connection conn, Integer id) throws SQLException {
        String query = "{CALL obtener_dispositivo(?)}";
        CallableStatement cs = conn.prepareCall(query);
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement getSelectAllPS(Connection conn) throws SQLException {
        String query = "{CALL listar_dispositivo()}";
        CallableStatement cs = conn.prepareCall(query);
        return cs;
    }

    @Override
    public Dispositivo createFromResultSet(ResultSet rs) throws SQLException {
        Dispositivo dev = new Dispositivo();
        Grupo grupo = new Grupo();
        dev.setGrupo(grupo);
        dev.setId(rs.getInt("dispositivoid"));
        dev.setNombre(rs.getString("nombre"));
        dev.setModelo(rs.getString("modelo"));
        dev.setFechaRegistro(rs.getDate("fecharegistro"));
        dev.setNumeroSerie(rs.getString("numeroserie"));
        dev.setUbicacion(rs.getString("ubicacion"));
        dev.setActivo(rs.getString("activo").toCharArray()[0]);
        dev.setNivelBateria(rs.getInt("nivelbateria"));
        dev.setEstado(EstadoConexion.valueOf(rs.getString("estado_conexion")));
        dev.setUltimaConexion(rs.getDate("ultimaconexion"));
        dev.getGrupo().setId(rs.getInt("grupo_grupoid"));
        return dev;
    }

    @Override
    protected void setId(Dispositivo entity, Integer id) {
        entity.setId(id);
    }

}
