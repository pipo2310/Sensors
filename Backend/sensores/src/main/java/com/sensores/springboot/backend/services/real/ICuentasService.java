package com.sensores.springboot.backend.services.real;

import com.sensores.springboot.backend.model.entity.real.Cuentas;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface ICuentasService {
    /**
     * Metodo para obtener todas las cuentas de la base de datos
     * @return Retorna una lista con las cuentas
     */
    public List<Cuentas> findAll();

    /**
     * Metodo para insertar una cuenta en la base de datos
     * @param cuentas cuenta para insertar en la base de datos
     * @return Retorna la cuenta insertada
     */
    public Cuentas save(Cuentas cuentas);

    /**
     * Metodo para eliminar una cuenta de la base de datos
     * @param cuentasPk id de la cuenta a eliminar
     */
    public void deleteById(long cuentasPk);

    /**
     * Metodo para obtener la informacion de una cuenta por su id
     * @param cuentasPk id de la cuenta de la cual quiero su informacion
     * @return Retorna la infromacion de la cuenta
     */
    public Cuentas findById(long cuentasPk);

    @Query("SELECT C FROM Cuentas C WHERE C.codigo = ?1 OR C.email = ?1")
    public Cuentas getCuentaByCorreoOrCodigo(String identificador);
}
