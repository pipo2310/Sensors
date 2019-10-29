package com.sensores.springboot.backend.model.entity.real;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tipo_sensor" , schema = "public")
public class TipoSensor {

    @Id
    @Column(name = "id_tipo")
    private int id_tipo;


    @Column(name = "nombre")
    private String nombre;


    @Column(name = "alerta_amarilla_global")
    private double alerta_amarilla_global;


    @Column(name = "alerta_roja_global")
    private double alerta_roja_global;

    @Column(name = "costo")
    private Double costo;


    public int getId_tipo() {
        return id_tipo;
    }

    public void setId_tipo(int id_tipo) {
        this.id_tipo = id_tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getAlerta_amarilla_global() {
        return alerta_amarilla_global;
    }

    public void setAlerta_amarilla_global(double alerta_amarilla_global) {
        this.alerta_amarilla_global = alerta_amarilla_global;
    }

    public double getAlerta_roja_global() {
        return alerta_roja_global;
    }

    public void setAlerta_roja_global(double alerta_roja_global) {
        this.alerta_roja_global = alerta_roja_global;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    @OneToMany(mappedBy = "tipoSensor")
    private List<Sensores> tipoSensoresList = new ArrayList<>();


}

