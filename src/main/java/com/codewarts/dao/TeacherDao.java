package com.codewarts.dao;

import com.codewarts.entity.*;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class TeacherDao {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<ChildGroup> getAllGroupChild(Department department) {
        return sessionFactory.getCurrentSession()
                .createQuery("from ChildGroup ch where ch.department.id = :id", ChildGroup.class)
                .setParameter("id", department.getId()).list();
    }

    public List<Child> getAllChildByGroupAndDepartment(Department department, int childGroup) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Child c where c.childGroup.id = :id and c.childGroup.department.id = :idDep",
                        Child.class)
                .setParameter("id", childGroup)
                .setParameter("idDep", department.getId())
                .list();
    }

    public List<Theme> getAllTheme() {
        return sessionFactory.getCurrentSession().createQuery("from Theme", Theme.class).list();
    }

    public Staff getAllStaff(String login) {
        return sessionFactory.getCurrentSession().createQuery("from Staff s where s.login = :login", Staff.class)
                .setParameter("login", login).getSingleResult();
    }

    public void saveChildAttendance(LocalDate date, String[] child) {
        for (String s : child) {
            Child ch = sessionFactory.getCurrentSession().get(Child.class, Integer.parseInt(s));
            sessionFactory.getCurrentSession().save(new Attendance(date, ch));
        }
    }

    public void saveTeacherAttendance(Staff staff, Integer group_id, LocalDate date, String theme) {
        ChildGroup childGroup = sessionFactory.getCurrentSession().get(ChildGroup.class, group_id);
        Theme them = sessionFactory.getCurrentSession().get(Theme.class, Integer.parseInt(theme));
        sessionFactory.getCurrentSession().save(new Accounting(date, childGroup, staff, them));
    }

    public List<Accounting> getAllAccounting(Integer childGroup) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Accounting a where a.childGroup.id = :id", Accounting.class)
                .setParameter("id", childGroup).list();
    }
}
