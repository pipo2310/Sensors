package com.sensores.springboot.backend.controllers.real;

import com.sensores.springboot.backend.model.entity.foo.Medicion;
import com.sensores.springboot.backend.model.entity.real.Sensores;
import com.sensores.springboot.backend.model.entity.real.Sensores_Logs;
import com.sensores.springboot.backend.services.real.ISensoresLogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api")
public class SensoresLogsController {

    @Autowired
    ISensoresLogsService iSensoresLogsService;

    @GetMapping("/sensoresLogs")
    public List<Sensores_Logs> index() {return this.iSensoresLogsService.findAll();}

   @GetMapping("/sensoresLogs_mes")
    public Collection<Medicion> index2(@RequestParam int tipo) {return this.iSensoresLogsService.getMesesHistoricos(tipo);}

    @GetMapping("/sensoresLogs_semana")
    public Collection<Medicion> index3(@RequestParam int tipo) {return this.iSensoresLogsService.getSemanasHistoricos(tipo);}

    @GetMapping("/sensoresLogs_dia")
    public Collection<Medicion> index4(@RequestParam int tipo) {return this.iSensoresLogsService.getDiasHistoricos(tipo);}

}
