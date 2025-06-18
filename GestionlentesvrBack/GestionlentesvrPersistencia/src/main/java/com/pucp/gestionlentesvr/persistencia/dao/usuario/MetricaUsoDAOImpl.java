package com.pucp.gestionlentesvr.persistencia.dao.usuario;

import com.pucp.gestionlentesvr.dominio.aplicacion.Aplicacion;
import com.pucp.gestionlentesvr.dominio.aplicacion.CategoriaAplicacion;
import com.pucp.gestionlentesvr.dominio.dispositivo.Dispositivo;
import com.pucp.gestionlentesvr.dominio.dispositivo.EstadoConexion;
import com.pucp.gestionlentesvr.dominio.dispositivo.Grupo;
import com.pucp.gestionlentesvr.dominio.usuario.MetricaUso;
import com.pucp.gestionlentesvr.dominio.usuario.Usuario;
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

public class MetricaUsoDAOImpl extends BaseDAOImpl<MetricaUso> implements MetricaUsoDAO {

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
    public Aplicacion obtenerAppMasUsada() {
        Aplicacion app = new Aplicacion();
        try (Connection conn = DBManager.getInstance().obtenerConexion();) {
            String query = "{CALL obtener_app_mas_ejecutada()}";
            CallableStatement cs = conn.prepareCall(query);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                app.setId(rs.getInt("aplicacionid"));
                app.setNombre(rs.getString("nombre"));
                app.setVersion(rs.getString("version"));
                app.setDesarrollador(rs.getString("desarrollador"));
                app.setDescripcion(rs.getString("descripcion"));
                app.setTamanomb(rs.getDouble("tamanio"));
                app.setCategoria(CategoriaAplicacion.valueOf(rs.getString("categoria")));
                app.setActivo(rs.getString("activo").toCharArray()[0]);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar entidades", e);
        }
        return app;
    }

    @Override
    public Dispositivo obtenerDispositivoMasUsado() {
        Dispositivo dev = new Dispositivo();
        try (Connection conn = DBManager.getInstance().obtenerConexion();) {
            String query = "{CALL obtener_dispositivo_mas_usado()}";
            CallableStatement cs = conn.prepareCall(query);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
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
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al listar entidades", e);
        }
        return dev;
    }

    @Override
    public Dispositivo obtenerDispositivoMenosUsado() {
        Dispositivo dev = new Dispositivo();
        try (Connection conn = DBManager.getInstance().obtenerConexion();) {
            String query = "{CALL obtener_dispositivo_menos_usado()}";
            CallableStatement cs = conn.prepareCall(query);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
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
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al listar entidades", e);
        }
        return dev;
    }
}
