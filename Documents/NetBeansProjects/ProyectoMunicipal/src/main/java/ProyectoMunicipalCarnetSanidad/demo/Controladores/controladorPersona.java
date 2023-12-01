package ProyectoMunicipalCarnetSanidad.demo.Controladores;

import ProyectoMunicipalCarnetSanidad.demo.Clases.Ficha;
import ProyectoMunicipalCarnetSanidad.demo.Clases.Persona;
import ProyectoMunicipalCarnetSanidad.demo.Repositorios.repositorioFicha;
import ProyectoMunicipalCarnetSanidad.demo.Repositorios.repositorioPersona;
import ProyectoMunicipalCarnetSanidad.demo.Servicios.servicioArchivos;
import ProyectoMunicipalCarnetSanidad.demo.Servicios.servicioPersona;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import org.apache.catalina.connector.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("municipal/")
@CrossOrigin("http://localhost:4200/")
public class controladorPersona {

    @Autowired
    servicioPersona servicioPersona;

    @Autowired
    servicioArchivos servicioArchivos;

    @Autowired
    repositorioFicha repoFicha;

    @Autowired
    HttpServletRequest HttpServletRequest;

    @Autowired
    repositorioPersona repositorioPersona;

    private String URL;

    @PostMapping("/registrar")
    public Persona guardarUsuario(@RequestBody Persona p) throws Exception {

        System.out.println("Guardando");
        return servicioPersona.registrarPersona(p);
    }

    @PostMapping("/registrarArchivoPorId/{idPersona}")
    public String registrarArchivoPorId(@PathVariable Long idPersona, @RequestPart("file") MultipartFile formdata) throws Exception {
        String nombre = servicioArchivos.Almacenar(formdata);
        String host = HttpServletRequest.getRequestURL().toString().replace(HttpServletRequest.getRequestURI(), "");
        String URL = ServletUriComponentsBuilder
                .fromHttpUrl(host)
                .path("/municipal/")
                .path(nombre)
                .toUriString();
        this.URL = URL;
        return URL;
    }

    @PostMapping("/registrarFicha/{idPersona}")
    public Ficha guardarFicha(@PathVariable Long idPersona, @RequestBody Ficha f) throws Exception {
        f.setUrlFicha(URL);
        return servicioPersona.guardarFicha(idPersona, f);
    }

    @GetMapping("/lista")
    public List<Persona> listarPersonas() {
        return servicioPersona.listarTodos();
    }

    @GetMapping("/listaApellido")
    public List<Persona> listarPersonasApellido() {
        return servicioPersona.listarApellidosAlfabeticamenteAZ();
    }

    @GetMapping("/listaApellidoZA")
    public List<Persona> listarPersonasApellidoZA() {
        return servicioPersona.listarApellidosAlfabeticamenteZA();
    }
    @GetMapping("/listaNombre")
    public List<Persona> listarPersonasAlfabeticamente() {
        return servicioPersona.listarNombresAlfabeticamenteAZ();
    }

    @GetMapping("/listaNombreZA")
    public List<Persona> listarPersonasAlfabeticamenteZA() {
        return servicioPersona.listarNombresAlfabeticamenteZA();
    }
    
    @GetMapping("/listaFechaDesc")
    public List<Persona> listarPorFechaDesc() {
        return servicioPersona.listarPorFechaDesc();
    }

    @GetMapping("/listaFechaAsc")
    public List<Persona> listarPorFechaAsc() {
        return servicioPersona.listarPorFechaAsc();
    }

    @DeleteMapping("/borrar/{id_persona}")
    public ResponseEntity<String> borrarPersona(@PathVariable Long id_persona) {
        servicioPersona.borrarPersona(id_persona);
        return ResponseEntity.ok().body("Borrado correctamente");
    }

    @PostMapping("/obtenerFicha")
    public ResponseEntity<Ficha> obtenerFicha(@RequestBody Persona persona) {
        return servicioPersona.obtenerFicha(persona);
    }

    @GetMapping("empleado/{idPersona}")
    public ResponseEntity<Persona> obtenerPersonaPorId(@PathVariable Long idPersona) throws Exception {
        return servicioPersona.encontrarPorId(idPersona);
    }

    @GetMapping("/obtenerFichasPorId/{id}")
    public ResponseEntity<List<Ficha>> obtenerFichasPorId(@PathVariable Long id) throws Exception {
        return servicioPersona.obtenerFichasPorId(id);
    }

    @GetMapping("/hola")
    public String saludar() {
        return "hola";
    }

    @PostMapping("/obtenerEstadoDeLaFicha")
    public Ficha obtenerEstadoFicha(@RequestBody Long idPersona) throws Exception {

        return servicioPersona.obtenerEstado(idPersona);
    }

    @GetMapping("/obtenerIdDeFicha/{idPersona}")
    public ResponseEntity<Long> obtenerIdDeFicha(@PathVariable Long idPersona) throws Exception {
        return servicioPersona.obtenerIdFichaActual(idPersona);
    }

    @PostMapping("/desaprobarFicha/{idFicha}")
    public ResponseEntity<Ficha> desaprobarFicha(@PathVariable Long idFicha, @RequestBody Persona persona) throws Exception {
        return this.servicioPersona.DesaprobarFicha(idFicha, persona);
    }

    @GetMapping("{filename:.+}")
    public ResponseEntity<Resource> cargarRecurso(@PathVariable String filename) throws IOException {
        Resource resource = servicioArchivos.cargarRecurso(filename);
        String contType = Files.probeContentType(resource.getFile().toPath());
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_TYPE, contType)
                .body(resource);

    }

    @GetMapping("/verificarDNI")
    public ResponseEntity<Boolean> verificarDNI(@RequestParam String dni) {
        Persona encontrado = repositorioPersona.findByDni(dni);
        return ResponseEntity.ok(encontrado != null);
    }

    @GetMapping("/verificarFicha")
    public ResponseEntity<Boolean> verificarFicha(@RequestParam int nroFicha) {

        System.out.println("NUMERO CABRO:" + nroFicha);
        Ficha encontrado = repoFicha.findByNroFicha(nroFicha);
        return ResponseEntity.ok(encontrado != null);
    }
}
