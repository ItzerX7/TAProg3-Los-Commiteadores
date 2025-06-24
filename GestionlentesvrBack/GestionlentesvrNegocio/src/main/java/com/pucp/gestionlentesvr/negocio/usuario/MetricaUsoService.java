package com.pucp.gestionlentesvr.negocio.usuario;

<<<<<<< HEAD
=======
import com.pucp.gestionlentesvr.dominio.aplicacion.Aplicacion;
import com.pucp.gestionlentesvr.dominio.dispositivo.Dispositivo;
>>>>>>> 72e72ce (Ignorar archivos temporales de Visual Studio y build)
import com.pucp.gestionlentesvr.dominio.usuario.MetricaUso;
import java.util.List;

public interface MetricaUsoService {

    void registrarMetricaUso(MetricaUso metricaUso) throws Exception;

    void actualizarMetricaUso(MetricaUso metricaUso) throws Exception;

    void eliminarMetricaUso(Integer idMetricaUso) throws Exception;

    MetricaUso obtenerMetricaUso(int idMetricaUso) throws Exception;

    List<MetricaUso> listarMetricaUso() throws Exception;
<<<<<<< HEAD
=======
    
    public Aplicacion obtenerAppMasUsada();
    
    public Dispositivo obtenerDispositivoMasUsado();
    
     public Dispositivo obtenerDispositivoMenosUsado();
>>>>>>> 72e72ce (Ignorar archivos temporales de Visual Studio y build)
}
