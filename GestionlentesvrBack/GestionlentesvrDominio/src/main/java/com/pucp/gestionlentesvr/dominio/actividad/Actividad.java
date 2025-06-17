package com.pucp.gestionlentesvr.dominio.actividad;

import com.pucp.gestionlentesvr.dominio.dispositivo.Dispositivo;
import com.pucp.gestionlentesvr.dominio.usuario.Usuario;
import java.sql.Timestamp;

public class Actividad {

    private int id;
    private Timestamp fechaHora;
    private String descripcion;
    private String detallesTecnicos;
    private TipoActividad tipo;
    private Usuario usuario;
    private Dispositivo dispositivo;
    private char activo;

    public Actividad() {
    }

    public Actividad(Timestamp fechaHora, String descripcion, String detallesTecnicos,
            TipoActividad tipo, Usuario usuario, Dispositivo dispositivo, char activo) {
        this.fechaHora = new Timestamp(System.currentTimeMillis());
        this.descripcion = descripcion;
        this.detallesTecnicos = detallesTecnicos;
        this.tipo = tipo;
        this.usuario = usuario;
        this.dispositivo = dispositivo;
        this.activo = 's';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Timestamp fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDetallesTecnicos() {
        return detallesTecnicos;
    }

    public void setDetallesTecnicos(String detallesTecnicos) {
        this.detallesTecnicos = detallesTecnicos;
    }

    public TipoActividad getTipo() {
        return tipo;
    }

    public void setTipo(TipoActividad tipo) {
        this.tipo = tipo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Dispositivo getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(Dispositivo dispositivo) {
        this.dispositivo = dispositivo;
    }

    public char getActivo() {
        return activo;
    }

    public void setActivo(char activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Actividad{");
        sb.append("id=").append(id);
        sb.append(", fechaHora=").append(fechaHora);
        sb.append(", descripcion=").append(descripcion);
        sb.append(", detallesTecnicos=").append(detallesTecnicos);
        sb.append(", tipo=").append(tipo);
        sb.append(", usuario=").append(usuario);
        sb.append(", dispositivo=").append(dispositivo);
        sb.append(", activo=").append(activo);
        sb.append('}');
        return sb.toString();
    }

}
