package com.sensores.springboot.backend.model.entity.real;

import javax.persistence.*;

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

    @Column(name = "clave")
    private String clave;

    @Column(name = "email")
    private String email;

    @Column(name = "usuario")
    private String usuario;

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

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
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
}
