package com.pucp.gestionlentesvr.persistencia.dao.dispositivo;

import com.pucp.gestionlentesvr.dominio.dispositivo.Firmware;
import com.pucp.gestionlentesvr.persistencia.BaseDAOImpl;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class FirmwareDAOImpl extends BaseDAOImpl<Firmware> implements FirmwareDAO {

      @Override
    protected CallableStatement getInsertPS(Connection conn, Firmware entity) throws SQLException {
        String query = "{CALL insertar_firmware(?, ?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(query);
        cs.registerOutParameter(1, Types.INTEGER);
        cs.setString(2, entity.getNombre());
        cs.setString(3, entity.getVersion());
        cs.setString(4, entity.getDescripcion());
        return cs;
    }

    @Override
    protected CallableStatement getUpdatePS(Connection conn, Firmware entity) throws SQLException {
       String query = "{CALL actualizar_firmware(?, ?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(query);
        cs.setInt(1, entity.getId());
        cs.setString(2, entity.getNombre());
        cs.setString(3, entity.getVersion());
        cs.setString(4, entity.getDescripcion());
        return cs;
    }

    @Override
    protected CallableStatement getDeletePS(Connection conn, Integer id) throws SQLException {
        String query = "{CALL eliminar_firmware(?)}";
        CallableStatement cs = conn.prepareCall(query);
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement getSelectByIdPS(Connection conn, Integer id) throws SQLException {
        String query = "{CALL obtener_firmware(?)}";
        CallableStatement cs = conn.prepareCall(query);
        cs.setInt(1, id);
        return cs; 
    }

    @Override
    protected CallableStatement getSelectAllPS(Connection conn) throws SQLException {
        String query = "{CALL listar_firmware()}";
        CallableStatement cs = conn.prepareCall(query);
        return cs;  
    }

    @Override
    protected Firmware createFromResultSet(ResultSet rs) throws SQLException {
        Firmware firm = new Firmware();
        firm.setId(rs.getInt("firmwareid"));
        firm.setNombre(rs.getString("nombre"));
        firm.setVersion(rs.getString("version"));
        firm.setDescripcion(rs.getString("descripcion"));
        firm.setActivo((rs.getString("activo")).toCharArray()[0]);
        return firm;
    }
    
    @Override
    protected void setId(Firmware entity, Integer id) {
        entity.setId(id);
    }
}