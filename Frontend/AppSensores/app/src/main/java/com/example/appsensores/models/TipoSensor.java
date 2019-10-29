package com.example.appsensores.models;

import com.google.gson.annotations.SerializedName;

public class TipoSensor {

    @SerializedName("id_tipo")
    private Integer id_tipo;
    @SerializedName("alerta_amarilla_global")
    private Float alerta_amarilla_global;
    @SerializedName("alerta_roja_global")
    private Float alerta_roja_global;
    @SerializedName("costo")
    private Float costo;
    @SerializedName("nombre")
    private String nombre;

    public TipoSensor(Integer id_tipo, Float alerta_amarilla_global, Float alerta_roja_global, Float costo, String nombre) {
        this.id_tipo = id_tipo;
        this.alerta_amarilla_global = alerta_amarilla_global;
        this.alerta_roja_global = alerta_roja_global;
        this.costo = costo;
        this.nombre = nombre;
    }

    public Integer getId_tipo() {
        return id_tipo;
    }

    public void setId_tipo(Integer id_tipo) {
        this.id_tipo = id_tipo;
    }

    public Float getAlerta_amarilla_global() {
        return alerta_amarilla_global;
    }

    public void setAlerta_amarilla_global(Float alerta_amarilla_global) {
        this.alerta_amarilla_global = alerta_amarilla_global;
    }

    public Float getAlerta_roja_global() {
        return alerta_roja_global;
    }

    public void setAlerta_roja_global(Float alerta_roja_global) {
        this.alerta_roja_global = alerta_roja_global;
    }

    public Float getCosto() {
        return costo;
    }

    public void setCosto(Float costo) {
        this.costo = costo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
