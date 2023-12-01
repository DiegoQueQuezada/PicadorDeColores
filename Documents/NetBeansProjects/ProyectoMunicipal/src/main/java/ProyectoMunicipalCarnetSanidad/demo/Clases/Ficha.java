package ProyectoMunicipalCarnetSanidad.demo.Clases;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "rs_ficha")
public class Ficha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFicha;                    //ID

    @Column(nullable = false)               //NUMERO DE FICHA
    private int nroFicha;

    @Column(nullable = false)               //FECHA DE INSCRIPCION
    private Date fechaInscripcion;

    @Column(nullable = false)               //FECHA DE CADUCACION
    private Date fechaCaducacion;

    @Column(nullable = false)
    private String laboratorio;              //NOMBRE LABORATORIO

    @Column(nullable = false)
    private String grupoSanguineo;           //GRUPO SANGUINEO

    @Column()
    private String observaciones;            //OBSERVACIONES

    @Transient
    private int tiempoCaducacion = 14;          //TIEMPO DE CADUCACION (DIAS)

    private String estado;                   //ESTADO(CADUCADO,APROBADO;DESAPROBADO;INEXISTENTE;EN REVISION)   

    private String urlFicha;                 //URL DE IMAGEN ADJUNTA 

    @ManyToOne()
    Persona persona;                        //PERSONA ASOCIADA

    @JsonIgnore
    @OneToOne(mappedBy = "ficha")
    Carnet carnet;                          //CARNET ASOCIADO

}
