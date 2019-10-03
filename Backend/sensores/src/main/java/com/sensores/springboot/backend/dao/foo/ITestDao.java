package com.sensores.springboot.backend.dao.foo;

import com.sensores.springboot.backend.model.entity.foo.Test;
import org.springframework.data.repository.CrudRepository;

public interface ITestDao extends CrudRepository<Test, Integer> {
}
