package com.codewarts.dao;

import com.codewarts.dao.IDao.MainDao;
import com.codewarts.entity.Child;
import com.codewarts.entity.ChildGroup;
import com.codewarts.entity.Department;
import com.codewarts.entity.Staff;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MainDaoImpl implements MainDao {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Staff getAllStaff(String login) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Staff s join fetch s.department join fetch s.staffRole " +
                                "where s.login = :login", Staff.class)
                .setParameter("login", login)
                .getSingleResult();
    }

    public List<ChildGroup> getAllChildGroup(Department department) {
        return sessionFactory.getCurrentSession()
                .createQuery("from ChildGroup ch join fetch ch.department " +
                        "where ch.department.id = :id", ChildGroup.class)
                .setParameter("id", department.getId())
                .list();
    }

    public List<Child> getAllChildByGroupAndDepartment(Department department, int childGroup) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Child c join fetch c.childGroup where c.childGroup.id = :id " +
                        "and c.childGroup.department.id = :idDep", Child.class)
                .setParameter("id", childGroup)
                .setParameter("idDep", department.getId())
                .list();
    }
}
