package application.core.test.dao.implementation;

import application.core.test.dao.TestDao;
import application.dto.TestDto;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TestDaoImplementation implements TestDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public TestDto findById(Integer id) {
        return sessionFactory.getCurrentSession().get(TestDto.class, id);
    }

    @Override
    public void insert(TestDto test) {
        sessionFactory.getCurrentSession().save(test);
    }
}
