package com.sensores.springboot.backend.controllers.real;

import com.sensores.springboot.backend.model.entity.real.Medicion;
import com.sensores.springboot.backend.model.entity.real.Sensores_Logs;
import com.sensores.springboot.backend.model.entity.real.Sensores_logs_pk;
import com.sensores.springboot.backend.services.real.ISensoresLogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api")
public class SensoresLogsController {

    @Autowired
    private ISensoresLogsService iSensoresLogsService;

    @GetMapping("/sensoresLogs")
    public List<Sensores_Logs> index() {return this.iSensoresLogsService.findAll();}

    @GetMapping("/sensoresLogs_mes")
    public Collection<Medicion> index2(@RequestParam int tipo) {return this.iSensoresLogsService.getMesesHistoricos(tipo);}

    @GetMapping("/sensoresLogs_semana")
    public Collection<Medicion> index3(@RequestParam int tipo) {return this.iSensoresLogsService.getSemanasHistoricos(tipo);}

    @GetMapping("/sensoresLogs_dia")
    public Collection<Medicion> index4(@RequestParam int tipo) {return this.iSensoresLogsService.getDiasHistoricos(tipo);}

    @PostMapping("/sensoresLogs_post")
    public Sensores_Logs create(@RequestParam String log) {
        String[] elem = log.split(",");
        Sensores_Logs senLog = new Sensores_Logs();
        double d = Double.parseDouble(elem[1]);
        long l = Long.parseLong(elem[0]);
        Sensores_logs_pk spk = new Sensores_logs_pk(l);
        senLog.setSensoresLogsPk(spk);
        senLog.setValor(d);
        return iSensoresLogsService.save(senLog);
    }

}
