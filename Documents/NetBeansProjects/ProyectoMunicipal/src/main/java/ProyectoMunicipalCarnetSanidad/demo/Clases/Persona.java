package ProyectoMunicipalCarnetSanidad.demo.Clases;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter@Setter
@Table(name = "rs_persona")
public class Persona {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id_persona;

@Column(nullable = false)
private Date fechaRegistro;      //FECHA

@Column(nullable = false)
private String nombre;           //NOMBRES

@Column(nullable = false)
private String apellido;         //APELLIDOS

@Column(nullable = false)
private int edad;                //EDAD

@Column(nullable = false,unique = true)
private String dni;              //DNI

@Column(nullable = true)
private String direccion;        //DIRECCION

@Column(nullable = false)
private String ocupacion;        //OCUPACION

@Column(nullable = false)
private String centroTrabajo;    //CENTRO DE TRABAJO

@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "persona")
@JsonIgnore
private Set<Ficha> fichas=new HashSet<>(); //FICHAS ASOCIADAS


}
