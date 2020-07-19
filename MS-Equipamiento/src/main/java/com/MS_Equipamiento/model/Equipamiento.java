package com.MS_Equipamiento.model;

import java.util.UUID;

public class Equipamiento {
    private final UUID id;
    private Categoria categoria;
    private String name;
    private String descripsion;
    private long estado;


    public Equipamiento(UUID id, Categoria categoria, String name, String descripsion, int estado) {
        this.id = id;
        this.categoria = categoria;
        this.name = name;
        this.descripsion = descripsion;
        this.estado = estado;
    }

    public UUID getId() {
        return id;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public String getName() {
        return name;
    }

    public String getDescripsion() {
        return descripsion;
    }

    public long getEstado() {
        return estado;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescripsion(String descripsion) {
        this.descripsion = descripsion;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
