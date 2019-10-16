package com.sensores.springboot.backend.controllers.real;


import com.sensores.springboot.backend.dao.real.ITipoDao;
import com.sensores.springboot.backend.model.entity.real.TipoSensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TipoSensorController {

    @Autowired
    ITipoDao iTipoDao;

    @GetMapping("/tipo_sensor")
        public List<TipoSensor> index(){
        return (List<TipoSensor>) this.iTipoDao.findAll();
    }

}
