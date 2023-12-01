package ProyectoMunicipalCarnetSanidad.demo.Repositorios;

import ProyectoMunicipalCarnetSanidad.demo.Clases.Ficha;
import ProyectoMunicipalCarnetSanidad.demo.Clases.Persona;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface repositorioFicha extends JpaRepository<Ficha, Long> {

    List<Ficha  > findByPersona(Persona persona);
    
    Ficha findByIdFicha(Long id);
    
    Ficha findByNroFicha(int nroFicha);
    
}
