package com.codewarts.dao.IDao;

import com.codewarts.entity.*;

import java.time.LocalDate;
import java.util.List;

public interface DirectorDao {
    List<Staff> getAllStaff();
    List<Staff> getAllTeachers(Department department);
    List<Department> getAllDepartments();
    List<StaffRole> getAllStaffRoles();
    List<Accounting> getAllHoursByTeacher(int idTeacher, LocalDate dateFrom, LocalDate dateTo);
    String getPricePerHourByTeacher(int idTeacher);
    List<Payment> getAllPayments(LocalDate dateFrom, LocalDate dateTo);
    void changePricePerHour(int idTeacher, String pricePerHour);
    void addStaff(Staff staff, int idRole, int idDepartment);
    void deleteStaff(int idStaff);
}
