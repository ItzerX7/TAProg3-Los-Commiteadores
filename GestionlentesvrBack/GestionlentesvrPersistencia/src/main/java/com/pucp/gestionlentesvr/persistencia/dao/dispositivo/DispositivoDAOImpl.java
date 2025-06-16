package com.pucp.gestionlentesvr.persistencia.dao.dispositivo;

import com.pucp.gestionlentesvr.dominio.dispositivo.Dispositivo;
import com.pucp.gestionlentesvr.persistencia.BaseDAOImpl;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
        String query = "{CALL actualizar_dispositivo(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(query);
        cs.registerOutParameter(1, Types.INTEGER);
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
    protected Dispositivo createFromResultSet(ResultSet rs) throws SQLException {
        Dispositivo dev = new Dispositivo();
        dev.setId(rs.getInt("p_dispositivoid"));
        dev.setNombre(rs.getString("p_nombre"));
        dev.setModelo(rs.getString("p_modelo"));
        dev.setNumeroSerie(rs.getString("p_numeroserie"));
        dev.setUbicacion(rs.getString("p_ubicacion"));
        dev.setActivo(rs.getString("p_nivelbateria").toCharArray()[0]);
        dev.setNivelBateria(rs.getInt("p_ultimaconexion"));
        dev.setUltimaConexion(rs.getTimestamp("p_estado_conexion"));
        dev.getGrupo().setId(rs.getInt("p_grupo_grupoid"));
        return dev;
    }

    @Override
    protected void setId(Dispositivo entity, Integer id) {
        entity.setId(id);
    }
    
}
