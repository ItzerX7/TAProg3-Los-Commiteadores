package com.pucp.gestionlentesvr.negocio.actividad;

import com.pucp.gestionlentesvr.dominio.actividad.Actividad;
import com.pucp.gestionlentesvr.persistencia.dao.actividad.ActividadDAO;
import com.pucp.gestionlentesvr.persistencia.dao.actividad.ActividadDAOImpl;
import java.util.List;

public class ActividadServiceImpl implements ActividadService {

    private final ActividadDAO actividadDAO;

    public ActividadServiceImpl() {
        this.actividadDAO = new ActividadDAOImpl();
    }

    @Override
    public void registrarActividad(Actividad actividad) throws Exception {
        if (actividad.getDescripcion() == null || actividad.getDescripcion().trim().isEmpty()) {
            throw new Exception("Debe colocar una descripción");
        }
        if (actividad.getDetallesTecnicos() == null || actividad.getDetallesTecnicos().trim().isEmpty()) {
            throw new Exception("Debe colocar los detalles de la actividad");
        }
        if (actividad.getTipo() == null) {
            throw new Exception("Debe colocar el tipo de actividad a registrar");
        }
        if (actividadDAO.obtener(actividad.getUsuario().getId()) == null) {
            throw new Exception("El usuario no existe");
        }
        if (actividadDAO.obtener(actividad.getDispositivo().getId()) == null) {
            throw new Exception("El dispositivo no existe");
        }

        actividadDAO.agregar(actividad);
    }

    @Override
    public void actualizarActividad(Actividad actividad) throws Exception {
        if (actividadDAO.obtener(actividad.getId()) == null) {
            throw new Exception("La actividad no existe");
        }
        if (actividad.getDescripcion() == null || actividad.getDescripcion().trim().isEmpty()) {
            throw new Exception("Debe colocar una descripción");
        }
        if (actividad.getDetallesTecnicos() == null || actividad.getDetallesTecnicos().trim().isEmpty()) {
            throw new Exception("Debe colocar los detalles de la actividad");
        }
        if (actividad.getTipo() == null) {
            throw new Exception("Debe colocar el tipo de actividad a registrar");
        }
        if (actividadDAO.obtener(actividad.getUsuario().getId()) == null) {
            throw new Exception("El usuario no existe");
        }
        if (actividadDAO.obtener(actividad.getDispositivo().getId()) == null) {
            throw new Exception("El dispositivo no existe");
        }

        actividadDAO.actualizar(actividad);
    }

    @Override
    public void activarActividad(Actividad actividad) throws Exception {
        if (actividadDAO.obtener(actividad.getId()) == null) {
            throw new Exception("La actividad no existe");
        }
        if (String.valueOf(actividad.getActivo()) == null) {
            throw new Exception("Debe colocar 's' o 'n' para si o no");
        }
        
        actividadDAO.actualizar(actividad);
    }

    @Override
    public Actividad obtenerActividad(int idActividad) throws Exception {
        Actividad actividad = actividadDAO.obtener(idActividad);
        if (actividad == null) {
            throw new Exception("La actividad no existe");
        }
        return actividad;
    }

    @Override
    public List<Actividad> listarActividad() throws Exception {
        return actividadDAO.listarTodos();
    }

}
