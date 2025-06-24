package com.pucp.gestionlentesvr.negocio.aplicacion;

import com.pucp.gestionlentesvr.dominio.aplicacion.Aplicacion;
<<<<<<< HEAD
=======
import com.pucp.gestionlentesvr.dominio.dispositivo.Dispositivo;
>>>>>>> 72e72ce (Ignorar archivos temporales de Visual Studio y build)
import java.util.List;

public interface AplicacionService {

    void registrarAplicacion(Aplicacion aplicacion) throws Exception;

    void actualizarAplicacion(Aplicacion aplicacion) throws Exception;

    void eliminarAplicacion(Integer idAplicacion) throws Exception;

    Aplicacion obtenerAplicacion(int idAplicacion) throws Exception;

    List<Aplicacion> listarAplicacion() throws Exception;
<<<<<<< HEAD
=======
    
    public void eliminarAplicacionesPorDispositivo();
    
    public List<Aplicacion> listarAplicacionesConDispositivos(int idAplicacion) throws Exception;
    
    public List<Integer> contarAplicacionesPorTipoEnMetricas();
    
    public List<Dispositivo> listarDispositivosPorAplicaciones(Integer id);
    
>>>>>>> 72e72ce (Ignorar archivos temporales de Visual Studio y build)
}
