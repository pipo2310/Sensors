package com.example.appsensores;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Sensores {
    //@JsonProperty("sensoresPk")
    private Long sensoresPk;

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

    public boolean isAlerta_amarilla() {
        return alerta_amarilla;
    }

    public void setAlerta_amarilla(boolean alerta_amarilla) {
        this.alerta_amarilla = alerta_amarilla;
    }

    public boolean isAlerta_roja() {
        return alerta_roja;
    }

    public void setAlerta_roja(boolean alerta_roja) {
        this.alerta_roja = alerta_roja;
    }

    // @JsonProperty("unidad")
    private String unidad;
    //@JsonProperty("id_cuenta")
    private String id_cuenta;
   // @JsonProperty("alerta_amarilla")
    private boolean alerta_amarilla;
   // @JsonProperty("alerta_roja")
    private boolean alerta_roja;

}
