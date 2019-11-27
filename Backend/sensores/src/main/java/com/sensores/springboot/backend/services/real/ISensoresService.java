package com.sensores.springboot.backend.services.real;

import com.sensores.springboot.backend.model.entity.real.Sensores;

import java.util.List;

public interface ISensoresService {
    public List<Sensores> findAll();

    public Sensores save(Sensores sensores);

    /**
     * Metodo para buscar una cuenta de la base de datos
     * @param sensorId id del sensor que se quiere buscar
     * @return Retorna la cuenta que se encontró
     */
    public Sensores findById(long sensorId);

    public int  delete(Long id);
}
