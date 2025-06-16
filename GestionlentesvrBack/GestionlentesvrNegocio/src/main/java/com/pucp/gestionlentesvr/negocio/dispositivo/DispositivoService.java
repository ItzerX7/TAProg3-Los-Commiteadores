package com.pucp.gestionlentesvr.negocio.dispositivo;

import com.pucp.gestionlentesvr.dominio.dispositivo.Dispositivo;
import java.util.List;

public interface DispositivoService {

    void registrarDispositivo(Dispositivo dispositivo) throws Exception;

    void actualizarDispositivo(Dispositivo dispositivo) throws Exception;

    void eliminarDispositivo(Integer idDispositivo) throws Exception;

    Dispositivo obtenerDispositivo(int idDispositivo) throws Exception;

    List<Dispositivo> listarDispositivo() throws Exception;
}
