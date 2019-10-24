package com.example.appsensores.models;

import com.google.gson.annotations.Expose;

public class Medicion {

    @Expose
    private String nombre;

    @Expose
    private int tipo;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getType() {
        return tipo;
    }

    public void setType(int tipo) {
        this.tipo = tipo;
    }


}
