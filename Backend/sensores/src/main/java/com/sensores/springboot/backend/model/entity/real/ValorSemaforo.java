package com.sensores.springboot.backend.model.entity.real;

public class ValorSemaforo {
    private String dia;
    private double valor;



        public ValorSemaforo(String dia, double valor){
        this.dia = dia;
        this.valor = valor;
    }

    public String getDia() {
        return dia;
    }

    public void setMetrica(String dia) {
        this.dia = dia;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

}
