package com.pucp.gestionlentesvr.persistencia;

import java.util.List;

public interface BaseDAO<T> {

    void agregar(T entidad);

    T obtener(Integer id);

    List<T> listarTodos();

    void actualizar(T entidad);

    void setActivo(Integer id);
}
