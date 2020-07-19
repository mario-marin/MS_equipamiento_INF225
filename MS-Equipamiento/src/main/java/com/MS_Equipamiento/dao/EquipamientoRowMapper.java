package com.MS_Equipamiento.dao;

import com.MS_Equipamiento.model.Categoria;
import com.MS_Equipamiento.model.Equipamiento;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class EquipamientoRowMapper implements RowMapper<Equipamiento> {


    @Override
    public Equipamiento mapRow(ResultSet resultSet, int i) throws SQLException {

        Categoria categoria = new Categoria(
                UUID.fromString(resultSet.getString("categoria.idCategoria")),
                resultSet.getString("categoria.Nombre"),
                resultSet.getString("categoria.Descripcion"),
                resultSet.getInt("categoria.Estado")
        );

        Equipamiento equipamiento = new Equipamiento(
                UUID.fromString(resultSet.getString("equipamiento.idEquipamiento")),
                categoria,
                resultSet.getString("equipamiento.Nombre"),
                resultSet.getString("equipamiento.Descripcion"),
                resultSet.getInt("equipamiento.Estado")
        );

        return (equipamiento);
    }
}
