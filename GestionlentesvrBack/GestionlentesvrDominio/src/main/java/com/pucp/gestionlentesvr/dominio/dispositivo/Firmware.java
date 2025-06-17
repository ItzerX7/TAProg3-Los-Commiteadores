package com.pucp.gestionlentesvr.dominio.dispositivo;

public class Firmware {

    private int id;
    private String nombre;
    private String version;
    private String descripcion;
    private char activo;

    public Firmware() {
    }

    public Firmware(String nombre, String version, String descripcion, char activo) {
        this.nombre = nombre;
        this.version = version;
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
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
        sb.append("Firmware{");
        sb.append("id=").append(id);
        sb.append(", nombre=").append(nombre);
        sb.append(", version=").append(version);
        sb.append(", descripcion=").append(descripcion);
        sb.append(", activo=").append(activo);
        sb.append('}');
        return sb.toString();
    }

}
