package com.codewarts.dao;

import com.codewarts.entity.Child;
import com.codewarts.entity.ChildGroup;
import com.codewarts.entity.Department;
import com.codewarts.entity.Parent;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdminDao {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Child getChildById(int editChild) {
        return sessionFactory.getCurrentSession().get(Child.class, editChild);
    }

    public List<Department> getAllDepartments() {
        return sessionFactory.getCurrentSession().createQuery("from Department ", Department.class).list();
    }

    public void saveChild(int idChild, String name, String surname, int idGroup, String mother,
                          String father, String phoneFather, String phoneMother) {
        Child child = sessionFactory.getCurrentSession().get(Child.class, idChild);
        ChildGroup childGroup = sessionFactory.getCurrentSession().get(ChildGroup.class, idGroup);
        int idParent = child.getParent().getId();
        Parent parent = sessionFactory.getCurrentSession().get(Parent.class, idParent);
        parent.setFather(father);
        parent.setMother(mother);
        parent.setPhoneFather(phoneFather);
        parent.setPhoneMother(phoneMother);
        child.setChildGroup(childGroup);
        child.setName(name);
        child.setSurname(surname);
        child.setParent(parent);
        sessionFactory.getCurrentSession().update(child);
    }
}
