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
                UUID.fromString(resultSet.getString("categoria.idcategoria")),
                resultSet.getString("categoria.nombre"),
                resultSet.getString("categoria.descripcion"),
                resultSet.getInt("categoria.estado")
        );

        Equipamiento equipamiento = new Equipamiento(
                UUID.fromString(resultSet.getString("equipamiento.idequipamiento")),
                categoria,
                resultSet.getString("equipamiento.nombre"),
                resultSet.getString("equipamiento.descripcion"),
                resultSet.getInt("equipamiento.estado")
        );

        return (equipamiento);
    }
}
