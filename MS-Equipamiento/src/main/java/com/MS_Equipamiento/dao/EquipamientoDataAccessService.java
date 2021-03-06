package com.MS_Equipamiento.dao;

import com.MS_Equipamiento.model.Equipamiento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Repository("postgres-equipamiento")
public class EquipamientoDataAccessService implements EquipamientoDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public EquipamientoDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insertEquipamiento(Equipamiento equipamiento) {



        String query = "INSERT INTO equipamiento (idequipamiento, idcategoria, nombre, descripcion, estado) VALUES (?,?,?,?,?)";
        Object[] values = new Object[]{equipamiento.getId(),equipamiento.getCategoria().getId(),equipamiento.getName(),equipamiento.getDescripsion(),equipamiento.getEstado()};
        jdbcTemplate.update(query,values);

        long timestamp = Instant.now().getEpochSecond();
        UUID idlog = UUID.randomUUID();

        query = "INSERT INTO LOG (idlog, idequipamiento, idcategoria, accionRealizada, fecha) VALUES (?,?,?,?,?)";
        values = new Object[]{idlog,equipamiento.getId(),null,100,timestamp};
        jdbcTemplate.update(query,values);
    }

    @Override
    public void deleteEquipamiento(UUID equipamiento_id) {
        String query = "UPDATE equipamiento SET estado=? WHERE idequipamiento=?";
        Object[] values = new Object[]{900,equipamiento_id};
        jdbcTemplate.update(query,values);

        long timestamp = Instant.now().getEpochSecond();
        UUID idlog = UUID.randomUUID();

        query = "INSERT INTO LOG (idlog, idequipamiento, idcategoria, accionRealizada, fecha) VALUES (?,?,?,?,?)";
        values = new Object[]{idlog,equipamiento_id,null,900,timestamp};
        jdbcTemplate.update(query,values);
    }

    @Override
    public Equipamiento getEquipamiento(UUID equipamiento_id) {
        String query = "SELECT CAST(equipamiento.idequipamiento AS VARCHAR), equipamiento.nombre, equipamiento.descripcion, equipamiento.estado, categoria.idcategoria, categoria.nombre, categoria.descripcion, categoria.estado FROM equipamiento INNER JOIN categoria ON equipamiento.idcategoria=categoria.idcategoria WHERE idequipamiento=?";
        Object[] values = new Object[]{equipamiento_id};
        return (jdbcTemplate.queryForObject(query,values,new EquipamientoRowMapper()));
    }

    @Override
    public void updateEquipamiento(Equipamiento equipamiento) {

        UUID equipamiento_id = equipamiento.getId();

        Equipamiento old_equipamiento = getEquipamiento(equipamiento_id);

        if (old_equipamiento.getEstado() != 900){ //si el equipo esta eliminado entonses no hacer nada
            if (!equipamiento.getDescripsion().equals(old_equipamiento.getDescripsion()) ){
                String query = "UPDATE equipamiento SET descripcion=? WHERE idequipamiento=?";
                Object[] values = new Object[]{equipamiento.getDescripsion(),equipamiento_id};
                jdbcTemplate.update(query,values);

                long timestamp = Instant.now().getEpochSecond();
                UUID idlog = UUID.randomUUID();

                query = "INSERT INTO log (idlog, idequipamiento, idcategoria, accionrealizada, fecha) VALUES (?,?,?,?,?)";
                values = new Object[]{idlog,equipamiento_id,null,800,timestamp};
                jdbcTemplate.update(query,values);

                query = "INSERT INTO modificaciones ( idlog, antescambio, despuescambio) VALUES (?,?,?)";
                String AntesCambio = "Descripcion: " + old_equipamiento.getDescripsion();
                String DespuesCambio = "Descripcion: " + equipamiento.getDescripsion();
                values = new Object[]{idlog,AntesCambio,DespuesCambio};
                jdbcTemplate.update(query,values);

            }
            if (!equipamiento.getName().equals(old_equipamiento.getName()) ) {
                String query = "UPDATE equipamiento SET nombre=? WHERE idequipamiento=?";
                Object[] values = new Object[]{equipamiento.getName(), equipamiento_id};
                jdbcTemplate.update(query, values);

                long timestamp = Instant.now().getEpochSecond();
                UUID idlog = UUID.randomUUID();

                query = "INSERT INTO log (idlog,idequipamiento, idcategoria, accionrealizada, fecha) VALUES (?,?,?,?,?)";
                values = new Object[]{idlog,equipamiento_id, null, 800, timestamp};
                jdbcTemplate.update(query, values);

                query = "INSERT INTO modificaciones (idlog, antescambio, despuescambio) VALUES (?,?,?)";
                String AntesCambio = "Nombre: " + old_equipamiento.getName();
                String DespuesCambio = "Nombre: " + equipamiento.getName();
                values = new Object[]{idlog,AntesCambio,DespuesCambio};
                jdbcTemplate.update(query,values);
            }

            if (!equipamiento.getCategoria().getId().equals(old_equipamiento.getCategoria().getId()) ){


                String query = "ALTER TABLE equipamiento DROP CONSTRAINT equipamiento_idcategoria_fkey";
                jdbcTemplate.update(query);

                query = "UPDATE equipamiento SET idcategoria=? WHERE idequipamiento=?";
                Object[] values = new Object[]{equipamiento.getCategoria().getId(),equipamiento_id};
                jdbcTemplate.update(query,values);

                query = "ALTER TABLE equipamiento ADD CONSTRAINT equipamiento_idcategoria_fkey FOREIGN KEY (idcategoria) REFERENCES categoria(idcategoria)";
                jdbcTemplate.update(query);

                long timestamp = Instant.now().getEpochSecond();
                UUID idlog = UUID.randomUUID();

                query = "INSERT INTO log (idlog,idequipamiento, idcategoria, accionrealizada, fecha) VALUES (?,?,?,?,?)";
                values = new Object[]{idlog,equipamiento_id,null,800,timestamp};
                jdbcTemplate.update(query,values);

                query = "INSERT INTO modificaciones (idlog, antescambio, despuescambio) VALUES (?,?,?)";
                String AntesCambio = "idCategoria: " + old_equipamiento.getCategoria().getId().toString();
                String DespuesCambio = "idCategoria: " + equipamiento.getCategoria().getId().toString();
                values = new Object[]{idlog,AntesCambio,DespuesCambio};
                jdbcTemplate.update(query,values);



            }
            if (equipamiento.getEstado() != old_equipamiento.getEstado() ){

                String query = "UPDATE equipamiento SET estado=? WHERE idequipamiento=?";
                Object[] values = new Object[]{equipamiento.getEstado(),equipamiento_id};
                jdbcTemplate.update(query,values);

                long timestamp = Instant.now().getEpochSecond();
                UUID idlog = UUID.randomUUID();

                query = "INSERT INTO log (idlog,idequipamiento, idcategoria, accionrealizada, fecha) VALUES (?,?,?,?,?)";
                values = new Object[]{idlog,equipamiento_id,null,800,timestamp};
                jdbcTemplate.update(query,values);

                query = "INSERT INTO modificaciones (idlog, antescambio, despuescambio) VALUES (?,?,?)";
                String AntesCambio = "Estado: " + old_equipamiento.getEstado();
                String DespuesCambio = "Estado: " + equipamiento.getEstado();
                values = new Object[]{idlog,AntesCambio,DespuesCambio};
                jdbcTemplate.update(query,values);


            }
        }
    }

    @Override
    public List<Equipamiento> getAllEquipamiento() { //works
        String query = "SELECT CAST(equipamiento.idequipamiento AS VARCHAR), equipamiento.nombre, equipamiento.descripcion, equipamiento.estado, CAST(categoria.idcategoria AS VARCHAR), categoria.nombre, categoria.descripcion, categoria.estado FROM equipamiento INNER JOIN categoria ON equipamiento.idcategoria=categoria.idcategoria";
        Object[] values = new Object[]{};
        return (jdbcTemplate.query(query,values,new EquipamientoRowMapper()));
    }

    @Override
    public List<Equipamiento> getAllEquipamientoFromCategory(UUID id_categoria) {
        String query = "SELECT CAST(equipamiento.idequipamiento AS VARCHAR), equipamiento.nombre, equipamiento.descripcion, equipamiento.estado, CAST(categoria.idcategoria AS VARCHAR), categoria.nombre, categoria.descripcion, categoria.estado FROM equipamiento INNER JOIN categoria ON equipamiento.idcategoria=categoria.idcategoria WHERE equipamiento.idcategoria=?";
        Object[] values = new Object[]{id_categoria};
        return (jdbcTemplate.query(query,values,new EquipamientoRowMapper()));
    }


}
