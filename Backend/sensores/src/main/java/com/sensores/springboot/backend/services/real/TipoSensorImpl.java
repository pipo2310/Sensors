package com.sensores.springboot.backend.services.real;


import com.sensores.springboot.backend.dao.real.ITipoDao;
import com.sensores.springboot.backend.model.entity.real.TipoSensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

import javax.persistence.Table;

@Service
public class TipoSensorImpl implements ITipoSensorService {
    @Autowired
    ITipoDao iTipoDao;

    @Override
    public List<TipoSensor> findAll() {
            return (List<TipoSensor>)this.iTipoDao.findAll();
    }

    @Override
    public TipoSensor findById(int id) {
        return iTipoDao.findById(id).orElse(null);
    }

}
