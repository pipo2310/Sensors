package com.sensores.springboot.backend.dao.real;

import com.sensores.springboot.backend.model.entity.real.Sensores;
import org.springframework.data.repository.CrudRepository;

public interface ISensoresDao extends CrudRepository<Sensores, Long> {
}
