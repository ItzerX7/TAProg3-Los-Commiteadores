package com.pucp.gestionlentesvr.negocio.configuracion;

import com.pucp.gestionlentesvr.dominio.configuracion.Configuracion;
import com.pucp.gestionlentesvr.persistencia.dao.configuracion.ConfiguracionDAO;
import com.pucp.gestionlentesvr.persistencia.dao.configuracion.ConfiguracionDAOImpl;
import java.util.List;

public class ConfiguracionServiceImpl implements ConfiguracionService {

    private final ConfiguracionDAO configuracionDAO;

    public ConfiguracionServiceImpl() {
        this.configuracionDAO = new ConfiguracionDAOImpl();
    }

    @Override
    public void registrarConfiguracion(Configuracion configuracion) throws Exception {
        if (configuracion.getNombre() == null || configuracion.getNombre().trim().isEmpty()) {
            throw new Exception("Debe colocar un nombre");
        }
        if (configuracion.getDescripcion() == null || configuracion.getDescripcion().trim().isEmpty()) {
            throw new Exception("Debe colocar una descripcion");
        }
        if (configuracion.getValor() == null || configuracion.getValor().trim().isEmpty()) {
            throw new Exception("Debe colocar un valor");
        }
        if (configuracion.getTipo() == null) {
            throw new Exception("Debe colocar el tipo de configuracion a registrar");
        }

        configuracionDAO.agregar(configuracion);
    }

    @Override
    public void actualizarConfiguracion(Configuracion configuracion) throws Exception {
        if (configuracionDAO.obtener(configuracion.getId()) == null) {
            throw new Exception("La configuracion no existe");
        }
        if (configuracion.getNombre() == null || configuracion.getNombre().trim().isEmpty()) {
            throw new Exception("Debe colocar un nombre");
        }
        if (configuracion.getDescripcion() == null || configuracion.getDescripcion().trim().isEmpty()) {
            throw new Exception("Debe colocar una descripcion");
        }
        if (configuracion.getValor() == null || configuracion.getValor().trim().isEmpty()) {
            throw new Exception("Debe colocar un valor");
        }
        if (configuracion.getTipo() == null) {
            throw new Exception("Debe colocar el tipo de configuracion a registrar");
        }
        
        configuracionDAO.actualizar(configuracion);
    }

    @Override
    public void activarConfiguracion(Configuracion configuracion) throws Exception {
        if (configuracionDAO.obtener(configuracion.getId()) == null) {
            throw new Exception("La configuracion no existe");
        }
        if (String.valueOf(configuracion.getActivo()) == null) {
            throw new Exception("Debe colocar 's' o 'n' para si o no");
        }
        
        configuracionDAO.actualizar(configuracion);
    }

    @Override
    public Configuracion obtenerConfiguracion(int idConfiguracion) throws Exception {
        Configuracion configuracion = configuracionDAO.obtener(idConfiguracion);
        if (configuracion == null) {
            throw new Exception("La configuracion no existe");
        }
        return configuracion;
    }

    @Override
    public List<Configuracion> listarConfiguracion() throws Exception {
        return configuracionDAO.listarTodos();
    }
}
