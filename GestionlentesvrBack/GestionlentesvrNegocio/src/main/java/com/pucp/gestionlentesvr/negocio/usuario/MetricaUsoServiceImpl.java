package com.pucp.gestionlentesvr.negocio.usuario;

import com.pucp.gestionlentesvr.dominio.aplicacion.Aplicacion;
import com.pucp.gestionlentesvr.dominio.dispositivo.Dispositivo;
import com.pucp.gestionlentesvr.dominio.usuario.MetricaUso;
import com.pucp.gestionlentesvr.persistencia.dao.usuario.MetricaUsoDAO;
import com.pucp.gestionlentesvr.persistencia.dao.usuario.MetricaUsoDAOImpl;
import java.util.List;

public class MetricaUsoServiceImpl implements MetricaUsoService {

    private final MetricaUsoDAO metricaUsoDAO;

    public MetricaUsoServiceImpl() {
        this.metricaUsoDAO = new MetricaUsoDAOImpl();
    }

    @Override
    public void registrarMetricaUso(MetricaUso metricaUso) throws Exception {
        if (metricaUso.getTiempoUsoMinutos() < 0) {
            throw new Exception("El tiempo de uso no puede ser negativo");
        }
        if (metricaUso.getNivelBateriaInicial() > 100) {
            throw new Exception("El nivel de la bateria inicial no puede ser mayor a 100");
        }
        if (metricaUso.getNivelBateriaFinal() < 0 ) {
            throw new Exception("El nivel de la bateria final no puede ser menor a 0");
        }
        if (metricaUsoDAO.obtener(metricaUso.getUsuario().getId()) == null) {
            throw new Exception("El usuario no existe");
        }
        if (metricaUsoDAO.obtener(metricaUso.getDispositivo().getId()) == null) {
            throw new Exception("El dispositivo no existe");
        }
        
        metricaUsoDAO.agregar(metricaUso);
    }

    @Override
    public void actualizarMetricaUso(MetricaUso metricaUso) throws Exception {
        if (metricaUsoDAO.obtener(metricaUso.getId()) == null) {
            throw new Exception("La metrica no existe");
        }
        if (metricaUso.getTiempoUsoMinutos() < 0) {
            throw new Exception("El tiempo de uso no puede ser negativo");
        }
        if (metricaUso.getNivelBateriaInicial() > 100) {
            throw new Exception("El nivel de la bateria inicial no puede ser mayor a 100");
        }
        if (metricaUso.getNivelBateriaFinal() < 0 ) {
            throw new Exception("El nivel de la bateria final no puede ser menor a 0");
        }
        if (metricaUsoDAO.obtener(metricaUso.getUsuario().getId()) == null) {
            throw new Exception("El usuario no existe");
        }
        if (metricaUsoDAO.obtener(metricaUso.getDispositivo().getId()) == null) {
            throw new Exception("El dispositivo no existe");
        }
        
        metricaUsoDAO.actualizar(metricaUso);
    }

    @Override
    public void eliminarMetricaUso(Integer idMetricaUso) throws Exception {
        if (idMetricaUso < 1 || idMetricaUso == null) {
            throw new Exception("La metrica no existe");
        }
        
        metricaUsoDAO.eliminar(idMetricaUso);
    }

    @Override
    public MetricaUso obtenerMetricaUso(int idMetricaUso) throws Exception {
        MetricaUso metricaUso = metricaUsoDAO.obtener(idMetricaUso);
        if (metricaUso == null) {
            throw new Exception("La metrica no existe");
        }
        return metricaUso;
    }

    @Override
    public List<MetricaUso> listarMetricaUso() throws Exception {
        return metricaUsoDAO.listarTodos();
    }
    public Aplicacion obtenerAppMasUsada(){
        return metricaUsoDAO.obtenerAppMasUsada();
    }
    public Dispositivo obtenerDispositivoMasUsado(){
        return metricaUsoDAO.obtenerDispositivoMasUsado();
    }
    public Dispositivo obtenerDispositivoMenosUsado(){
        return metricaUsoDAO.obtenerDispositivoMenosUsado();
    }
}
