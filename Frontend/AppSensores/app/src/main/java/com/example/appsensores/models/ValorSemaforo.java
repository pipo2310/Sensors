package com.example.appsensores.models;

import com.google.gson.annotations.Expose;

public class ValorSemaforo {

    @Expose
    private String dia;

    @Expose
    private double valor;

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
