package ar.com.ada.api.empleados.services;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.empleados.entities.Categoria;
import ar.com.ada.api.empleados.entities.Empleado;
import ar.com.ada.api.empleados.entities.Empleado.EstadoEmpleadoEnum;
import ar.com.ada.api.empleados.repos.EmpleadoRepository;

@Service
public class EmpleadoService {
    
    @Autowired
    EmpleadoRepository repo;

    @Autowired
    CategoriaService categoriaService;

    public void crearEmpleado(Empleado empleado){
        repo.save(empleado);
    }

    public List<Empleado> traerEmpleados(){
        return repo.findAll();
    }

    public Empleado buscarEmpleadoById(Integer id){
        Optional<Empleado> result = repo.findById(id);
        if (result.isPresent())
            return result.get();
        return null;
    }

    // DELETE LOGICO
    public void bajaEmpleadoPorId(Integer id){
        Empleado empleado = this.buscarEmpleadoById(id);
        empleado.setFechaBaja(new Date());
        empleado.setEstado(EstadoEmpleadoEnum.BAJA);
        repo.save(empleado);
    }

    public List<Empleado> traerEmpleadaPorCategoria(Integer catId) {
		
        Categoria categoria = categoriaService.buscarCategoria(catId);
        return categoria.getEmpleados();

	}

    public void cambiarSueldo(Empleado empleado, BigDecimal sueldo){
        empleado.setSueldo(sueldo);
        repo.save(empleado);
    }

}
