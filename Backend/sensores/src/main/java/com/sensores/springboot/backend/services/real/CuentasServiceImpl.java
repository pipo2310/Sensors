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


    /**
     * Metodo para obtener todas las cuentas de la base de datos
     * @return Retorna una lista con las cuentas
     */
    @Override
    public List<Cuentas> findAll() {
        return (List<Cuentas>)iCuentasDao.findAll();
    }

    /**
     * Metodo para insertar una cuenta en la base de datos
     * @param cuentas cuenta para insertar en la base de datos
     * @return Retorna la cuenta insertada
     */
    @Override
    public Cuentas save(Cuentas cuentas) {
        return iCuentasDao.save(cuentas);
    }

    /**
     * Metodo para eliminar una cuenta de la base de datos
     * @param cuentasPk id de la cuenta a eliminar
     */
    @Override
    public void deleteById(long cuentasPk) {
        iCuentasDao.deleteById(cuentasPk);
    }

    /**
     * Metodo para obtener la informacion de una cuenta por su id
     * @param cuentasPk id de la cuenta de la cual quiero su informacion
     * @return Retorna la infromacion de la cuenta
     */
    @Override
    public Cuentas findById(long cuentasPk) {
        return iCuentasDao.findById(cuentasPk).orElse(null);
    }
}
