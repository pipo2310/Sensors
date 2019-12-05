package com.sensores.springboot.backend.dao.real;


import com.sensores.springboot.backend.model.entity.real.Cuentas;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ICuentasDao extends CrudRepository<Cuentas, Long> {
    @Query("SELECT C FROM Cuentas C WHERE C.codigo = ?1 OR C.email = ?1")
    Cuentas getCuentaByCorreoOrCodigo(String identificador);
}
