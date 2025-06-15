package com.pucp.gestionlentesvr.negocio.aplicacion;

import com.pucp.gestionlentesvr.dominio.aplicacion.Aplicacion;
import java.util.List;

public interface AplicacionService {

    void registrarAplicacion(Aplicacion aplicacion) throws Exception;

    void actualizarAplicacion(Aplicacion aplicacion) throws Exception;

    void activarAplicacion(Aplicacion aplicacion) throws Exception;

    Aplicacion obtenerAplicacion(int idAplicacion) throws Exception;

    List<Aplicacion> listarAplicacion() throws Exception;
}
