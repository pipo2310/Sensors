package com.sensores.springboot.backend.model.entity.real;

import javax.persistence.*;

@Entity
@Table(name = "sensores" , schema = "public")
public class Sensores {
    public long getSensoresPk() {
        return sensoresPk;
    }

    public void setSensoresPk(long sensoresPk) {
        this.sensoresPk = sensoresPk;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public long getId_cuenta() {
        return id_cuenta;
    }

    public void setId_cuenta(long id_cuenta) {
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

    @Id
    @Column(name = "sensores_pk")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long sensoresPk;

    @Column(name = "unidad")
    private String unidad;

    @Column(name = "id_cuenta")
    private long id_cuenta;

    @Column(name = "alerta_amarilla")
    @Basic(optional = true)
    private boolean alerta_amarilla;

    @Column(name = "alerta_roja")
    @Basic(optional = true)
    private boolean alerta_roja;
}
