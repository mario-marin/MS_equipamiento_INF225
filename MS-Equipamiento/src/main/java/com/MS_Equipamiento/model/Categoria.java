package com.MS_Equipamiento.model;

import java.util.UUID;

public class Categoria {
    private final UUID id;
    private String nombre;
    private String descripcion;
    private int estado;


    public Categoria(UUID id, String nombre, String descripcion, int estado) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public Categoria(String nombre, String descripcion, int estado) {
        this.id = UUID.randomUUID();;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public UUID getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getEstado() {
        return estado;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
