package ProyectoMunicipalCarnetSanidad.demo.Servicios;

import ProyectoMunicipalCarnetSanidad.demo.Clases.Carnet;
import ProyectoMunicipalCarnetSanidad.demo.Clases.Ficha;
import ProyectoMunicipalCarnetSanidad.demo.Clases.Persona;
import ProyectoMunicipalCarnetSanidad.demo.Repositorios.repositorioCarnet;
import ProyectoMunicipalCarnetSanidad.demo.Repositorios.repositorioFicha;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class servicioCarnet {

    @Autowired
    repositorioCarnet repositorioCarnet;

    @Autowired
    repositorioFicha repositorioFicha;
    
    

    public ResponseEntity<String> AprobarFicha(Long idFicha, Carnet carnet) {
        
        
        System.out.println("ID LLEGADO"+idFicha);
        Ficha ficha = repositorioFicha.findByIdFicha(idFicha);
        
        Date date1 = new Date();
        carnet.setNumeroCarnet(ficha.getNroFicha());
        carnet.setFicha(ficha);
        carnet.setFechaRegistro(date1);
        carnet.setEstado("En entrega");
        carnet.setFechaEmision(null);
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        calendar.add(Calendar.MONTH, 7);
        carnet.setFechaCaducidad(calendar.getTime());
        
        repositorioCarnet.save(carnet);
        ficha.setCarnet(carnet);
        ficha.setEstado("Aprobado");
        
        repositorioFicha.save(ficha);
        
        return ResponseEntity.ok().body("Registrado correctamente");
    }

    public ResponseEntity<List<Carnet>> obtenerListaCarnets() {
    return ResponseEntity.ok().body(repositorioCarnet.findAll());
    }

    public ResponseEntity<Persona> obtenerPersonaAsociada(int nroCarnet) {
        Carnet carnet=repositorioCarnet.findByIdCarnet(nroCarnet);
        return ResponseEntity.ok().body(carnet.getFicha().getPersona());
    }

    public ResponseEntity<Persona> obtenerPersonaAsociadaPorNroCarnet(int numeroCarnet) {
        Ficha ficha=repositorioFicha.findByNroFicha(numeroCarnet);
        return ResponseEntity.ok().body(ficha.getPersona());
        
    }
    public ResponseEntity<Ficha> obtenerFicha(Long IdFicha) {
        Ficha ficha=this.repositorioFicha.findByIdFicha(IdFicha);
        return ResponseEntity.ok().body(ficha);
    }
    
    public ResponseEntity<String> emitirCarnet(Carnet carnet) {
        Carnet carnetBuscado=repositorioCarnet.findByIdCarnet(carnet.getIdCarnet());
        carnetBuscado.setEstado("Emitido");
        carnetBuscado.setFechaEmision(new Date());
        repositorioCarnet.save(carnetBuscado);
        return ResponseEntity.ok().body("Emitido");
    }


    

}
