package com.sensores.springboot.backend.dao.real;

import com.sensores.springboot.backend.model.entity.real.TipoSensor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ITipoDao extends CrudRepository<TipoSensor, Integer> {
    List<TipoSensor> findAll();
    List<TipoSensor> findById(int id);
}
