package com.sensores.springboot.backend.dao.real;

import com.sensores.springboot.backend.model.entity.real.Sensores;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ISensoresDao extends CrudRepository<Sensores, Long> {

    @Query("SELECT s FROM Sensores s WHERE s.tipo = ?1")
    List<Sensores> sensoresPorTipo(int tipo);
}
