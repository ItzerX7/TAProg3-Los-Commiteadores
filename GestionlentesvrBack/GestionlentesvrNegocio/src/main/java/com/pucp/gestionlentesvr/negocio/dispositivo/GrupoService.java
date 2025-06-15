package com.pucp.gestionlentesvr.negocio.dispositivo;

import com.pucp.gestionlentesvr.dominio.dispositivo.Grupo;
import java.util.List;

public interface GrupoService {

    void registrarGrupo(Grupo grupo) throws Exception;

    void actualizarGrupo(Grupo grupo) throws Exception;

    void activarGrupo(Grupo grupo) throws Exception;

    Grupo obtenerGrupo(int idGrupo) throws Exception;

    List<Grupo> listarGrupo() throws Exception;
}
