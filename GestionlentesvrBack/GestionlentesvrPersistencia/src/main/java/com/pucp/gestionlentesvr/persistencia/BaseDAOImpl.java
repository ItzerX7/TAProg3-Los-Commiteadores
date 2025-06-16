package com.pucp.gestionlentesvr.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDAOImpl<T> implements BaseDAO<T> {

    protected abstract PreparedStatement getInsertPS(Connection conn, T entity) throws SQLException;
    protected abstract PreparedStatement getUpdatePS(Connection conn, T entity) throws SQLException;
    protected abstract CallableStatement getDeletePS(Connection conn, Integer id) throws SQLException;
    protected abstract PreparedStatement getSetActivePS(Connection conn, Integer id) throws SQLException;
    protected abstract PreparedStatement getSelectByIdPS(Connection conn, Integer id) throws SQLException;
    protected abstract PreparedStatement getSelectAllPS(Connection conn) throws SQLException;

    protected abstract T createFromResultSet(ResultSet rs) throws SQLException;
    protected abstract void setId(T entity, Integer id);

    @Override
    public void agregar(T entity) {
        try (Connection conn = DBManager.getInstance().obtenerConexion();
             PreparedStatement ps = getInsertPS(conn, entity)) {

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    setId(entity, rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al agregar entidad", e);
        }
    }

    @Override
    public T obtener(Integer id) {
        try (Connection conn = DBManager.getInstance().obtenerConexion();
             PreparedStatement ps = getSelectByIdPS(conn, id);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return createFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener entidad", e);
        }
        return null;
    }

    @Override
    public List<T> listarTodos() {
        List<T> entities = new ArrayList<>();
        try (Connection conn = DBManager.getInstance().obtenerConexion();
             PreparedStatement ps = getSelectAllPS(conn);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                entities.add(createFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar entidades", e);
        }
        return entities;
    }

    @Override
    public void actualizar(T entity) {
        try (Connection conn = DBManager.getInstance().obtenerConexion();
             PreparedStatement ps = getUpdatePS(conn, entity)) {

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar entidad", e);
        }
    }

    @Override
    public void setActivo(Integer id) {
        try (Connection conn = DBManager.getInstance().obtenerConexion();
             PreparedStatement ps = getSetActivePS(conn, id)) {

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar entidad", e);
        }
    }
    
    @Override
    public void eliminar(Integer id) {
        try (Connection conn = DBManager.getInstance().obtenerConexion();
             CallableStatement cs = getDeletePS(conn, id)) {

            cs.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar entidad", e);
        }
    }
}