package com.codewarts.service.IService;

import com.codewarts.entity.Department;
import com.codewarts.entity.Staff;
import com.codewarts.entity.StaffRole;

import java.time.LocalDate;
import java.util.List;

public interface DirectorService {
    List<StaffRole> getAllStaffRoles();
    List<Department> getAllDepartments();
    List<Staff> getAllTeachers(Department department);
    boolean changePricePerHour(int idTeacher, String pricePerHour);
    boolean addStaff(Staff staff, int idRole, int idDepartment);
    int getAllQuantityByTeacher(int idTeacher, LocalDate dateFrom, LocalDate dateTo);
    int getPricePerHourByTeacher(int idTeacher);
    int getAllPayments(LocalDate dateFrom, LocalDate dateTo);
    double getAllHoursByTeacher(int idTeacher, LocalDate dateFrom, LocalDate dateTo);
}
