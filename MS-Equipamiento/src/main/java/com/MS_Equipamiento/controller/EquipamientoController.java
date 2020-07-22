package com.MS_Equipamiento.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import com.MS_Equipamiento.model.Equipamiento;
import com.MS_Equipamiento.dao.EquipamientoDataAccessService;

import com.MS_Equipamiento.model.Categoria;
import com.MS_Equipamiento.dao.CategoriaDataAccessService;
import java.util.UUID;

@RestController
/***@RequestMapping("/postgressApp")

public class ApplicationController {

@Resource 

EquipamientoDataAccessService EquipamientoDataAccessService;

@PostMapping(value = "/createEq")
public void insertEquipamiento(@RequestBody Equipamiento eq) {
    EquipamientoDataAccessService.insertEquipamiento(eq);

}

@DeleteMapping(value = "/deleteEq")
public void deleteEquipamiento(@RequestBody UUID id) {
    EquipamientoDataAccessService.deleteEquipamiento(id);

}

@GetMapping(value = "/getEquip")
public void getEquipamiento(@RequestBody UUID id) {
    EquipamientoDataAccessService.getEquipamiento(id);

}

@PutMapping(value = "/updateEq")
public void updateEquipamiento(@RequestBody Equipamiento eq) {
    EquipamientoDataAccessService.updateEquipamiento(eq);

}

@GetMapping(value = "/equipList")
public List<Equipamiento> getEquipamientos() {
    EquipamientoDataAccessService.getAllEquipamiento();
    
}

@GetMapping(value = "/")
public List<Equipamiento> getAllEquipamientoFromCategory(@RequestBody UUID id) {
    EquipamientoDataAccessService.getAllEquipamientoFromCategory(id);

}

@Resource

CategoriaDataAccessService CategoriaDataAccessService;

@PostMapping(value = "/createCat")

public void insertCategoria(@RequestBody Categoria cat) {

    CategoriaDataAccessService.insertCategoria(cat);

}

}
***/

public class EquipamientoController {

    private final EquipamientoDataAccessService Equip;
    
    EquipamientoController(EquipamientoDataAccessService Equip) {
        this.Equip = Equip;
    }
   
  // Aggregate root

    @GetMapping("/equipamientos")
    List<Equipamiento> getAllEquipamiento() {
        return Equip.getAllEquipamiento();
    }

    @PostMapping("/equipamientos")
    void newEquipamiento(@RequestBody Equipamiento newEquipamiento) {
        Equip.insertEquipamiento(newEquipamiento);
    }

  // Single item

    @GetMapping("/equipamientos/{id}")
    void one(@PathVariable UUID id) {
        Equip.getEquipamiento(id);
    }

    @PutMapping("/equipamientos")
    void updateEquipamiento(@RequestBody Equipamiento equipamiento) {
        Equip.updateEquipamiento(equipamiento);
    }

    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable UUID id) {
        Equip.deleteEquipamiento(id);
    }

}
    //