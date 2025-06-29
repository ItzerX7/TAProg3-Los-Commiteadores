package com.pucp.gestionlentesvr.negocio.aplicacion;

import com.pucp.gestionlentesvr.dominio.aplicacion.Aplicacion;
import com.pucp.gestionlentesvr.dominio.dispositivo.Dispositivo;
import com.pucp.gestionlentesvr.persistencia.dao.aplicacion.AplicacionDAO;
import com.pucp.gestionlentesvr.persistencia.dao.aplicacion.AplicacionDAOImpl;
import java.util.List;

public class AplicacionServiceImpl implements AplicacionService {

    private final AplicacionDAO aplicacionDAO;

    public AplicacionServiceImpl() {
        this.aplicacionDAO = new AplicacionDAOImpl();
    }

    @Override
    public void registrarAplicacion(Aplicacion aplicacion) throws Exception {
        if (aplicacion.getNombre() == null || aplicacion.getNombre().trim().isEmpty()) {
            throw new Exception("Debe colocar un nombre");
        }
        if (aplicacion.getVersion() == null || aplicacion.getVersion().trim().isEmpty()) {
            throw new Exception("Debe colocar la version");
        }
        if (aplicacion.getDesarrollador() == null || aplicacion.getDesarrollador().trim().isEmpty()) {
            throw new Exception("Debe colocar el nombre del desarrollador");
        }
        if (aplicacion.getDescripcion() == null || aplicacion.getDescripcion().trim().isEmpty()) {
            throw new Exception("Debe colocar una descripcion");
        }
        if (aplicacion.getTamanomb() < 0) {
            throw new Exception("El peso de la aplicacion debe ser mayor a 0");
        }
        if (aplicacion.getCategoria() == null) {
            throw new Exception("Debe colocar la categoria de la aplicacion a registrar");
        }
        
        aplicacionDAO.agregar(aplicacion);
    }

    @Override
    public void actualizarAplicacion(Aplicacion aplicacion) throws Exception {
        if (aplicacionDAO.obtener(aplicacion.getId()) == null) {
            throw new Exception("La aplicacion no existe");
        }
        if (aplicacion.getNombre() == null || aplicacion.getNombre().trim().isEmpty()) {
            throw new Exception("Debe colocar un nombre");
        }
        if (aplicacion.getVersion() == null || aplicacion.getVersion().trim().isEmpty()) {
            throw new Exception("Debe colocar la version");
        }
        if (aplicacion.getDesarrollador() == null || aplicacion.getDesarrollador().trim().isEmpty()) {
            throw new Exception("Debe colocar el nombre del desarrollador");
        }
        if (aplicacion.getDescripcion() == null || aplicacion.getDescripcion().trim().isEmpty()) {
            throw new Exception("Debe colocar una descripcion");
        }
        if (aplicacion.getTamanomb() < 0) {
            throw new Exception("El peso de la aplicacion debe ser mayor a 0");
        }
        if (aplicacion.getCategoria() == null) {
            throw new Exception("Debe colocar la categoria de la aplicacion a registrar");
        }
        
        aplicacionDAO.actualizar(aplicacion);
    }

    @Override
    public void eliminarAplicacion(Integer idAplicacion) throws Exception {
        if (idAplicacion == null || idAplicacion < 1) {
            throw new Exception("La aplicación no existe o el ID no es válido.");
        }

        try {
            // Paso 1: Eliminar relaciones con dispositivos
            try {
                aplicacionDAO.eliminarRelacionesConDispositivos(idAplicacion);
            } catch (Exception exRel) {
                System.err.println("Error al eliminar relaciones con dispositivos: " + exRel.getMessage());
                throw new Exception("No se pudieron eliminar las relaciones con dispositivos antes de eliminar la aplicación.", exRel);
            }

            // Paso 2: Eliminar la aplicación
            try {
                aplicacionDAO.eliminar(idAplicacion);
            } catch (Exception exApp) {
                System.err.println("Error al eliminar la aplicación: " + exApp.getMessage());
                throw new Exception("No se pudo eliminar la aplicación después de eliminar sus relaciones.", exApp);
            }

        } catch (Exception exFinal) {
            // Re-lanzar con mensaje global para el service o WS
            throw new Exception("Error en el proceso de eliminación de la aplicación: " + exFinal.getMessage(), exFinal);
        }
    }

    

    @Override
    public Aplicacion obtenerAplicacion(int idAplicacion) throws Exception {
        Aplicacion aplicacion = aplicacionDAO.obtener(idAplicacion);
        if (aplicacion == null) {
            throw new Exception("La aplicacion no existe");
        }
        return aplicacion;
    }
    
    @Override
    public List<Aplicacion> listarAplicacionesConDispositivos(int idAplicacion) throws Exception {
        return aplicacionDAO.listarAplicacionesConDispositivos(idAplicacion);
    }

    @Override
    public List<Aplicacion> listarAplicacion() throws Exception {
        return aplicacionDAO.listarTodos();
    }

    @Override
    public void eliminarAplicacionesPorDispositivo() {
       aplicacionDAO.eliminarAplicacionesPorDispositivo();
    }

    @Override
    public List<Integer> contarAplicacionesPorTipoEnMetricas() {
        return aplicacionDAO.contarAplicacionesPorTipoEnMetricas();
    }

    @Override
    public List<Dispositivo> listarDispositivosPorAplicaciones(Integer id) {
        return aplicacionDAO.listarDispositivosPorAplicaciones(id);
    }

}
