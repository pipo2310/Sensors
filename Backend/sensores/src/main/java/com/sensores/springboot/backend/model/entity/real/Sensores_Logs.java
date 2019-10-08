package com.sensores.springboot.backend.model.entity.real;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "sensores_logs" , schema = "public")
public class Sensores_Logs implements Serializable {

    public Sensores_Logs(){}

    @EmbeddedId
    private Sensores_logs_pk sensoresLogsPk;

    @Column ( name = "valor")
    private double valor;

    public Sensores_logs_pk getSensoresLogsPk() {
        return sensoresLogsPk;
    }

    public void setSensoresLogsPk(Sensores_logs_pk sensoresLogsPk) {
        this.sensoresLogsPk = sensoresLogsPk;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    /*
    * Relación entre sensores Log y sensores
    * */
    @MapsId("sensores_id")
    @JoinColumns({
            @JoinColumn(name = "id_sensor", referencedColumnName = "sensores_pk")
    })
    @ManyToOne
    private Sensores sensores;
}
