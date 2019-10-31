package com.sensores.springboot.backend.services.real;

import com.sensores.springboot.backend.model.entity.real.Medicion;
import com.sensores.springboot.backend.model.entity.real.Sensores_Logs;

import java.util.Collection;
import java.util.List;


public interface ISensoresLogsService {
    public List<Sensores_Logs> findAll();

    public List<Medicion> getMesesHistoricos(int tipo);

    public List<Medicion> getSemanasHistoricos(int tipo);

    public List<Medicion> getDiasHistoricos(int tipo);
}
