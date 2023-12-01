
package ProyectoMunicipalCarnetSanidad.demo.Repositorios;

import ProyectoMunicipalCarnetSanidad.demo.Clases.Persona;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface repositorioPersona extends JpaRepository<Persona, Long> {
    
    public Persona findByDni(String dni);
    public List<Persona> findAllByOrderByNombre();
    public List<Persona> findAllByOrderByFechaRegistroAsc(); // Orden ascendente por fechaRegistro
    public List<Persona> findAllByOrderByFechaRegistroDesc(); // Orden descendente por fechaRegistro    
    public List<Persona> findAllByOrderByNombreDesc(); // Orden descendente por nombre
}
