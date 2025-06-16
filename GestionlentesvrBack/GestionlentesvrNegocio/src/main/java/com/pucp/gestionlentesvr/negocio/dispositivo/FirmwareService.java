package com.pucp.gestionlentesvr.negocio.dispositivo;

import com.pucp.gestionlentesvr.dominio.dispositivo.Firmware;
import java.util.List;

public interface FirmwareService {

    void registrarFirmware(Firmware firmware) throws Exception;

    void actualizarFirmware(Firmware firmware) throws Exception;

    void eliminarFirmware(Integer idFirmware) throws Exception;

    Firmware obtenerFirmware(int idFirmware) throws Exception;

    List<Firmware> listarFirmware() throws Exception;
}
