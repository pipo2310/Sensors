package com.sensores.springboot.backend.services.real;

import com.sensores.springboot.backend.model.entity.real.Cuentas;

import java.util.List;

public interface ICuentasService {
    public List<Cuentas> findAll();
}
