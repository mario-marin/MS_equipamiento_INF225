package com.MS_Equipamiento.controller;

import com.MS_Equipamiento.dao.EquipamientoDataAccessService;
import com.MS_Equipamiento.model.Equipamiento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@CrossOrigin
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
    @Autowired
    public EquipamientoController(EquipamientoDataAccessService Equip) {
        this.Equip = Equip;
    }
   
  // Aggregate root

    @GetMapping("/equipamientos") //works
    List<Equipamiento> getAllEquipamiento() {
        return Equip.getAllEquipamiento();
    }

    @GetMapping("/equipamientos/categoria/{id}") //works
    List<Equipamiento> getAllEquipamientoFromCategory(@PathVariable UUID id) {
        return Equip.getAllEquipamientoFromCategory(id);
    }

    @PostMapping("/equipamientos") //works
    void newEquipamiento(@RequestBody Map<String, Object> request) {

        UUID idcategoria = UUID.fromString((String)request.get("idcategoria"));
        String nombre = (String)request.get("nombre");
        String descripcion = (String)request.get("descripcion");
        int estado = (int)request.get("estado");

        Equipamiento newEquipamiento = new Equipamiento(idcategoria,nombre,descripcion,estado);

        Equip.insertEquipamiento(newEquipamiento);
    }

  // Single item

    @GetMapping("/equipamientos/{id}") //works
    Equipamiento one(@PathVariable UUID id) {
        return(Equip.getEquipamiento(id));
    }

    @PutMapping("/equipamientos") //works
    void updateEquipamiento(@RequestBody Map<String, Object> request) {

        UUID idqeuipamiento = UUID.fromString((String)request.get("idequipamiento"));
        UUID idcategoria = UUID.fromString((String)request.get("idcategoria"));
        String nombre = (String)request.get("nombre");
        String descripcion = (String)request.get("descripcion");
        int estado = (int)request.get("estado");

        Equipamiento newEquipamiento = new Equipamiento(idqeuipamiento,idcategoria,nombre,descripcion,estado);

        Equip.updateEquipamiento(newEquipamiento);
    }

    @DeleteMapping("/equipamientos/delete/{id}") //works
    void deleteEquipamiento(@PathVariable UUID id) {
        Equip.deleteEquipamiento(id);
    }

}
    //