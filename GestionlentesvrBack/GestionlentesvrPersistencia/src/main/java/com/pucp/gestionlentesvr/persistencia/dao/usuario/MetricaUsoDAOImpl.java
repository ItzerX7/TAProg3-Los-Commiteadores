package com.pucp.gestionlentesvr.persistencia.dao.usuario;

import com.pucp.gestionlentesvr.dominio.dispositivo.Dispositivo;
import com.pucp.gestionlentesvr.dominio.usuario.MetricaUso;
import com.pucp.gestionlentesvr.dominio.usuario.Usuario;
import com.pucp.gestionlentesvr.persistencia.BaseDAOImpl;
import com.pucp.gestionlentesvr.persistencia.DBManager;
import com.pucp.gestionlentesvr.persistencia.dao.dispositivo.DispositivoDAOImpl;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class MetricaUsoDAOImpl extends BaseDAOImpl<MetricaUso> implements MetricaUsoDAO {

    private DispositivoDAOImpl disDAO = new DispositivoDAOImpl();
    @Override
    protected CallableStatement getInsertPS(Connection conn, MetricaUso entity) throws SQLException {
        String query = "{CALL insertar_metricauso(?, ?, ?, ?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(query);
        cs.registerOutParameter(1, Types.INTEGER);
        cs.setInt(2, entity.getTiempoUsoMinutos());
        cs.setInt(3, entity.getNivelBateriaInicial());
        cs.setInt(4, entity.getNivelBateriaFinal());
        cs.setInt(5, entity.getUsuario().getId());
        cs.setInt(6, entity.getDispositivo().getId());
        return cs;
    }

    @Override
    protected CallableStatement getUpdatePS(Connection conn, MetricaUso entity) throws SQLException {
        String query = "{CALL insertar_metricauso(?, ?, ?, ?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(query);
        cs.setInt(1, entity.getId());
        cs.setInt(2, entity.getTiempoUsoMinutos());
        cs.setInt(3, entity.getNivelBateriaInicial());
        cs.setInt(4, entity.getNivelBateriaFinal());
        cs.setInt(5, entity.getUsuario().getId());
        cs.setInt(6, entity.getDispositivo().getId());
        return cs;
    }

    @Override
    protected CallableStatement getDeletePS(Connection conn, Integer id) throws SQLException {
        String query = "{CALL obtener_metricauso(?)}";
        CallableStatement cs = conn.prepareCall(query);
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement getSelectByIdPS(Connection conn, Integer id) throws SQLException {
        String query = "{CALL obtener_metricauso(?)}";
        CallableStatement cs = conn.prepareCall(query);
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement getSelectAllPS(Connection conn) throws SQLException {
        String query = "{CALL listar_metricauso()}";
        CallableStatement cs = conn.prepareCall(query);
        return cs;
    }

    @Override
    protected MetricaUso createFromResultSet(ResultSet rs) throws SQLException {
        MetricaUso met = new MetricaUso();
        Usuario usuario = new Usuario();
        met.setUsuario(usuario);
        Dispositivo dispositivo = new Dispositivo();
        met.setDispositivo(dispositivo);
        met.setId(rs.getInt("metricaid"));
        met.setFechaRegistro(rs.getDate("fecharegistro"));
        met.setTiempoUsoMinutos(rs.getInt("tiempousominutos"));
        met.setNivelBateriaInicial(rs.getInt("nivelbateriainicial"));
        met.setNivelBateriaFinal(rs.getInt("nivelbateriafinal"));
        met.getUsuario().setId(rs.getInt("usuario_usuarioid"));
        met.getDispositivo().setId(rs.getInt("dispositivo_dispositivoid"));
        met.setActivo(rs.getString("activo").charAt(0));
        return met;
    }

    @Override
    protected void setId(MetricaUso entity, Integer id) {
        entity.setId(id);
    }

    @Override
    public Dispositivo obtenerDispositivoMasUsado() {
        String query = "{CALL obtener_dispositivo_mas_usado()}";
        try (Connection conn = DBManager.getInstance().obtenerConexion();
                CallableStatement cs = conn.prepareCall(query); ResultSet rs = cs.executeQuery()) {
            
            if (rs.next()) {
                return disDAO.createFromResultSet(rs);
            }else{
                 throw new RuntimeException("Error al listar entidades");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar entidades", e);
        }
    }


}
