package com.sensores.springboot.backend.controllers.real;

import com.sensores.springboot.backend.model.entity.real.Sensores;
import com.sensores.springboot.backend.services.real.ISensoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SensoresController {

    @Autowired
    ISensoresService iSensoresService;

    @GetMapping("/sensores")
    public List<Sensores> index(){
        return this.iSensoresService.findAll();
    }
}
