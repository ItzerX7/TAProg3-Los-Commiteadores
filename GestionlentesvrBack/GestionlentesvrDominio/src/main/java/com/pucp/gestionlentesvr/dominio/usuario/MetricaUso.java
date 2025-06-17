package com.pucp.gestionlentesvr.dominio.usuario;

import com.pucp.gestionlentesvr.dominio.dispositivo.Dispositivo;
import java.util.Date;

public class MetricaUso {

    private int id;
    private Date fechaRegistro;
    private int tiempoUsoMinutos;
    private int nivelBateriaInicial;
    private int nivelBateriaFinal;
    private Usuario usuario;
    private Dispositivo dispositivo;
    private char activo;

    public MetricaUso() {
    }

    public MetricaUso(Date fechaRegistro, int tiempoUsoMinutos, int nivelBateriaInicial,
            int nivelBateriaFinal, Usuario usuario, Dispositivo dispositivo, char activo) {
        this.fechaRegistro = fechaRegistro;
        this.tiempoUsoMinutos = tiempoUsoMinutos;
        this.nivelBateriaInicial = nivelBateriaInicial;
        this.nivelBateriaFinal = nivelBateriaFinal;
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

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public int getTiempoUsoMinutos() {
        return tiempoUsoMinutos;
    }

    public void setTiempoUsoMinutos(int tiempoUsoMinutos) {
        this.tiempoUsoMinutos = tiempoUsoMinutos;
    }

    public int getNivelBateriaInicial() {
        return nivelBateriaInicial;
    }

    public void setNivelBateriaInicial(int nivelBateriaInicial) {
        this.nivelBateriaInicial = nivelBateriaInicial;
    }

    public int getNivelBateriaFinal() {
        return nivelBateriaFinal;
    }

    public void setNivelBateriaFinal(int nivelBateriaFinal) {
        this.nivelBateriaFinal = nivelBateriaFinal;
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
        sb.append("MetricaUso{");
        sb.append("id=").append(id);
        sb.append(", fechaRegistro=").append(fechaRegistro);
        sb.append(", tiempoUsoMinutos=").append(tiempoUsoMinutos);
        sb.append(", nivelBateriaInicial=").append(nivelBateriaInicial);
        sb.append(", nivelBateriaFinal=").append(nivelBateriaFinal);
        sb.append(", usuario=").append(usuario);
        sb.append(", dispositivo=").append(dispositivo);
        sb.append(", activo=").append(activo);
        sb.append('}');
        return sb.toString();
    }

}
