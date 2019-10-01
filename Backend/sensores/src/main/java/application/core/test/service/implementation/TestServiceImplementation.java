package application.core.test.service.implementation;

import application.core.test.dao.TestDao;
import application.core.test.service.TestService;
import application.dto.TestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TestServiceImplementation implements TestService {

    @Autowired
    TestDao testDao;

    @Override
    public TestDto findById(Integer id) {
        return this.testDao.findById(id);
    }

    @Override
    public void insert(TestDto test) {
        this.testDao.insert(test);
    }
}
