package com.sensores.springboot.backend.services.real;


import com.sensores.springboot.backend.dao.real.ISensoresLogsDao;
import com.sensores.springboot.backend.model.entity.real.Medicion;
import com.sensores.springboot.backend.model.entity.real.Sensores_Logs;
import com.sensores.springboot.backend.model.entity.real.ValorSemaforo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Service
public class SensoresLogsServiceImpl implements ISensoresLogsService {

    @Autowired
    private ISensoresLogsDao iSensoresLogsDao;

    @Override
    public List<Sensores_Logs> findAll() {return (List<Sensores_Logs>)this.iSensoresLogsDao.findAll(); }

    @Override
    public List<Medicion> getMesesHistoricos(int tipo) { return this.iSensoresLogsDao.obtenerMeses(tipo);}

    @Override
    public List<Medicion> getSemanasHistoricos(int tipo){return this.iSensoresLogsDao.obtenerSemanas(tipo);}

    @Override
    public List<Medicion> getDiasHistoricos(int tipo){return this.iSensoresLogsDao.obtenerDias(tipo);}

    @Override
    public List<ValorSemaforo> getValorSemaforo(int tipo){return this.iSensoresLogsDao.obtenerValorSem(tipo);}

    @Override
    @Transactional
    public Sensores_Logs save(Sensores_Logs log) {
        return iSensoresLogsDao.save(log);
    }

}
