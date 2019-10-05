package com.sensores.springboot.backend.services.foo;

import com.sensores.springboot.backend.model.entity.foo.Test;

import java.util.List;

public interface ITestService {
    public List<Test> findAll();
}
