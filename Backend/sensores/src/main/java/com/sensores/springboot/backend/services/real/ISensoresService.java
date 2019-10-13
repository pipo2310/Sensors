package com.sensores.springboot.backend.services.real;

import com.sensores.springboot.backend.model.entity.real.Sensores;

import java.util.List;

public interface ISensoresService {
    public List<Sensores> findAll();

    public Sensores save(Sensores sensores);

    public Sensores findById(Long id);

    public void  delete(Long id);
}
