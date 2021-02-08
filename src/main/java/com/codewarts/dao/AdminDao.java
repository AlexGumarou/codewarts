package com.codewarts.dao;

import com.codewarts.entity.*;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
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

    public void saveChild(int idChild, LocalDate date, String name, String surname, int idGroup, String mother,
                          String father, String phoneFather, String phoneMother) {
        Child child = sessionFactory.getCurrentSession().get(Child.class, idChild);
        ChildGroup childGroup = sessionFactory.getCurrentSession().get(ChildGroup.class, idGroup);
        Parent parent;
        child.setChildGroup(childGroup);
        child.setName(name);
        child.setSurname(surname);
        child.setBirthdayDate(date);
        if (child.getParent()!=null){
            int idParent = child.getParent().getId();
            parent = sessionFactory.getCurrentSession().get(Parent.class, idParent);
            parent.setFather(father);
            parent.setMother(mother);
            parent.setPhoneFather(phoneFather);
            parent.setPhoneMother(phoneMother);
        } else {
            parent = new Parent(mother, father, phoneMother, phoneFather);
        }
        sessionFactory.getCurrentSession().saveOrUpdate(parent);
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

    public void deleteChild(int idChild) {
        Child child = sessionFactory.getCurrentSession().get(Child.class, idChild);
        sessionFactory.getCurrentSession().remove(child);
    }

    public List<Child> findChildBySurname(String findChild) {
        return sessionFactory.getCurrentSession().createQuery("from Child ch where ch.surname = :surname")
                .setParameter("surname", findChild).list();
    }

    public void addChild(LocalDate date, String name, String surname, String mother, String father, String phoneMother,
                         String phoneFather, int idGroup) {
        ChildGroup childGroup = sessionFactory.getCurrentSession().get(ChildGroup.class, idGroup);
        Parent parent = new Parent(mother, father, phoneMother, phoneFather);
        sessionFactory.getCurrentSession().saveOrUpdate(parent);
        Child child = new Child(name,surname,date,parent,childGroup);
        sessionFactory.getCurrentSession().saveOrUpdate(child);
    }

    public void addChild(LocalDate date, String name, String surname, int idGroup) {
        ChildGroup childGroup = sessionFactory.getCurrentSession().get(ChildGroup.class, idGroup);
        Child child = new Child(name,surname,date,childGroup);
        sessionFactory.getCurrentSession().saveOrUpdate(child);
    }

    public List<Attendance> getAllAttendanceByChild(int idChild) {
        Child child = sessionFactory.getCurrentSession().get(Child.class,idChild);
        return child.getAttendance();
    }

    public List<Payment> getAllPaymentsByChild(int idChild) {
        Child child = sessionFactory.getCurrentSession().get(Child.class,idChild);
        return child.getPayment();
    }

    public List<StaffRole> getAllStaffRoles() {
        return sessionFactory.getCurrentSession().createQuery("from StaffRole", StaffRole.class).list();
    }

    public void addStaff(Staff staff, int idRole, int idDepartment) {
        StaffRole staffRole = sessionFactory.getCurrentSession().get(StaffRole.class, idRole);
        Department department = sessionFactory.getCurrentSession().get(Department.class, idDepartment);
        staff.setStaffRole(staffRole);
        staff.setDepartment(department);
        sessionFactory.getCurrentSession().saveOrUpdate(staff);
    }

    public List<ChildGroup> getAllGroupChild() {
        return sessionFactory.getCurrentSession().createQuery("from ChildGroup", ChildGroup.class).list();
    }

    public List<Staff> getAllStaff() {
        return sessionFactory.getCurrentSession().createQuery("from Staff", Staff.class).list();
    }

    public List<Child> getAllChild() {
        return sessionFactory.getCurrentSession().createQuery("from Child ", Child.class).list();
    }

    public void addPayment(LocalDate date, String sum, int idChild) {
        Child child = sessionFactory.getCurrentSession().get(Child.class, idChild);
        sessionFactory.getCurrentSession().saveOrUpdate(new Payment(date,sum,child));
    }

    public void addGroup(String name, Department department) {
        sessionFactory.getCurrentSession().saveOrUpdate(new ChildGroup(name, department));
    }

    public List<ChildGroup> getAllChildGroup() {
        return sessionFactory.getCurrentSession().createQuery("from ChildGroup", ChildGroup.class).list();
    }

    public List<Payment> getAllPaymentsByChildAndPayment(int idPayment) {
        return sessionFactory.getCurrentSession().createQuery("from Payment p where p.id = :id")
                .setParameter("id", idPayment).list();
    }

    public void savePayment(int idPayment, LocalDate date, String sum) {
        Payment payment = sessionFactory.getCurrentSession().get(Payment.class, idPayment);
        payment.setPaymentDate(date);
        payment.setSum(sum);
        sessionFactory.getCurrentSession().saveOrUpdate(payment);
    }

    public ChildGroup getChildGroup(int idChildGroup) {
        return sessionFactory.getCurrentSession().get(ChildGroup.class, idChildGroup);
    }

    public void saveChildGroup(int idChildGroup, String name) {
        ChildGroup childGroup = sessionFactory.getCurrentSession().get(ChildGroup.class, idChildGroup);
        childGroup.setName(name);
        sessionFactory.getCurrentSession().saveOrUpdate(childGroup);
    }

    public void deleteGroup(int idChildGroup) {
        ChildGroup childGroup = sessionFactory.getCurrentSession().get(ChildGroup.class, idChildGroup);
        sessionFactory.getCurrentSession().delete(childGroup);
    }
}
