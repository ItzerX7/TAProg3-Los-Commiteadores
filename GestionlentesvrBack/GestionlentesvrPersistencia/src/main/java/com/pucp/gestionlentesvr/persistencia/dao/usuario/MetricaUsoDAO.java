package com.pucp.gestionlentesvr.persistencia.dao.usuario;

import com.pucp.gestionlentesvr.dominio.dispositivo.Dispositivo;
import com.pucp.gestionlentesvr.dominio.usuario.MetricaUso;
import com.pucp.gestionlentesvr.persistencia.BaseDAO;

public interface MetricaUsoDAO extends BaseDAO<MetricaUso> {
    Dispositivo obtenerDispositivoMasUsado();
}
