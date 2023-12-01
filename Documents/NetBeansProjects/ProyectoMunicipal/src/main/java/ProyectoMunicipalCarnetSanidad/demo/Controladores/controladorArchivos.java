package ProyectoMunicipalCarnetSanidad.demo.Controladores;

import ProyectoMunicipalCarnetSanidad.demo.Servicios.servicioArchivos;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RequestMapping("archivos/")
@CrossOrigin("http://localhost:4200/")
@RestController
public class controladorArchivos {
    
    @Autowired
    private servicioArchivos servicioArchivos;
    @Autowired
    private HttpServletRequest httpServletRequest;
    
        @PostMapping("/descargar")
        public Map<String,String> descargarArchivo(@RequestParam("file") MultipartFile multipartFile){
            String archivo=servicioArchivos.Almacenar(multipartFile);
            String host=httpServletRequest.getRequestURL().toString().replace(httpServletRequest.getRequestURI(), "");
            String URL=ServletUriComponentsBuilder
                    .fromHttpUrl(host)
                    .path("/media/")
                    .path(archivo)
                    .toUriString();
            
            return Map.of("url", URL);
        }
    
    @GetMapping("/{nombre:.+}")
    public ResponseEntity<Resource> obtenerArchivo(@PathVariable String nombreArchivo)throws IOException{
        Resource resource=servicioArchivos.cargarRecurso(nombreArchivo);
        String tipo=Files.probeContentType(resource.getFile().toPath());
        
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, tipo).body(resource);
        
    }
    



    
}
