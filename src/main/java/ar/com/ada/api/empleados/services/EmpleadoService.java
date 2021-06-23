package ar.com.ada.api.empleados.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.empleados.entities.Empleado;
import ar.com.ada.api.empleados.repos.EmpleadoRepository;

@Service
public class EmpleadoService {
    
    @Autowired
    EmpleadoRepository repo;

    public void crearEmpleado(Empleado empleado){
        repo.save(empleado);
    }

    public List<Empleado> traerEmpleados(){
        return repo.findAll();
    }

    public Empleado buscarEmpleadoById(Integer id){
        Optional<Empleado> result = repo.findById(id);
        Empleado empleado = null;
        if (result.isPresent()){
            empleado = result.get();
        }
        return empleado;
    }
}
