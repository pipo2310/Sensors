package com.sensores.springboot.backend.controllers.foo;

import com.sensores.springboot.backend.model.entity.foo.Test;
import com.sensores.springboot.backend.services.foo.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    ITestService iTestService;

    @GetMapping("/test")
    public List<Test> index(){
        return this.iTestService.findAll();
    }
}
