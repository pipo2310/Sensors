package com.sensores.springboot.backend.services.real;

import com.sensores.springboot.backend.dao.real.ICuentasDao;
import com.sensores.springboot.backend.model.entity.real.Cuentas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentasServiceImpl implements ICuentasService {
    @Autowired
    ICuentasDao iCuentasDao;

    @Override
    public List<Cuentas> findAll() {
        return (List<Cuentas>)this.iCuentasDao.findAll();
    }
}
