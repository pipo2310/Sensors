package com.example.appsensores;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sensores {
    //@JsonProperty("sensoresPk")

    @Expose
    private Long sensoresPk;



    // @JsonProperty("unidad")
    @Expose
    private String nombreSensor;
    @Expose
    private String unidad;
    //@JsonProperty("id_cuenta")
    @Expose
    private String id_cuenta;
    // @JsonProperty("alerta_amarilla")
    @Expose
    private float alerta_amarilla;
    // @JsonProperty("alerta_roja")
    @Expose
    private float alerta_roja;
    @SerializedName("body")
    @Expose
    private String text;

    public Long getSensoresPk() {
        return sensoresPk;
    }

    public void setSensoresPk(Long sensoresPk) {
        this.sensoresPk = sensoresPk;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getId_cuenta() {
        return id_cuenta;
    }

    public void setId_cuenta(String id_cuenta) {
        this.id_cuenta = id_cuenta;
    }

    public float isAlerta_amarilla() {
        return alerta_amarilla;
    }

    public void setAlerta_amarilla(float alerta_amarilla) {
        this.alerta_amarilla = alerta_amarilla;
    }

    public float isAlerta_roja() {
        return alerta_roja;
    }

    public void setAlerta_roja(float alerta_roja) {
        this.alerta_roja = alerta_roja;
    }

    public String getNombreSensor() {
        return nombreSensor;
    }

    public void setNombreSensor(String nombreSensor) {
        this.nombreSensor = nombreSensor;
    }

    public String getText() {
        return text;
    }

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
}
