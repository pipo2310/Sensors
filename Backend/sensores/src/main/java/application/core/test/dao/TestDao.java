package application.core.test.dao;

import application.dto.TestDto;

public interface TestDao {

    TestDto findById(Integer id);
    void insert(TestDto test);
}
