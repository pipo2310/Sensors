package com.sensores.springboot.backend.services.real;

import com.sensores.springboot.backend.model.entity.real.TipoSensor;

import java.util.List;

public interface ITipoSensorService {
    public List<TipoSensor> findAll();

    public TipoSensor findById(int id);
}
