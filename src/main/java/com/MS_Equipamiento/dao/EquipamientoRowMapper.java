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
                UUID.fromString(resultSet.getString(5)),
                resultSet.getString(6),
                resultSet.getString(7),
                resultSet.getInt(8)
        );

        Equipamiento equipamiento = new Equipamiento(
                UUID.fromString(resultSet.getString(1)),
                categoria,
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getInt(4)
        );

        return (equipamiento);
    }
}
