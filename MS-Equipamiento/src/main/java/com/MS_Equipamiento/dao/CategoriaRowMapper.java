package com.MS_Equipamiento.dao;

import com.MS_Equipamiento.model.Categoria;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class CategoriaRowMapper implements RowMapper<Categoria> {


    @Override
    public Categoria mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Categoria(
                UUID.fromString(resultSet.getString("idcategoria")),
                resultSet.getString("nombre"),
                resultSet.getString("descripcion"),
                resultSet.getInt("estado")
        );
    }
}
