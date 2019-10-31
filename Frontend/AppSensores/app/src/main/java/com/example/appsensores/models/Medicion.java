package com.example.appsensores.models;

import com.google.gson.annotations.Expose;

public class Medicion {

    @Expose
    private String metrica;

    @Expose
    private double valor;

    @Expose
    private double anno;

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

    public double getAnno() {
        return anno;
    }

    public void setAnno(double anno) {
        this.anno = anno;
    }
}
