package com.pucp.gestionlentesvr.dominio.usuario;

public class Rol {
    private int id;
    private String nombre;
    private String descripcion;
    private char activo;

    public Rol() {
    }

    public Rol(String nombre, String descripcion, char activo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
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

    public char getActivo() {
        return activo;
    }

    public void setActivo(char activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Rol{");
        sb.append("id=").append(id);
        sb.append(", nombre=").append(nombre);
        sb.append(", descripcion=").append(descripcion);
        sb.append(", activo=").append(activo);
        sb.append('}');
        return sb.toString();
    }
    
}
