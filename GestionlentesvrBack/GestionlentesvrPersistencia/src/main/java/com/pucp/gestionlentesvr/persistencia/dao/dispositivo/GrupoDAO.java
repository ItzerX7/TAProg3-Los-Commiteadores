package com.pucp.gestionlentesvr.persistencia.dao.dispositivo;

import com.pucp.gestionlentesvr.dominio.dispositivo.Dispositivo;
import com.pucp.gestionlentesvr.dominio.dispositivo.Grupo;
import com.pucp.gestionlentesvr.persistencia.BaseDAO;
import java.util.List;

public interface GrupoDAO extends BaseDAO<Grupo> {
    public List<Dispositivo> listarDispositivoPorGrupo(int id);
}
