package com.sensores.springboot.backend.services.real;

import com.sensores.springboot.backend.dao.real.ISensoresDao;
import com.sensores.springboot.backend.model.entity.real.Sensores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SensoresServiceImpl implements ISensoresService{
    @Autowired
    ISensoresDao iSensoresDao;

    @Override
    @Transactional(readOnly = true)
    public List<Sensores> findAll() {
        return (List<Sensores>)this.iSensoresDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Sensores findById(long id) {
        return iSensoresDao.findById(id).orElse(null);
    }

	@Override
    @Transactional
    public Sensores save(Sensores sensores) {
        return iSensoresDao.save(sensores);
    }

    @Override
    @Transactional
    public int delete(Long id) {

        iSensoresDao.deleteById(id);
        return 1;
    }

}
