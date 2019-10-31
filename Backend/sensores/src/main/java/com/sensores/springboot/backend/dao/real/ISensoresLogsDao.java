
package com.sensores.springboot.backend.dao.real;

import com.sensores.springboot.backend.model.entity.real.Medicion;
import com.sensores.springboot.backend.model.entity.real.Sensores_Logs;
import com.sensores.springboot.backend.model.entity.real.Sensores_logs_pk;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.parser.Entity;
import java.util.Collection;
import java.util.List;

public interface ISensoresLogsDao extends CrudRepository<Sensores_Logs, Sensores_logs_pk> {


    @Query(nativeQuery = true)
  List<Medicion> obtenerMeses(@Param("tipo") int tipo);

    @Query(nativeQuery = true)
    List<Medicion> obtenerSemanas(@Param("tipo") int tipo);

    @Query(nativeQuery = true)
    List<Medicion> obtenerDias(@Param("tipo") int tipo);


}
