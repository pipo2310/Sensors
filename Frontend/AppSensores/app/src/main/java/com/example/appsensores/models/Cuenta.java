package com.example.appsensores.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cuenta {
    @Expose
    private Long cuentasPk;

    @Expose
    private boolean esAdmin;

    @Expose
    private String telefono;

    @Expose
    private String seccion;

    @Expose
    private String email;

    @Expose
    private String nombre;

    @Expose
    private String codigo;

    @SerializedName("body")
    @Expose
    private String text;

    public Long getCuentasPk() {
        return cuentasPk;
    }

    public void setCuentasPk(Long cuentasPk) {
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }
}
