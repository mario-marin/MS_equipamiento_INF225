package com.MS_Equipamiento.dao;

import com.MS_Equipamiento.model.Equipamiento;

import java.util.UUID;

public interface EquipamientoDao {
    void insertEquipamiento(Equipamiento equipamiento);
    void deleteEquipamiento(UUID equipamiento_id);
    Equipamiento getEquipamiento(UUID equipamiento_id);
    void updateEquipamiento(Equipamiento equipamiento);
}
