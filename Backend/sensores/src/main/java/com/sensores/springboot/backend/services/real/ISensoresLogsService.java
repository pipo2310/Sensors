package com.sensores.springboot.backend.services.real;

import com.sensores.springboot.backend.model.entity.real.Medicion;
import com.sensores.springboot.backend.model.entity.real.Sensores_Logs;
import com.sensores.springboot.backend.model.entity.real.ValorSemaforo;

import java.util.Collection;
import java.util.List;


public interface ISensoresLogsService {
    List<Sensores_Logs> findAll();

    List<Medicion> getMesesHistoricos(int tipo);

    List<Medicion> getSemanasHistoricos(int tipo);

    List<Medicion> getDiasHistoricos(int tipo);

    List<ValorSemaforo> getValorSemaforo(int tipo);

    Sensores_Logs save(Sensores_Logs log);
}
