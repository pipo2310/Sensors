package com.sensores.springboot.backend.controllers.real;

import com.sensores.springboot.backend.model.entity.real.Logs_Post;
import com.sensores.springboot.backend.model.entity.real.Medicion;
import com.sensores.springboot.backend.model.entity.real.Sensores_Logs;
import com.sensores.springboot.backend.model.entity.real.Sensores_logs_pk;
import com.sensores.springboot.backend.services.real.ISensoresLogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
/*
    @PostMapping("/sensoresLogs_post")
    public Sensores_Logs create(@RequestBody Logs_Post log) {
        System.out.println("a ver: "+ log);
        double valor = log.getValor();
        long sensor_pk = log.getSensores_pk();
        Sensores_Logs senLog = new Sensores_Logs();
        Sensores_logs_pk spk = new Sensores_logs_pk(sensor_pk);
        senLog.setSensoresLogsPk(spk);
        senLog.setValor(valor);
        return iSensoresLogsService.save(senLog);
    }*/

    @PostMapping("/sensoresLogs_post")
    public Sensores_Logs create(@RequestBody String log) {
        String[] elem = log.split(",");
        String[] numero = elem[0].split(":");

        numero[1] = numero[1].replace(" ",""); // id del sensor



        String[] numero2 = elem[1].split(":");


        numero2[1] =numero2[1].replace("}","");
        numero2[1] =numero2[1].replace("\"","");
        numero2[1] =numero2[1].replace(" ",""); // valor


        Sensores_Logs senLog = new Sensores_Logs();
        double d = Double.parseDouble(numero2[1]);
        long l = Long.parseLong(numero[1]);
        Sensores_logs_pk spk = new Sensores_logs_pk(l);
        senLog.setSensoresLogsPk(spk);
        senLog.setValor(d);
        return iSensoresLogsService.save(senLog);
    }


}
