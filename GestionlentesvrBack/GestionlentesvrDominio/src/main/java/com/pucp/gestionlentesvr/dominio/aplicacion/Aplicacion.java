package com.pucp.gestionlentesvr.dominio.aplicacion;

public class Aplicacion {

    private int id;
    private String nombre;
    private String version;
    private String desarrollador;
    private String descripcion;
    private double tamanomb;
    private CategoriaAplicacion categoria;
    private char activo;

    public Aplicacion() {
    }

    public Aplicacion(String nombre, String version, String desarrollador, String descripcion,
            double tamanomb, CategoriaAplicacion categoria, char activo) {
        this.nombre = nombre;
        this.version = version;
        this.desarrollador = desarrollador;
        this.descripcion = descripcion;
        this.tamanomb = tamanomb;
        this.categoria = categoria;
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

    public String getDesarrollador() {
        return desarrollador;
    }

    public void setDesarrollador(String desarrollador) {
        this.desarrollador = desarrollador;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getTamanomb() {
        return tamanomb;
    }

    public void setTamanomb(double tamanomb) {
        this.tamanomb = tamanomb;
    }

    public CategoriaAplicacion getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaAplicacion categoria) {
        this.categoria = categoria;
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
        sb.append("Aplicacion{");
        sb.append("id=").append(id);
        sb.append(", nombre=").append(nombre);
        sb.append(", version=").append(version);
        sb.append(", desarrollador=").append(desarrollador);
        sb.append(", descripcion=").append(descripcion);
        sb.append(", tamanomb=").append(tamanomb);
        sb.append(", categoria=").append(categoria);
        sb.append(", activo=").append(activo);
        sb.append('}');
        return sb.toString();
    }

}
