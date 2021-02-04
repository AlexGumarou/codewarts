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

    public boolean checkLoginPass(String login, String pass){
        List<Staff> list = getAllStaff();
        long size = list.stream().filter(s->s.getLogin().equals(login) && s.getPass().equals(pass)).count();
        return size == 1;
    }

}
