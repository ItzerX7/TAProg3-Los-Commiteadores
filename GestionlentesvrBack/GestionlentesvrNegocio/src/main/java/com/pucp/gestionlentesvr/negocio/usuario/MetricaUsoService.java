package com.pucp.gestionlentesvr.negocio.usuario;

import com.pucp.gestionlentesvr.dominio.usuario.MetricaUso;
import java.util.List;

public interface MetricaUsoService {

    void registrarMetricaUso(MetricaUso metricaUso) throws Exception;

    void actualizarMetricaUso(MetricaUso metricaUso) throws Exception;

    void eliminarMetricaUso(Integer idMetricaUso) throws Exception;

    MetricaUso obtenerMetricaUso(int idMetricaUso) throws Exception;

    List<MetricaUso> listarMetricaUso() throws Exception;
}
