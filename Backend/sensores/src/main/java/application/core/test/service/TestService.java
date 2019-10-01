package application.core.test.service;

import application.dto.TestDto;

public interface TestService {

    TestDto findById(Integer id);
    void insert(TestDto test);
}
