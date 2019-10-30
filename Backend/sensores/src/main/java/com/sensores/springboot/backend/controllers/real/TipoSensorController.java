package com.sensores.springboot.backend.controllers.real;


import com.sensores.springboot.backend.dao.real.ITipoDao;
import com.sensores.springboot.backend.model.entity.real.TipoSensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TipoSensorController {

    @Autowired
    private ITipoDao iTipoDao;

    @GetMapping("/tipo_sensor")
    public List<TipoSensor> index() {
        return this.iTipoDao.findAll();
    }

    @PutMapping("/tipo_sensor/{id}")
    TipoSensor updateTipoSensor(@RequestBody TipoSensor nuevoSensor, @PathVariable Integer id) {

        return iTipoDao.findById(id)
                .map(tipo_sensor -> {
                    tipo_sensor.setCosto(nuevoSensor.getCosto());
                    return iTipoDao.save(tipo_sensor);
                })
                .orElseGet(() -> {
                    nuevoSensor.setId(id);
                    return iTipoDao.save(nuevoSensor);
                });

    }
}
