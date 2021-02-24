package com.codewarts.dao;

import com.codewarts.dao.IDao.TeacherDao;
import com.codewarts.entity.*;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public class TeacherDaoImpl implements TeacherDao {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Theme> getAllTheme() {
        return sessionFactory.getCurrentSession().createQuery("from Theme", Theme.class).list();
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
