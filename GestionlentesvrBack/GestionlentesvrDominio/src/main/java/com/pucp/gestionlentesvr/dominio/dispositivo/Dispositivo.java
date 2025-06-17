package com.pucp.gestionlentesvr.dominio.dispositivo;

import java.util.Date;

public class Dispositivo {

    private int id;
    private String nombre;
    private String modelo;
    private String numeroSerie;
    private Date fechaRegistro;
    private String ubicacion;
    private int nivelBateria;
    private Date ultimaConexion;
    private EstadoConexion estado;
    private Grupo grupo;
    private char activo;

    public Dispositivo() {
        
    }

    public Dispositivo(String nombre, String modelo, String numeroSerie, Date fechaRegistro,
            String ubicacion, int nivelBateria, Date ultimaConexion, EstadoConexion estado,
            Grupo grupo, char activo) {
        this.nombre = nombre;
        this.modelo = modelo;
        this.numeroSerie = numeroSerie;
        this.fechaRegistro = new Date(System.currentTimeMillis());
        this.ubicacion = ubicacion;
        this.nivelBateria = nivelBateria;
        this.ultimaConexion = ultimaConexion;
        this.estado = estado;
        this.grupo = grupo;
        this.activo = 's';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getNivelBateria() {
        return nivelBateria;
    }

    public void setNivelBateria(int nivelBateria) {
        this.nivelBateria = nivelBateria;
    }

    public Date getUltimaConexion() {
        return ultimaConexion;
    }

    public void setUltimaConexion(Date ultimaConexion) {
        this.ultimaConexion = ultimaConexion;
    }

    public EstadoConexion getEstado() {
        return estado;
    }

    public void setEstado(EstadoConexion estado) {
        this.estado = estado;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
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
        sb.append("Dispositivo{");
        sb.append("id=").append(id);
        sb.append(", nombre=").append(nombre);
        sb.append(", modelo=").append(modelo);
        sb.append(", numeroSerie=").append(numeroSerie);
        sb.append(", fechaRegistro=").append(fechaRegistro);
        sb.append(", ubicacion=").append(ubicacion);
        sb.append(", nivelBateria=").append(nivelBateria);
        sb.append(", ultimaConexion=").append(ultimaConexion);
        sb.append(", estado=").append(estado);
        sb.append(", grupo=").append(grupo);
        sb.append(", activo=").append(activo);
        sb.append('}');
        return sb.toString();
    }
    
}
