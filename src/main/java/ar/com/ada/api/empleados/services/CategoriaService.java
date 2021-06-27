package ar.com.ada.api.empleados.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.empleados.entities.Categoria;
import ar.com.ada.api.empleados.entities.Empleado;
import ar.com.ada.api.empleados.models.response.InfoMinimaEmpleado;
import ar.com.ada.api.empleados.repos.CategoriaRepository;
import java.util.*;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository repository;

    public void crearCategoria(Categoria categoria){
        repository.save(categoria);
    }
    
    public List<Categoria> traerCategorias(){
        return repository.findAll();
    }

    public Categoria buscarCategoria(Integer id){
        Optional<Categoria> resultado = repository.findById(id); // metodo 'optional'
        Categoria categoria = null;
        if (resultado.isPresent())
            categoria = resultado.get();
        return categoria;
        
    }

    public List<InfoMinimaEmpleado> traerEmpleadosDeCategoria(Integer id){
        Categoria categoria = this.buscarCategoria(id);
        List<InfoMinimaEmpleado> traerEmpleados = new ArrayList();
        for (Empleado e : categoria.getEmpleados()){
            InfoMinimaEmpleado info = new InfoMinimaEmpleado();
            info.id = e.getEmpleadoId();
            info.nombre = e.getNombre();
            traerEmpleados.add(info);
        }
        return traerEmpleados;
    }
}
