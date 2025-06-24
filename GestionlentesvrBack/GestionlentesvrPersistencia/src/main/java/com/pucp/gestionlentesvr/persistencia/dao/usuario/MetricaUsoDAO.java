package com.pucp.gestionlentesvr.persistencia.dao.usuario;

<<<<<<< HEAD
import com.pucp.gestionlentesvr.dominio.usuario.MetricaUso;
import com.pucp.gestionlentesvr.persistencia.BaseDAO;

public interface MetricaUsoDAO extends BaseDAO<MetricaUso> {
    
=======
import com.pucp.gestionlentesvr.dominio.aplicacion.Aplicacion;
import com.pucp.gestionlentesvr.dominio.dispositivo.Dispositivo;
import com.pucp.gestionlentesvr.dominio.usuario.MetricaUso;
import com.pucp.gestionlentesvr.persistencia.BaseDAO;
import java.util.List;

public interface MetricaUsoDAO extends BaseDAO<MetricaUso> {
    public Aplicacion obtenerAppMasUsada();
    public Dispositivo obtenerDispositivoMasUsado();
    public Dispositivo obtenerDispositivoMenosUsado();
>>>>>>> 72e72ce (Ignorar archivos temporales de Visual Studio y build)
}
