package ProyectoMunicipalCarnetSanidad.demo.Repositorios;

import ProyectoMunicipalCarnetSanidad.demo.Clases.Carnet;

import org.springframework.data.jpa.repository.JpaRepository;


public interface repositorioCarnet extends JpaRepository<Carnet, Long> {
    
Carnet findByIdCarnet(long idCarnet);    
    
}
