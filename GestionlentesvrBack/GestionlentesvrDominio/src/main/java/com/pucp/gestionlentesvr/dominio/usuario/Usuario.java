package com.pucp.gestionlentesvr.dominio.usuario;

import java.util.Date;

public class Usuario {

    private int id;
    private String nombre;
    private String apellido;
    private String correo;
    private String contrasena;
    private Date fechaCreacion;
    private Rol rol;
    private char activo;

    public Usuario() {
    }

    public Usuario(String nombre, String apellido, String correo, String contrasena,
            Date fechaCreacion, Rol rol, char activo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.contrasena = contrasena;
        this.fechaCreacion = new Date(System.currentTimeMillis());
        this.rol = rol;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
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
        sb.append("Usuario{");
        sb.append("id=").append(id);
        sb.append(", nombre=").append(nombre);
        sb.append(", apellido=").append(apellido);
        sb.append(", correo=").append(correo);
        sb.append(", contrasena=").append(contrasena);
        sb.append(", fechaCreacion=").append(fechaCreacion);
        sb.append(", rol=").append(rol);
        sb.append(", activo=").append(activo);
        sb.append('}');
        return sb.toString();
    }

}
