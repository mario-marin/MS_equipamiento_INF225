package com.MS_Equipamiento.dao;

import com.MS_Equipamiento.model.Categoria;

import java.util.List;
import java.util.UUID;

public interface CategoriasDao {
    void insertCategoria(Categoria categoria);
    void deleteCategoria(UUID Categoria_id);
    Categoria getCategoria(UUID Categoria_id);
    void updateCategoria(Categoria categoria);
    List<Categoria> getAllCategorias();
}
