package com.pucp.gestionlentesvr.dominio.configuracion;

import com.pucp.gestionlentesvr.dominio.usuario.Usuario;
import java.sql.Timestamp;

public class Configuracion {

    private int id;
    private String nombre;
    private String descripcion;
    private Timestamp fechaCreacion;
    private String valor;
    private TipoConfiguracion tipo;
    private char activo;

    public Configuracion() {
    }

    public Configuracion(String nombre, String descripcion, Timestamp fechaCreacion,
            String valor, TipoConfiguracion tipo, char activo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaCreacion = new Timestamp(System.currentTimeMillis());
        this.valor = valor;
        this.tipo = tipo;
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

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public TipoConfiguracion getTipo() {
        return tipo;
    }

    public void setTipo(TipoConfiguracion tipo) {
        this.tipo = tipo;
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
        sb.append("Configuracion{");
        sb.append("id=").append(id);
        sb.append(", nombre=").append(nombre);
        sb.append(", descripcion=").append(descripcion);
        sb.append(", fechaCreacion=").append(fechaCreacion);
        sb.append(", valor=").append(valor);
        sb.append(", tipo=").append(tipo);
        sb.append(", activo=").append(activo);
        sb.append('}');
        return sb.toString();
    }

}
