package com.pucp.gestionlentesvr.negocio.aplicacion;

import com.pucp.gestionlentesvr.dominio.aplicacion.Aplicacion;
<<<<<<< HEAD
=======
import com.pucp.gestionlentesvr.dominio.dispositivo.Dispositivo;
>>>>>>> 72e72ce (Ignorar archivos temporales de Visual Studio y build)
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
        if (idAplicacion < 1 || idAplicacion == null) {
            throw new Exception("La aplicacion no existe");
        }
        
        aplicacionDAO.eliminar(idAplicacion);
    }

    @Override
    public Aplicacion obtenerAplicacion(int idAplicacion) throws Exception {
        Aplicacion aplicacion = aplicacionDAO.obtener(idAplicacion);
        if (aplicacion == null) {
            throw new Exception("La aplicacion no existe");
        }
        return aplicacion;
    }
<<<<<<< HEAD
=======
    
    @Override
    public List<Aplicacion> listarAplicacionesConDispositivos(int idAplicacion) throws Exception {
        return aplicacionDAO.listarAplicacionesConDispositivos(idAplicacion);
    }
>>>>>>> 72e72ce (Ignorar archivos temporales de Visual Studio y build)

    @Override
    public List<Aplicacion> listarAplicacion() throws Exception {
        return aplicacionDAO.listarTodos();
    }

<<<<<<< HEAD
=======
    @Override
    public void eliminarAplicacionesPorDispositivo() {
       aplicacionDAO.eliminarAplicacionesPorDispositivo();;
    }

    @Override
    public List<Integer> contarAplicacionesPorTipoEnMetricas() {
        return aplicacionDAO.contarAplicacionesPorTipoEnMetricas();
    }

    @Override
    public List<Dispositivo> listarDispositivosPorAplicaciones(Integer id) {
        return aplicacionDAO.listarDispositivosPorAplicaciones(id);
    }

>>>>>>> 72e72ce (Ignorar archivos temporales de Visual Studio y build)
}
