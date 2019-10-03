package com.sensores.springboot.backend.services.foo;

import com.sensores.springboot.backend.dao.foo.ITestDao;
import com.sensores.springboot.backend.model.entity.foo.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements ITestService{

    @Autowired
    ITestDao iTestDao;

    @Override
    public List<Test> findAll() {
        return (List<Test>)this.iTestDao.findAll();
    }
}
