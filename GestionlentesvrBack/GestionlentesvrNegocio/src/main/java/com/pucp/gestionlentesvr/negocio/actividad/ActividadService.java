package com.pucp.gestionlentesvr.negocio.actividad;

import com.pucp.gestionlentesvr.dominio.actividad.Actividad;
import java.util.List;

public interface ActividadService {

    void registrarActividad(Actividad actividad) throws Exception;

    void actualizarActividad(Actividad actividad) throws Exception;

    void eliminarActividad(Integer idActividad) throws Exception;

    Actividad obtenerActividad(int idActividad) throws Exception;

    List<Actividad> listarActividad() throws Exception;
}
