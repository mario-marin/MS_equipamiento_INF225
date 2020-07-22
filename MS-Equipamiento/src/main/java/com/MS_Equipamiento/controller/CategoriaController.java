package com.MS_Equipamiento.controller;

import com.MS_Equipamiento.dao.CategoriaDataAccessService;
import com.MS_Equipamiento.model.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import javax.annotation.Resource;

import java.util.List;
import java.util.UUID;

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

    @GetMapping("/categorias")
    List<Categoria> getAllCategorias() {
        return Cat.getAllCategorias();
    }

    @PostMapping("/categorias")
    void newCategoria(@RequestBody Categoria newCategoria) {
        Cat.insertCategoria(newCategoria);
    }

  // Single item

    @GetMapping("/categorias/{id}")
    void one(@PathVariable UUID id) {
        Cat.getCategoria(id);
    }

    @PutMapping("/categorias")
    void updateCategoria(@RequestBody Categoria categoria) {
        Cat.updateCategoria(categoria);
    }

    @DeleteMapping("/categorias/{id}")
    void deleteCategoria(@PathVariable UUID id) {
        Cat.deleteCategoria(id);
    }

}
    //