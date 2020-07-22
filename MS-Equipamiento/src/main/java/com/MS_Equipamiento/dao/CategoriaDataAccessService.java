package com.MS_Equipamiento.dao;

import com.MS_Equipamiento.model.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Repository("postgres-categorias")
public class CategoriaDataAccessService implements CategoriasDao{

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CategoriaDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void insertCategoria(Categoria categoria) {

        KeyHolder keyHolder = new GeneratedKeyHolder();

        String query = "INSERT INTO categoria ( nombre, descripcion, estado) VALUES (?,?,?)";
        Object[] values = new Object[]{categoria.getNombre(),categoria.getDescripcion(),categoria.getEstado()};
        jdbcTemplate.update(query,values,keyHolder);

        long timestamp = Instant.now().getEpochSecond();

        query = "INSERT INTO log (idequipamiento, idcategoria, accionrealizada, fecha) VALUES (?,?,?,?)";
        values = new Object[]{null,keyHolder.getKey(),10,timestamp};
        jdbcTemplate.update(query,values);
    }

    @Override
    public void deleteCategoria(UUID Categoria_id) {
        String query = "UPDATE categoria SET estado=? WHERE idcategoria=?";
        Object[] values = new Object[]{90,Categoria_id};
        jdbcTemplate.update(query,values);

        long timestamp = Instant.now().getEpochSecond();

        query = "INSERT INTO log (idequipamiento, idcategoria, accionrealizada, fecha) VALUES (?,?,?,?)";
        values = new Object[]{null,Categoria_id,90,timestamp};
        jdbcTemplate.update(query,values);
    }

    @Override
    public Categoria getCategoria(UUID Categoria_id) {
        String query = "SELECT CAST(idcategoria AS VARCHAR), nombre, descripcion, estado FROM categoria WHERE idcategoria=?";
        Object[] values = new Object[]{Categoria_id};
        return (jdbcTemplate.queryForObject(query,values,new CategoriaRowMapper()));
    }

    @Override
    public void updateCategoria(Categoria categoria) {

        UUID categoria_id = categoria.getId();

        Categoria old_categoria = getCategoria(categoria_id);

        if (old_categoria.getEstado() != 90){ //si la categoria esta eliminada entonses no hacer nada
            if (categoria.getDescripcion() != old_categoria.getDescripcion() ){
                String query = "UPDATE categoria SET descripcion=? WHERE idcategoria=?";
                Object[] values = new Object[]{categoria.getDescripcion(),categoria_id};
                jdbcTemplate.update(query,values);

                long timestamp = Instant.now().getEpochSecond();
                KeyHolder keyHolder = new GeneratedKeyHolder();

                query = "INSERT INTO log (idequipamiento, idcategoria, accionrealizada, fecha) VALUES (?,?,?,?)";
                values = new Object[]{null,categoria_id,80,timestamp};
                jdbcTemplate.update(query,values,keyHolder);

                query = "INSERT INTO modificaciones (idmodificacion, idlog, antescambio, cespuescambio) VALUES (?,?,?,?)";
                String AntesCambio = "Descripcion: " + old_categoria.getDescripcion();
                String DespuesCambio = "Descripcion: " + categoria.getDescripcion();
                values = new Object[]{keyHolder.getKey(),AntesCambio,DespuesCambio};
                jdbcTemplate.update(query,values);

            }
            if (categoria.getNombre() != old_categoria.getNombre() ) {
                String query = "UPDATE categoria SET nombre=? WHERE idcategoria=?";
                Object[] values = new Object[]{categoria.getNombre(), categoria_id};
                jdbcTemplate.update(query, values);

                long timestamp = Instant.now().getEpochSecond();
                KeyHolder keyHolder = new GeneratedKeyHolder();

                query = "INSERT INTO log (idequipamiento, idcategoria, accionrealizada, fecha) VALUES (?,?,?,?)";
                values = new Object[]{null,categoria_id,80,timestamp};
                jdbcTemplate.update(query, values, keyHolder);

                query = "INSERT INTO modificaciones (idmodificacion, idlog, antesCambio, despuescambio) VALUES (?,?,?,?)";
                String AntesCambio = "Nombre: " + old_categoria.getNombre();
                String DespuesCambio = "Nombre: " + categoria.getNombre();
                values = new Object[]{keyHolder.getKey(),AntesCambio,DespuesCambio};
                jdbcTemplate.update(query,values);
            }
            if (categoria.getEstado() != old_categoria.getEstado() ){
                String query = "UPDATE categoria SET Estado=? WHERE idCategoria=?";
                Object[] values = new Object[]{categoria.getEstado(),categoria_id};
                jdbcTemplate.update(query,values);

                long timestamp = Instant.now().getEpochSecond();
                KeyHolder keyHolder = new GeneratedKeyHolder();

                query = "INSERT INTO log (idequipamiento, idcategoria, accionrealizada, fecha) VALUES (?,?,?,?)";
                values = new Object[]{null,categoria_id,80,timestamp};
                jdbcTemplate.update(query,values,keyHolder);

                query = "INSERT INTO modificaciones (idmodificacion, idlog, antescambio, despuesCambio) VALUES (?,?,?,?)";
                String AntesCambio = "Estado: " + old_categoria.getEstado();
                String DespuesCambio = "Estado: " + categoria.getEstado();
                values = new Object[]{keyHolder.getKey(),AntesCambio,DespuesCambio};
                jdbcTemplate.update(query,values);
            }
        }
    }

    @Override
    public List<Categoria> getAllCategorias() {
        String query = "SELECT CAST(idcategoria AS VARCHAR),nombre,descripcion,estado FROM categoria";
        Object[] values = new Object[]{};
        return (jdbcTemplate.query(query,values,new CategoriaRowMapper()));
    }
}
