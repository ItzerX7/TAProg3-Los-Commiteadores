package com.pucp.gestionlentesvr.persistencia.dao.dispositivo;

import com.pucp.gestionlentesvr.dominio.dispositivo.Dispositivo;
import com.pucp.gestionlentesvr.dominio.dispositivo.Firmware;
import com.pucp.gestionlentesvr.persistencia.BaseDAO;

public interface DispositivoDAO extends BaseDAO<Dispositivo> {
    public boolean insertarDisFirmware(Integer idDis,Integer idFirm);
    public Firmware obtenerUltimoFirm(Integer idDis);
}
