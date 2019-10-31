package com.sensores.springboot.backend.model.entity.real;



public class Medicion {
    private String metrica;
    private double valor;
    private double anno;


    public Medicion(double anno,String metrica, double valor){
        this.metrica = metrica;
        this.valor = valor;
        this.anno = anno;
    }

    public double getAnno() {
        return anno;
    }

    public void setAnno(double anno) {
        this.anno = anno;
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
