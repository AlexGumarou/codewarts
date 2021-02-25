package com.codewarts.dao;

import com.codewarts.dao.IDao.AdminDao;
import com.codewarts.entity.*;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class AdminDaoImpl implements AdminDao {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Child getChildById(int editChild) {
        return sessionFactory.getCurrentSession().get(Child.class, editChild);
    }

    public List<Child> getAllChild(Department department) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Child c join fetch c.childGroup " +
                        "where c.childGroup.department.id = :id", Child.class)
                .setParameter("id", department.getId()).list();
    }

    public List<Attendance> getAllAttendanceByChild(int idChild) {
        Child child = sessionFactory.getCurrentSession().get(Child.class,idChild);
        return child.getAttendance();
    }

    public List<Payment> getAllPaymentsByChild(int idChild) {
        Child child = sessionFactory.getCurrentSession().get(Child.class,idChild);
        return child.getPayment();
    }

    public ChildGroup getChildGroupById(int idChildGroup) {
        return sessionFactory.getCurrentSession().get(ChildGroup.class, idChildGroup);
    }

    public List<Payment> getAllPaymentsByChildAndPayment(int idPayment) {
        return sessionFactory.getCurrentSession().createQuery("from Payment p where p.id = :id")
                .setParameter("id", idPayment).list();
    }

    public void saveChild(int idChild, LocalDate date, String name, String surname, int idGroup, Parent parent) {
        Child child = sessionFactory.getCurrentSession().get(Child.class, idChild);
        saveChild(idChild, date, name,surname,idGroup);
        child.setParent(parent);
        sessionFactory.getCurrentSession().saveOrUpdate(child);
    }

    public void saveChild(int idChild, LocalDate date, String name, String surname, int idGroup) {
        Child child = sessionFactory.getCurrentSession().get(Child.class, idChild);
        ChildGroup childGroup = sessionFactory.getCurrentSession().get(ChildGroup.class, idGroup);
        child.setChildGroup(childGroup);
        child.setName(name);
        child.setSurname(surname);
        child.setBirthdayDate(date);
        sessionFactory.getCurrentSession().saveOrUpdate(child);
    }

    public void savePayment(int idPayment, LocalDate date, String sum) {
        Payment payment = sessionFactory.getCurrentSession().get(Payment.class, idPayment);
        payment.setPaymentDate(date);
        payment.setSum(sum);
        sessionFactory.getCurrentSession().saveOrUpdate(payment);
    }

    public void saveChildGroup(int idChildGroup, String name) {
        ChildGroup childGroup = sessionFactory.getCurrentSession().get(ChildGroup.class, idChildGroup);
        childGroup.setName(name);
        sessionFactory.getCurrentSession().saveOrUpdate(childGroup);
    }

    public void addChild(LocalDate date, String name, String surname, Parent parent, int idGroup) {
        ChildGroup childGroup = sessionFactory.getCurrentSession().get(ChildGroup.class, idGroup);
        sessionFactory.getCurrentSession().saveOrUpdate(parent);
        Child child = new Child(name,surname,date,parent,childGroup);
        sessionFactory.getCurrentSession().saveOrUpdate(child);
    }

    public void addChild(LocalDate date, String name, String surname, int idGroup) {
        ChildGroup childGroup = sessionFactory.getCurrentSession().get(ChildGroup.class, idGroup);
        Child child = new Child(name,surname,date,childGroup);
        sessionFactory.getCurrentSession().saveOrUpdate(child);
    }

    public void addPayment(LocalDate date, String sum, int idChild) {
        Child child = sessionFactory.getCurrentSession().get(Child.class, idChild);
        sessionFactory.getCurrentSession().saveOrUpdate(new Payment(date,sum,child));
    }

    public void addGroup(String name, Department department, String lessonTime) {
        sessionFactory.getCurrentSession().saveOrUpdate(new ChildGroup(name, lessonTime, department));
    }

    public List<Child> findChildBySurname(String findChild, Department department) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Child ch join fetch ch.childGroup where ch.surname = :surname and" +
                        " ch.childGroup.department.id = :id")
                .setParameter("id", department.getId())
                .setParameter("surname", findChild).list();
    }

    public void deleteGroup(int idChildGroup) {
        ChildGroup childGroup = sessionFactory.getCurrentSession().get(ChildGroup.class, idChildGroup);
        sessionFactory.getCurrentSession().delete(childGroup);
    }

    public void deleteChild(int idChild) {
        Child child = sessionFactory.getCurrentSession().get(Child.class, idChild);
        sessionFactory.getCurrentSession().remove(child);
    }
}
