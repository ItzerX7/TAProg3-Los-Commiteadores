package com.pucp.gestionlentesvr.dominio.dispositivo;

import java.sql.Timestamp;

public class Grupo {

    private int id;
    private String nombre;
    private String descripcion;
    private Timestamp fechaCreacion;
    private String ubicacion;
    private char activo;

    public Grupo() {
    }

    public Grupo(String nombre, String descripcion, Timestamp fechaCreacion, String ubicacion, char activo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaCreacion = new Timestamp(System.currentTimeMillis());
        this.ubicacion = ubicacion;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
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
        sb.append("Grupo{");
        sb.append("id=").append(id);
        sb.append(", nombre=").append(nombre);
        sb.append(", descripcion=").append(descripcion);
        sb.append(", fechaCreacion=").append(fechaCreacion);
        sb.append(", ubicacion=").append(ubicacion);
        sb.append(", activo=").append(activo);
        sb.append('}');
        return sb.toString();
    }

}
