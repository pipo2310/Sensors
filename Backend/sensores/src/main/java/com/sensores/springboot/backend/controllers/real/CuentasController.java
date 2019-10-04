package com.sensores.springboot.backend.controllers.real;

import com.sensores.springboot.backend.model.entity.real.Cuentas;
import com.sensores.springboot.backend.services.real.ICuentasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CuentasController {

    @Autowired
    ICuentasService iCuentasService;

    @GetMapping("/cuentas")
    public List<Cuentas> index(){
        return this.iCuentasService.findAll();
    }
}
