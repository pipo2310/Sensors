package com.sensores.springboot.backend.model.entity.real;

import javax.persistence.*;

@Entity
@Table(name = "sensores_logs" , schema = "public")
public class Sensores_Logs {

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
}
