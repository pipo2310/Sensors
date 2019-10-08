package com.sensores.springboot.backend.controllers.real;

import com.sensores.springboot.backend.model.entity.real.Sensores;
import com.sensores.springboot.backend.model.entity.real.Sensores_Logs;
import com.sensores.springboot.backend.services.real.ISensoresLogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SensoresLogsController {

    @Autowired
    ISensoresLogsService iSensoresLogsService;

    @GetMapping("/sensoresLogs")
    public List<Sensores_Logs> index() {return this.iSensoresLogsService.findAll();}

}
