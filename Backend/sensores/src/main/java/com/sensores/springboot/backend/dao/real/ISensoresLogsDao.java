package com.sensores.springboot.backend.dao.real;

import com.sensores.springboot.backend.model.entity.real.Sensores_Logs;
import com.sensores.springboot.backend.model.entity.real.Sensores_logs_pk;
import org.springframework.data.repository.CrudRepository;

public interface ISensoresLogsDao extends CrudRepository<Sensores_Logs, Sensores_logs_pk> {
}
