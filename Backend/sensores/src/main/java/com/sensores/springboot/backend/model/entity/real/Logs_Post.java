package com.sensores.springboot.backend.model.entity.real;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Logs_Post {
    @JsonProperty("id_sensor")
    private Long id_sensor;
    @JsonProperty("valor")
    private double valor;

    public Logs_Post(Long sensores_pk, double valor){
        this.id_sensor = sensores_pk;
        this.valor = valor;
    }

    public Long getSensores_pk() {
        return id_sensor;
    }

    public void setSensores_pk(Long sensores_pk) {
        this.id_sensor = sensores_pk;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
