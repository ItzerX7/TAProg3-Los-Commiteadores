package com.pucp.gestionlentesvr.persistencia.dao.aplicacion;

import com.pucp.gestionlentesvr.dominio.aplicacion.Aplicacion;
import com.pucp.gestionlentesvr.persistencia.BaseDAO;
import java.util.List;

public interface AplicacionDAO extends BaseDAO<Aplicacion> {
    public void eliminarAplicacionesPorDispositivo();
    public List<Aplicacion> listarAplicacionesConDispositivos(Integer id);
}
