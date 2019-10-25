package com.example.appsensores.models;

import com.google.gson.annotations.Expose;

public class Medicion {

    @Expose
    private String metrica;

    @Expose
    private double valor;

    public String getMetrica() {
        return metrica;
    }

    public void setMetrica(String metrica) {
        this.metrica = metrica;
    }

    public double getValor() {
        return valor;
    }

    public void setValore(double valor) {
        this.valor = valor;
    }


}
