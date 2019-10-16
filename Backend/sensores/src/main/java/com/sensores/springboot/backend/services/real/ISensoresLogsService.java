package com.sensores.springboot.backend.services.real;

import com.sensores.springboot.backend.model.entity.real.Sensores_Logs;

import java.util.List;


public interface ISensoresLogsService {
    public List<Sensores_Logs> findAll();
}
