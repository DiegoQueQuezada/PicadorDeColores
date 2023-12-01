package ProyectoMunicipalCarnetSanidad.demo.Servicios;

import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class servicioArchivos {

    private Path path;

    @PostConstruct
    public void iniciarDirectorio() throws IOException {
        path = Paths.get("Archivos");
        Files.createDirectories(path);
    }

    public String Almacenar(MultipartFile multipartFile) {
        try {
            if (multipartFile.isEmpty()) {
                throw new RuntimeException("Esta vacio");
            }
            String nombreArchivo = multipartFile.getOriginalFilename();
            Path paht1 = path.resolve(Paths.get(nombreArchivo)).normalize().toAbsolutePath();
            try (InputStream inputStream = multipartFile.getInputStream()) {

                Files.copy(inputStream, paht1, StandardCopyOption.REPLACE_EXISTING);
            }
            return nombreArchivo;
        } catch (Exception exception) {
            throw new RuntimeException("Error al almacenar el archivo");
        }

    }

    public Resource cargarRecurso(String filename) {
        try {
            Path path1 = path.resolve(filename);
            Resource resource = new UrlResource(path1.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException();
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

}
