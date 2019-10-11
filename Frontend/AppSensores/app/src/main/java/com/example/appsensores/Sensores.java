package com.example.appsensores;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sensores {

    @SerializedName("sensoresPk")
    @Expose
    private Long sensoresPk;

    @SerializedName("nommbre")
    @Expose
    private String nommbre;

    @SerializedName("unidad")
    @Expose
    private String unidad;

    @SerializedName("id_cuenta")
    @Expose
    private Long id_cuenta;

    @SerializedName("alerta_amarilla")
    @Expose
    private float alerta_amarilla;

    @SerializedName("alerta_roja")
    @Expose
    private float alerta_roja;

    @SerializedName("tipo")
    @Expose
    private int tipo;

    @SerializedName("body")
    @Expose
    private String body;

    @Override
    public String toString() {
        return "Sensores{" +
                "sensoresPk='" + sensoresPk +
                ", unidad='" + unidad +
                ", id_cuenta=" + id_cuenta +
                ", alerta_amarilla=" + alerta_amarilla +
                ", alerta_roja=" + alerta_roja +
                '}';
    }

    public Long getSensoresPk() {
        return sensoresPk;
    }

    public void setSensoresPk(Long sensoresPk) {
        this.sensoresPk = sensoresPk;
    }

    public String getNommbre() {
        return nommbre;
    }

    public void setNommbre(String nommbre) {
        this.nommbre = nommbre;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public Long getId_cuenta() {
        return id_cuenta;
    }

    public void setId_cuenta(Long id_cuenta) {
        this.id_cuenta = id_cuenta;
    }

    public float getAlerta_amarilla() {
        return alerta_amarilla;
    }

    public void setAlerta_amarilla(float alerta_amarilla) {
        this.alerta_amarilla = alerta_amarilla;
    }

    public float getAlerta_roja() {
        return alerta_roja;
    }

    public void setAlerta_roja(float alerta_roja) {
        this.alerta_roja = alerta_roja;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
