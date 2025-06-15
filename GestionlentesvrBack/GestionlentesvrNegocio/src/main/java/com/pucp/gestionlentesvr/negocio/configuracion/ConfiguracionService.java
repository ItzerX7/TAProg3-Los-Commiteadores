package com.pucp.gestionlentesvr.negocio.configuracion;

import com.pucp.gestionlentesvr.dominio.configuracion.Configuracion;
import java.util.List;

public interface ConfiguracionService {

    void registrarConfiguracion(Configuracion configuracion) throws Exception;

    void actualizarConfiguracion(Configuracion configuracion) throws Exception;

    void activarConfiguracion(Configuracion configuracion) throws Exception;

    Configuracion obtenerConfiguracion(int idConfiguracion) throws Exception;

    List<Configuracion> listarConfiguracion() throws Exception;
}
