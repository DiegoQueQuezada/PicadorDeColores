package ProyectoMunicipalCarnetSanidad.demo.Controladores;

import ProyectoMunicipalCarnetSanidad.demo.Clases.Carnet;
import ProyectoMunicipalCarnetSanidad.demo.Clases.Ficha;
import ProyectoMunicipalCarnetSanidad.demo.Clases.Persona;
import ProyectoMunicipalCarnetSanidad.demo.Servicios.servicioCarnet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/municipal")
public class controladorCarnet {

    @Autowired
    servicioCarnet servicioCarnet;

    @PostMapping("/registrarCarnet/{idFicha}")
    public ResponseEntity<String> registrarCarnet(@RequestBody Carnet carnet, @PathVariable Long idFicha) {
        return servicioCarnet.AprobarFicha(idFicha, carnet);
    }

    @GetMapping("/obtenerListaCarnet")
    public ResponseEntity<List<Carnet>> obtenerListaCarnet() {
        return servicioCarnet.obtenerListaCarnets();
    }

    @GetMapping("/obtenerPersonaAsociada/{idCarnet}")
    public ResponseEntity<Persona> obtenerPersonaAsociada(@PathVariable int idCarnet) {
        return servicioCarnet.obtenerPersonaAsociada(idCarnet);
    }
    
    @GetMapping("/obtenerPersonaAsociadaPorNroCarnet/{numeroCarnet}")
    public ResponseEntity<Persona> obtenerPersonaAsociadaPorNroCarnet(@PathVariable int numeroCarnet) {
        
        System.out.println("LLEGA"+numeroCarnet);
        return servicioCarnet.obtenerPersonaAsociadaPorNroCarnet(numeroCarnet);
    }

    @GetMapping("/obtenerFicha/{IdFicha}")
    public ResponseEntity<Ficha> obtenerFicha(@PathVariable Long IdFicha) {
        return servicioCarnet.obtenerFicha(IdFicha);
    }
    
     @PostMapping("/emitirCarnet")
    public ResponseEntity<String> obtenerFicha(@RequestBody Carnet carnet) {
        return servicioCarnet.emitirCarnet(carnet);
    }

}
