package com.codewarts.dao.IDao;

import com.codewarts.entity.*;

import java.time.LocalDate;
import java.util.List;

public interface AdminDao {
    Child getChildById(int editChild);
    List<Child> getAllChild(Department department);
    List<Child> findChildBySurname(String findChild, Department department);
    List<Attendance> getAllAttendanceByChild(int idChild);
    List<Payment> getAllPaymentsByChild(int idChild);
    List<Payment> getAllPaymentsByChildAndPayment(int idPayment);
    ChildGroup getChildGroupById(int idChildGroup);
    void saveChild(int idChild, LocalDate date, String name, String surname, int idGroup, Parent parent);
    void saveChild(int idChild, LocalDate date, String name, String surname, int idGroup);
    void savePayment(int idPayment, LocalDate date, String sum);
    void saveChildGroup(int idChildGroup, String name);
    void addChild(LocalDate date, String name, String surname, Parent parent, int idGroup);
    void addChild(LocalDate date, String name, String surname, int idGroup);
    void addPayment(LocalDate date, String sum, int idChild);
    void addGroup(String name, Department department, String lessonTime);
    void deleteGroup(int idChildGroup);
    void deleteChild(int idChild);

}
