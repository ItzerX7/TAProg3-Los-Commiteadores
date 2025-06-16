package com.pucp.gestionlentesvr.persistencia;

import java.util.List;

public interface BaseDAO<T> {

    void agregar(T entidad);

    T obtener(Integer id);

    void actualizar(T entidad);

    void eliminar(Integer id);

    List<T> listarTodos();
}
