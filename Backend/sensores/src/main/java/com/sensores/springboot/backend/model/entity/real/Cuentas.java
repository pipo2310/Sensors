package com.sensores.springboot.backend.model.entity.real;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cuentas" , schema = "public")
public class Cuentas {

    @Id
    @Column(name = "cuentas_pk")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long cuentasPk;

    @Column(name = "es_admin")
    private boolean esAdmin;

    @Column(name = "telefono")
    @Basic(optional = true)
    private String telefono;

    @Column(name = "seccion")
    private String seccion;

    @Column(name = "email")
    private String email;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "codigo")
    @Basic(optional = true)
    private String codigo;

    public long getCuentasPk() {
        return cuentasPk;
    }

    public void setCuentasPk(long cuentasPk) {
        this.cuentasPk = cuentasPk;
    }

    public boolean isEsAdmin() {
        return esAdmin;
    }

    public void setEsAdmin(boolean esAdmin) {
        this.esAdmin = esAdmin;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    @OneToMany(mappedBy = "cuentas")
    private List<Sensores> sensoresList = new ArrayList<>();
}
