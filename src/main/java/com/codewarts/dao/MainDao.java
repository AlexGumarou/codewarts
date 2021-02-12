package com.codewarts.dao;

import com.codewarts.entity.Staff;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class MainDao {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Staff> getAllStaff() {
        return sessionFactory.getCurrentSession().createQuery("from Staff", Staff.class).list();
    }
}
