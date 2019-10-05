package com.sensores.springboot.backend.services.real;

import com.sensores.springboot.backend.dao.real.ISensoresDao;
import com.sensores.springboot.backend.model.entity.real.Sensores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensoresServiceImpl implements ISensoresService{
    @Autowired
    ISensoresDao iSensoresDao;

    @Override
    public List<Sensores> findAll() {
        return (List<Sensores>)this.iSensoresDao.findAll();
    }
}
