package com.pucp.gestionlentesvr.persistencia.dao.aplicacion;

import com.pucp.gestionlentesvr.dominio.aplicacion.Aplicacion;
import com.pucp.gestionlentesvr.dominio.aplicacion.CategoriaAplicacion;
<<<<<<< HEAD
import com.pucp.gestionlentesvr.persistencia.BaseDAOImpl;
=======
import com.pucp.gestionlentesvr.dominio.dispositivo.Dispositivo;
import com.pucp.gestionlentesvr.persistencia.BaseDAOImpl;
import com.pucp.gestionlentesvr.persistencia.DBManager;
import com.pucp.gestionlentesvr.persistencia.dao.dispositivo.DispositivoDAOImpl;
>>>>>>> 72e72ce (Ignorar archivos temporales de Visual Studio y build)
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
<<<<<<< HEAD
=======
import java.util.ArrayList;
import java.util.List;
>>>>>>> 72e72ce (Ignorar archivos temporales de Visual Studio y build)

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

<<<<<<< HEAD
=======
    @Override
    public void eliminarAplicacionesPorDispositivo() {
        try (Connection conn = DBManager.getInstance().obtenerConexion();) {
            String query = "{CALL eliminarAplicacionesPorDispositivo()}";
            CallableStatement cs = conn.prepareCall(query);
            cs.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al Eliminar Aplicacion", e);
        }
    }

    @Override
    public List<Aplicacion> listarAplicacionesConDispositivos(Integer id) {
        List<Aplicacion> apps= new ArrayList<>();
        try (Connection conn = DBManager.getInstance().obtenerConexion();) {
            String query = "{CALL listarAplicacionesPorDispositivos(?)}";
            CallableStatement cs = conn.prepareCall(query); 
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                apps.add(createFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar entidades", e);
        }
        return apps;
    }

    @Override
    public List<Dispositivo> listarDispositivosPorAplicaciones(Integer id) {
        List<Dispositivo> dis= new ArrayList<>();
        DispositivoDAOImpl dao = new DispositivoDAOImpl();
        try (Connection conn = DBManager.getInstance().obtenerConexion();) {
            String query = "{CALL listarDispositivosPorAplicaciones(?)}";
            CallableStatement cs = conn.prepareCall(query); 
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                dis.add( dao.createFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar entidades", e);
        }
        return dis;
    }

    @Override
    public List<Integer> contarAplicacionesPorTipoEnMetricas() {
        List<Integer> cantidades= new ArrayList<>();
         try (Connection conn = DBManager.getInstance().obtenerConexion();) {
            String query = "{CALL contarAplicacionesPorTipoEnMetricas()}";
            CallableStatement cs = conn.prepareCall(query); 
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                cantidades.add(rs.getInt("cantidad"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar entidades", e);
        }
        return cantidades;
    }

>>>>>>> 72e72ce (Ignorar archivos temporales de Visual Studio y build)
}
