package ar.com.ada.api.empleados.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.empleados.entities.Categoria;
import ar.com.ada.api.empleados.entities.Empleado;
import ar.com.ada.api.empleados.entities.Empleado.EstadoEmpleadoEnum;
import ar.com.ada.api.empleados.models.request.InfoEmpleadoNuevo;
import ar.com.ada.api.empleados.models.response.GenericResponse;
import ar.com.ada.api.empleados.services.CategoriaService;
import ar.com.ada.api.empleados.services.EmpleadoService;

@RestController
public class EmpleadoController {
    
    @Autowired
    private EmpleadoService service;

    @Autowired 
    private CategoriaService categoriaService;

    @PostMapping("/empleadas")
    public ResponseEntity<?> crearEmpleado(@RequestBody InfoEmpleadoNuevo empleadoInfo){
        
        GenericResponse response = new GenericResponse();
        
        Empleado empleado = new Empleado();
        
        empleado.setNombre(empleadoInfo.nombre);
        empleado.setEdad(empleadoInfo.edad);
        empleado.setSueldo(empleadoInfo.sueldo);
        empleado.setFechaAlta(new Date());
        empleado.setEstado(EstadoEmpleadoEnum.ACTIVO);
        
        Categoria categoria = categoriaService.buscarCategoria(empleadoInfo.categoria);
        empleado.setCategoria(categoria);
        
        service.crearEmpleado(empleado);

        response.isOk = true;
        response.id = empleado.getEmpleadoId();
        response.message = "La empleada ha sido creada con exito";
        return ResponseEntity.ok(response);
    }

    @GetMapping("/empleadas")
    public ResponseEntity<List<Empleado>> traerEmpleados(){
        return ResponseEntity.ok(service.traerEmpleados());
    }


}
