package com.MS_Equipamiento.controller;

import com.MS_Equipamiento.dao.CategoriaDataAccessService;
import com.MS_Equipamiento.model.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.List;
import java.util.UUID;
import java.util.Map;

@CrossOrigin
@RestController
/***@RequestMapping("/postgressApp")

public class ApplicationController {

@Resource 

CategoriaDataAccessService CategoriaDataAccessService;

@PostMapping(value = "/createEq")
public void insertCategoria(@RequestBody Categoria eq) {
    CategoriaDataAccessService.insertCategoria(eq);

}

@DeleteMapping(value = "/deleteEq")
public void deleteCategoria(@RequestBody UUID id) {
    CategoriaDataAccessService.deleteCategoria(id);

}

@GetMapping(value = "/getCat")
public void getCategoria(@RequestBody UUID id) {
    CategoriaDataAccessService.getCategoria(id);

}

@PutMapping(value = "/updateEq")
public void updateCategoria(@RequestBody Categoria eq) {
    CategoriaDataAccessService.updateCategoria(eq);

}

@GetMapping(value = "/CatList")
public List<Categoria> getCategorias() {
    CategoriaDataAccessService.getAllCategoria();
    
}

@GetMapping(value = "/")
public List<Categoria> getAllCategoriaFromCategory(@RequestBody UUID id) {
    CategoriaDataAccessService.getAllCategoriaFromCategory(id);

}

@Resource

CategoriaDataAccessService CategoriaDataAccessService;

@PostMapping(value = "/createCat")

public void insertCategoria(@RequestBody Categoria cat) {

    CategoriaDataAccessService.insertCategoria(cat);

}

}
***/

public class CategoriaController {

    private final CategoriaDataAccessService Cat;
    @Autowired
    public CategoriaController(CategoriaDataAccessService Cat) {
        this.Cat = Cat;
    }
   
  // Aggregate root

    @GetMapping("/categorias") // it works!
    List<Categoria> getAllCategorias() {
        return Cat.getAllCategorias();
    }

    @PostMapping("/categorias") // fixed
    void newCategoria(@RequestBody Map<String, Object> request) {
        String nombre = (String)request.get("nombre");
        String descripcion = (String)request.get("descripcion");
        int estado = (int)request.get("estado");

        Categoria newCategoria;
        newCategoria = new Categoria(nombre, descripcion, estado);

        Cat.insertCategoria(newCategoria);
    }

  // Single item

    @GetMapping("/categorias/{id}") // it works!
    Categoria one(@PathVariable UUID id) {
        return Cat.getCategoria(id);
    }

    @PutMapping("/categorias") // works
    void updateCategoria(@RequestBody Map<String, Object> request) {
        UUID id = UUID.fromString((String)request.get("idcategoria"));
        String nombre = (String)request.get("nombre");
        String descripcion = (String)request.get("descripcion");
        int estado = (int)request.get("estado");

        Categoria newCategoria = new Categoria(id,nombre, descripcion, estado);

        Cat.updateCategoria(newCategoria);
    }

    @DeleteMapping("/categorias/{id}") //works
    void deleteCategoria(@PathVariable("id") UUID id) {
        Cat.deleteCategoria(id);
    }

}
    //