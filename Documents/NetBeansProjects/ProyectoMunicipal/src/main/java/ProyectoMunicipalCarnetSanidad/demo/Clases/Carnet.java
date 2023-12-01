package ProyectoMunicipalCarnetSanidad.demo.Clases;

import jakarta.persistence.*;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "rs_Carnet")
public class Carnet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCarnet;         //ID

    @Column(nullable = false)
    private int numeroCarnet;       //NUMERO DE CARNET

    @Column(nullable = false)
    private Date fechaRegistro;  //FECHA DE REGISTRO

    @Column()
    private Date fechaEmision;   //FECHA DE EMISION

    @Column(nullable = false)
    private Date fechaCaducidad; //FECHA DE CADUCIDAD

    @Column(nullable = false)
    private String estado;          //ESTADO (EN ENTREGA,ENTREGADO,CADUCADO)

    private int tiempoActivo = 7;     //TIEMPO EN ACTIVIDAD (MESES)

    @OneToOne()  //FICHA ASOCIADA
    Ficha ficha;

    public boolean haExpirado() {
        return new Date().after(fechaCaducidad);
    }
}
