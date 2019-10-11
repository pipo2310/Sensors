package com.sensores.springboot.backend.model.entity.real;

import javax.persistence.*;
import java.util.*;

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

    public double getAlerta_amarilla() {
        return alerta_amarilla;
    }

    public void setAlerta_amarilla(double alerta_amarilla) {
        this.alerta_amarilla = alerta_amarilla;
    }

    public double getAlerta_roja() {
        return alerta_roja;
    }

    public void setAlerta_roja(double alerta_roja) {
        this.alerta_roja = alerta_roja;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Id
    @Column(name = "sensores_pk")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long sensoresPk;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "unidad")
    private String unidad;

    @Column(name = "id_cuenta")
    private long id_cuenta;

    @Column(name = "alerta_amarilla")
    @Basic(optional = true)
    private double alerta_amarilla;

    @Column(name = "alerta_roja")
    @Basic(optional = true)
    private double alerta_roja;

    @Column(name = "tipo")
    private int tipo;


    /*
    * Relación entre sensor y tipo de sensor
    * */
    @MapsId("tipo")
    @JoinColumns({
            @JoinColumn(name = "tipo", referencedColumnName = "id_tipo")
    })
    @ManyToOne
    private TipoSensor tipoSensor;


    /*
     * Relación entre sensor y cuenta
     * */
    @MapsId("id_cuenta")
    @JoinColumns({
            @JoinColumn(name = "id_cuenta", referencedColumnName = "cuentas_pk")
    })
    @ManyToOne
    private Cuentas cuentas;

    /*
    * Relación entre sensor y sensor Log
    * */
    @OneToMany(mappedBy = "sensores")
    private List<Sensores_Logs> sensoresList = new ArrayList<>();


}
