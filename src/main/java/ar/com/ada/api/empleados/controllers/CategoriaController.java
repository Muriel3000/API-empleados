package ar.com.ada.api.empleados.controllers;

import ar.com.ada.api.empleados.entities.Categoria;
import ar.com.ada.api.empleados.entities.Empleado;
import ar.com.ada.api.empleados.models.response.GenericResponse;
import ar.com.ada.api.empleados.services.CategoriaService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoriaController {
    
    @Autowired
    private CategoriaService service;
    
    @PostMapping("/categorias")
    public ResponseEntity<?> crearCategoria(@RequestBody Categoria categoria) {
        GenericResponse response = new GenericResponse();
        service.crearCategoria(categoria);
        response.isOk = true;
        response.id = categoria.getCategoriaId();
        response.message = "La categoria fue creada con exito.";
        return ResponseEntity.ok(response);
    }

    @GetMapping("/categorias")
    public ResponseEntity<List<Categoria>> traerCategorias(){
        return ResponseEntity.ok(service.traerCategorias());
    }

    @GetMapping("/categorias/{catId}")
    public ResponseEntity<List<Empleado>> traerEmpleadosDeCategoria(@PathVariable("catId") int id){
        return ResponseEntity.ok(service.traerEmpleadosDeCategoria(id));
    }
}
