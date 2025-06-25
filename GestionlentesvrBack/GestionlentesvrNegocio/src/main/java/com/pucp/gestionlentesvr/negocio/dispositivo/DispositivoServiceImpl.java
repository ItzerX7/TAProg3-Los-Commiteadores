package com.pucp.gestionlentesvr.negocio.dispositivo;

import com.pucp.gestionlentesvr.dominio.dispositivo.Dispositivo;
import com.pucp.gestionlentesvr.dominio.dispositivo.Firmware;
import com.pucp.gestionlentesvr.persistencia.dao.dispositivo.DispositivoDAO;
import com.pucp.gestionlentesvr.persistencia.dao.dispositivo.DispositivoDAOImpl;
import java.util.List;

public class DispositivoServiceImpl implements DispositivoService {

    private final DispositivoDAO dispositivoDAO;

    public DispositivoServiceImpl() {
        this.dispositivoDAO = new DispositivoDAOImpl();
    }

    @Override
    public void registrarDispositivo(Dispositivo dispositivo) throws Exception {
        if (dispositivo.getNombre() == null || dispositivo.getNombre().trim().isEmpty()) {
            throw new Exception("Debe colocar un nombre");
        }
        if (dispositivo.getModelo() == null || dispositivo.getModelo().trim().isEmpty()) {
            throw new Exception("Debe colocar el modelo del dispositivo");
        }
        if (dispositivo.getNumeroSerie() == null || dispositivo.getNumeroSerie().trim().isEmpty()) {
            throw new Exception("Debe colocar el numero de serie");
        }
        if (dispositivo.getUbicacion() == null || dispositivo.getUbicacion().trim().isEmpty()) {
            throw new Exception("Debe colocar la ubicacion del dispositivo");
        }
        if (dispositivo.getNivelBateria() < 0) {
            throw new Exception("El nivel de la bateria no puede ser menor a 0");
        }
        if (dispositivo.getEstado() == null) {
            throw new Exception("Debe colocar el estado del dispositivo a registrar");
        }
        if (dispositivoDAO.obtener(dispositivo.getGrupo().getId()) == null) {
            throw new Exception("El grupo no existe");
        }
        dispositivoDAO.agregar(dispositivo);
    }

    @Override
    public void actualizarDispositivo(Dispositivo dispositivo) throws Exception {
        if (dispositivoDAO.obtener(dispositivo.getId()) == null) {
            throw new Exception("El dispositivo no existe");
        }
        if (dispositivo.getNombre() == null || dispositivo.getNombre().trim().isEmpty()) {
            throw new Exception("Debe colocar un nombre");
        }
        if (dispositivo.getModelo() == null || dispositivo.getModelo().trim().isEmpty()) {
            throw new Exception("Debe colocar el modelo del dispositivo");
        }
        if (dispositivo.getNumeroSerie() == null || dispositivo.getNumeroSerie().trim().isEmpty()) {
            throw new Exception("Debe colocar el numero de serie");
        }
        if (dispositivo.getUbicacion() == null || dispositivo.getUbicacion().trim().isEmpty()) {
            throw new Exception("Debe colocar la ubicacion del dispositivo");
        }
        if (dispositivo.getNivelBateria() < 0) {
            throw new Exception("El nivel de la bateria no puede ser menor a 0");
        }
        if (dispositivo.getEstado() == null) {
            throw new Exception("Debe colocar el estado del dispositivo a registrar");
        }
        if (dispositivoDAO.obtener(dispositivo.getGrupo().getId()) == null) {
            throw new Exception("El grupo no existe");
        }

        dispositivoDAO.actualizar(dispositivo);
    }

    @Override
    public void eliminarDispositivo(Integer idDispositivo) throws Exception {
        if (idDispositivo < 1 || idDispositivo == null) {
            throw new Exception("El dispositivo no existe");
        }

        dispositivoDAO.eliminar(idDispositivo);
    }

    @Override
    public Dispositivo obtenerDispositivo(int idDispositivo) throws Exception {
        Dispositivo dispositivo = dispositivoDAO.obtener(idDispositivo);
        if (dispositivo == null) {
            throw new Exception("El dispositivo no existe");
        }
        return dispositivo;
    }

    @Override
    public List<Dispositivo> listarDispositivo() throws Exception {
        return dispositivoDAO.listarTodos();
    }

    @Override
    public boolean insertarDisFirmware(Integer idDis, Integer idFirm) {
        return dispositivoDAO.insertarDisFirmware(idDis, idFirm);
    }

    @Override
    public Firmware obtenerUltimoFirm(Integer idDis) {
        return dispositivoDAO.obtenerUltimoFirm(idDis);
    }

}
