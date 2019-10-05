package com.sensores.springboot.backend.dao.real;


import com.sensores.springboot.backend.model.entity.real.Cuentas;
import org.springframework.data.repository.CrudRepository;

public interface ICuentasDao extends CrudRepository<Cuentas, Long> {
}
