package com.codewarts.dao;

import com.codewarts.entity.Staff;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MainDao {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Staff getAllStaff(String login) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Staff s join fetch s.department join fetch s.staffRole where s.login = :login",
                        Staff.class)
                .setParameter("login", login).getSingleResult();
    }
}
