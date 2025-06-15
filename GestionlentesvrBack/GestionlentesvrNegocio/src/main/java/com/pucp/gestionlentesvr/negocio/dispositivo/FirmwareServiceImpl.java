package com.pucp.gestionlentesvr.negocio.dispositivo;

import com.pucp.gestionlentesvr.dominio.dispositivo.Firmware;
import com.pucp.gestionlentesvr.persistencia.dao.dispositivo.FirmwareDAO;
import com.pucp.gestionlentesvr.persistencia.dao.dispositivo.FirmwareDAOImpl;
import java.util.List;

public class FirmwareServiceImpl implements FirmwareService {

    private final FirmwareDAO firmwareDAO;

    public FirmwareServiceImpl() {
        this.firmwareDAO = new FirmwareDAOImpl();
    }

    @Override
    public void registrarFirmware(Firmware firmware) throws Exception {
        if (firmware.getNombre() == null || firmware.getNombre().trim().isEmpty()) {
            throw new Exception("Debe colocar un nombre");
        }
        if (firmware.getVersion() == null || firmware.getVersion().trim().isEmpty()) {
            throw new Exception("Debe colocar la version");
        }
        if (firmware.getDescripcion() == null || firmware.getDescripcion().trim().isEmpty()) {
            throw new Exception("Debe colocar una descripcion");
        }

        firmwareDAO.agregar(firmware);
    }

    @Override
    public void actualizarFirmware(Firmware firmware) throws Exception {
        if (firmwareDAO.obtener(firmware.getId()) == null) {
            throw new Exception("El firmware no existe");
        }
        if (firmware.getNombre() == null || firmware.getNombre().trim().isEmpty()) {
            throw new Exception("Debe colocar un nombre");
        }
        if (firmware.getVersion() == null || firmware.getVersion().trim().isEmpty()) {
            throw new Exception("Debe colocar la version");
        }
        if (firmware.getDescripcion() == null || firmware.getDescripcion().trim().isEmpty()) {
            throw new Exception("Debe colocar una descripcion");
        }

        firmwareDAO.actualizar(firmware);
    }

    @Override
    public void activarFirmware(Firmware firmware) throws Exception {
        if (firmwareDAO.obtener(firmware.getId()) == null) {
            throw new Exception("El firmware no existe");
        }
        if (String.valueOf(firmware.getActivo()) == null) {
            throw new Exception("Debe colocar 's' o 'n' para si o no");
        }

        firmwareDAO.actualizar(firmware);
    }

    @Override
    public Firmware obtenerFirmware(int idFirmware) throws Exception {
        Firmware firmware = firmwareDAO.obtener(idFirmware);
        if (firmware == null) {
            throw new Exception("El firmware no existe");
        }
        return firmware;
    }

    @Override
    public List<Firmware> listarFirmware() throws Exception {
        return firmwareDAO.listarTodos();
    }

}
