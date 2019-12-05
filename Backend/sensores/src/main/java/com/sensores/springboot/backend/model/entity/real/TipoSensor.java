package com.sensores.springboot.backend.model.entity.real;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tipo_sensor" , schema = "public")
public class TipoSensor {

    @Id
    @Column(name = "id_tipo")
    private int id;


    @Column(name = "nombre")
    private String nombre;

    @Column(name = "costo")
    @Basic()
    private Float costo;


    public int getId() {
        return id;
    }

    public void setId(int id_tipo) {
        this.id = id_tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Float getCosto() {
        return costo;
    }

    public void setCosto(Float costo) {
        this.costo = costo;
    }

    @OneToMany(mappedBy = "tipoSensor")
    private List<Sensores> tipoSensoresList = new ArrayList<>();



}

