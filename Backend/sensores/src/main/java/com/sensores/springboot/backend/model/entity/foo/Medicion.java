package com.sensores.springboot.backend.model.entity.foo;



public class Medicion {
    private String metrica;
    private double valor;


    public Medicion(String metrica, double valor){
        this.metrica = metrica;
        this.valor = valor;
    }

    public String getMetrica() {
        return metrica;
    }

    public void setMetrica(String metrica) {
        this.metrica = metrica;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
