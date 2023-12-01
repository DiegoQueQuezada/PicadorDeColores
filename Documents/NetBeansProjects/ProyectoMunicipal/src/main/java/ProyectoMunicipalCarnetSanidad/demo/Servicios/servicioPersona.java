package ProyectoMunicipalCarnetSanidad.demo.Servicios;

import ProyectoMunicipalCarnetSanidad.demo.Clases.Ficha;
import ProyectoMunicipalCarnetSanidad.demo.Clases.Persona;
import ProyectoMunicipalCarnetSanidad.demo.Repositorios.repositorioFicha;
import ProyectoMunicipalCarnetSanidad.demo.Repositorios.repositorioPersona;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class servicioPersona {

    @Autowired
    repositorioPersona repoPer;

    @Autowired
    repositorioFicha repoFicha;

    public Persona registrarPersona(Persona p) throws Exception {

        Persona encontrado = repoPer.findByDni(p.getDni());
        if (encontrado != null) {
            System.out.println("Usuario ya existente");
            throw new Exception("Este usuario ya se registro.");
        } else {
            encontrado = repoPer.save(p);
        }
        return encontrado;
    }

    public List<Persona> listarTodos() {
        return repoPer.findAll();
    }

    public List<Persona> listarNombresAlfabeticamenteAZ() {
        return repoPer.findAllByOrderByNombre();
    }
    
    public List<Persona> listarNombresAlfabeticamenteZA() {
        return repoPer.findAllByOrderByNombreDesc();
    }
    
    public List<Persona> listarApellidosAlfabeticamenteAZ() {
        return repoPer.findAllByOrderByNombre();
    }
    
    public List<Persona> listarApellidosAlfabeticamenteZA() {
        return repoPer.findAllByOrderByNombreDesc();
    }

    public ResponseEntity<Ficha> obtenerFicha(Persona persona) {
        List<Ficha> fichas = repoFicha.findByPersona(persona);
        Ficha ficha = fichas.get(fichas.size() - 1);
        return ResponseEntity.ok().body(ficha);
    }

    public List<Persona> listarPorFechaDesc() {
        return repoPer.findAllByOrderByFechaRegistroDesc();
    }

    public List<Persona> listarPorFechaAsc() {
        return repoPer.findAllByOrderByFechaRegistroAsc();

    }

    public void borrarPersona(Long idPersona) {
        repoPer.deleteById(idPersona);
    }

//    public Carnet crearCarnet(String id){
//        
//    }
    public Ficha guardarFicha(Long idPersona, Ficha ficha) throws Exception {
        Persona persona = repoPer.findById(idPersona).orElseThrow(() -> new Exception("No existe el empleado"));
        ficha.setEstado("En revision");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(ficha.getFechaInscripcion());
        calendar.add(Calendar.DAY_OF_MONTH, ficha.getTiempoCaducacion());
        calendar.add(Calendar.HOUR, 5);
        ficha.setFechaCaducacion(calendar.getTime());
        persona.getFichas().add(ficha);
        ficha.setPersona(persona);
        return repoFicha.save(ficha);
    }

    public ResponseEntity<Persona> encontrarPorId(Long idPersona) throws Exception {
        Persona persona = repoPer.findById(idPersona).orElseThrow(() -> new Exception("No existe el usuario"));;

        if (persona == null) {
            System.out.println("No existe");
        }
        return ResponseEntity.ok(persona);
    }

    public Ficha obtenerEstado(Long idPersona) throws Exception {

        Persona persona = repoPer.findById(idPersona).orElseThrow(() -> new Exception("Error: No existe la persona con el ID proporcionado"));
        List<Ficha> fichas = repoFicha.findByPersona(persona);

        if (fichas.isEmpty()) {
            return null;
        } else {
            Ficha fichaActual = fichas.get(fichas.size() - 1);
            if (fichaActual.getEstado().equals("Aprobado") && fichaActual.getEstado().equals("Desaprobado")) {
                return fichaActual;
            } else {
                if (new Date().after(fichaActual.getFechaCaducacion())) {
                    fichaActual.setEstado("Caducada");
                    repoFicha.save(fichaActual);
                }
            }
            return fichaActual;

        }

    }

    public ResponseEntity<List<Ficha>> obtenerFichasPorId(Long id) throws Exception {
        Persona persona = repoPer.findById(id).orElseThrow(() -> new Exception("Error: No existe la persona con el ID proporcionado"));
        List<Ficha> fichas = repoFicha.findByPersona(persona);
        return ResponseEntity.ok(fichas);
    }

    public Ficha encontrarFichaPorId(Long idFicha) {
        return repoFicha.findByIdFicha(idFicha);
    }

    public ResponseEntity<Long> obtenerIdFichaActual(Long idPersona) throws Exception {
        Persona persona = repoPer.findById(idPersona).orElseThrow(() -> new Exception("Error: No existe la persona con el ID proporcionado"));
        List<Ficha> fichas = repoFicha.findByPersona(persona);
        Ficha fichaActual = fichas.get(fichas.size() - 1);
        return ResponseEntity.ok().body(fichaActual.getIdFicha());
    }

    public ResponseEntity<Ficha> DesaprobarFicha(Long FichaActual, Persona persona) {

        Set<Ficha> lista1 = persona.getFichas();
        Ficha ficha = repoFicha.findByIdFicha(FichaActual);
        lista1.remove(ficha);
        ficha.setEstado("Desaprobado");
        lista1.add(ficha);
        repoFicha.save(ficha);
        persona.setFichas(lista1);
        repoPer.save(persona);
        return ResponseEntity.ok().body(ficha);

    }
}
