package com.codewarts.service.IService;

import com.codewarts.entity.*;

import java.time.LocalDate;
import java.util.List;

public interface AdminService {
    Child getChildById(int editChild);
    ChildGroup getChildGroup(int idChildGroup);
    List<Child> getAllChild (Department department);
    List<Child> findChildBySurname(String findChild, Department department);
    List<Child> getAllChildBirthday(Department department);
    List<Attendance> getAllAttendanceByChild(int idChild);
    List<Payment> getAllPaymentsByChild(int idChild);
    List<Payment> getAllPaymentsByChildAndPayment(int idPayment);
    boolean addPayment(LocalDate date, String sum, int idChild);
    boolean addGroup(String name, Department department, String lessonTime);
    boolean savePayment(int idPayment, LocalDate date, String sum);
    boolean saveChildGroup(int idChildGroup, String name, Department department);
    boolean addChild(LocalDate date, String name, String surname, String mother, String father,
                     String phoneMother, String phoneFather, int idGroup);
    boolean saveChild(int idChild, LocalDate date, String name, String surname, int idGroup, String mother,
                      String phoneMother, String father, String phoneFather);
    void deleteGroup(int idChildGroup);
    void deleteChild(int idChild);

}
