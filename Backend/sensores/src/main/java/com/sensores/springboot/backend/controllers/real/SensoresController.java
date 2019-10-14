package com.sensores.springboot.backend.controllers.real;

import com.sensores.springboot.backend.model.entity.real.Sensores;
import com.sensores.springboot.backend.services.real.ISensoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/sensores")
    public Sensores create(@RequestBody Sensores sensores) {
        return iSensoresService.save(sensores);
    }

    @PutMapping("/sensores/{id}")
    public Sensores update(@RequestBody Sensores sensores,@PathVariable Long id){
        Sensores sensorActual = iSensoresService.findById(id);
        sensorActual.setNombre(sensores.getNombre());
        sensorActual.setId_cuenta(sensores.getId_cuenta());
        sensorActual.setAlerta_amarilla(sensores.getAlerta_amarilla());
        sensorActual.setAlerta_roja(sensores.getAlerta_roja());
        sensorActual.setTipo(sensores.getTipo());
        sensorActual.setUnidad(sensores.getUnidad());

        return iSensoresService.save(sensorActual);
    }

    @DeleteMapping("/sensores/{id}")
    public int delete(@PathVariable Long id){
        iSensoresService.delete(id);
        return 1;
    }
}
