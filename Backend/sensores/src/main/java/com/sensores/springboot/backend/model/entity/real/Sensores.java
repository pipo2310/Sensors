package com.sensores.springboot.backend.model.entity.real;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "sensores" , schema = "public")
public class Sensores implements Serializable {
    private static final long serialVersionUID = 2766807839901964384L;
    
    @Id
    @Column(name = "sensores_pk")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sensoresPk;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "unidad")
    private String unidad;

    @Column(name = "id_cuenta")
    private Long id_cuenta;

    @Column(name = "alerta_amarilla")
    @Basic(optional = true)
    private float alerta_amarilla;

    @Column(name = "alerta_roja")
    @Basic(optional = true)
    private float alerta_roja;

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

    /*
    * Gets and Sets
    * */
    public Long getSensoresPk() {
        return sensoresPk;
    }

    public void setSensoresPk(Long sensoresPk) {
        this.sensoresPk = sensoresPk;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public TipoSensor getTipoSensor() {
        return tipoSensor;
    }

    public void setTipoSensor(TipoSensor tipoSensor) {
        this.tipoSensor = tipoSensor;
    }

    public Cuentas getCuentas() {
        return cuentas;
    }

    public void setCuentas(Cuentas cuentas) {
        this.cuentas = cuentas;
    }

    public List<Sensores_Logs> getSensoresList() {
        return sensoresList;
    }

    public void setSensoresList(List<Sensores_Logs> sensoresList) {
        this.sensoresList = sensoresList;
    }
}
