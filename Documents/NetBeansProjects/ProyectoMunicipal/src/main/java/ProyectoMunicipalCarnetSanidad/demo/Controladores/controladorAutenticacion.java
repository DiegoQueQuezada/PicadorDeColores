/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProyectoMunicipalCarnetSanidad.demo.Controladores;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("aut/")
@CrossOrigin("http://localhost:4200")
public class controladorAutenticacion {

@GetMapping("/hola")
public String saludo(){
    System.out.println("RECIBIDO");
    return "asfasf";
    }

@PostMapping("/login")
public ResponseEntity<String> logearse() {
    // Obtén la información de autenticación del contexto de seguridad
    System.out.println("xs");
    System.out.println("SFAFSAHDSADJASKDHASDASKDKJASHFKJSFAS;VAFASHFA;HGASFSAH;ASJ");
    // Simplemente devolvemos un mensaje de éxito en este ejemplo
    return ResponseEntity.ok("Inicio de sesión exitoso");
}
}