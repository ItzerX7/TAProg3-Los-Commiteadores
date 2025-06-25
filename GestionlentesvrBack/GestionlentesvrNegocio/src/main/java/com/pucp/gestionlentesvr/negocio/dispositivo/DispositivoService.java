package com.pucp.gestionlentesvr.negocio.dispositivo;

import com.pucp.gestionlentesvr.dominio.dispositivo.Dispositivo;
import com.pucp.gestionlentesvr.dominio.dispositivo.Firmware;
import java.util.List;

public interface DispositivoService {

    void registrarDispositivo(Dispositivo dispositivo) throws Exception;

    void actualizarDispositivo(Dispositivo dispositivo) throws Exception;

    void eliminarDispositivo(Integer idDispositivo) throws Exception;

    Dispositivo obtenerDispositivo(int idDispositivo) throws Exception;

    List<Dispositivo> listarDispositivo() throws Exception;
    
    boolean insertarDisFirmware(Integer idDis,Integer idFirm);
    
    Firmware obtenerUltimoFirm(Integer idDis);
}
