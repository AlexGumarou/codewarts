package com.codewarts.service;

import com.codewarts.dao.AdminDao;
import com.codewarts.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AdminService {

    private AdminDao adminDao;

    @Autowired
    public void setAdminDao(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    public Child getChildById(int editChild) {
        return adminDao.getChildById(editChild);
    }

    public List<Department> getAllDepartments(){
        return adminDao.getAllDepartments();
    }

    public List<ChildGroup> getAllGroupChild(Department department){
        return adminDao.getAllGroupChild().stream().filter(s->s.getDepartment().getId()==(department.getId()))
                .collect(Collectors.toList());
    }

    public boolean saveChild(int idChild, LocalDate date, String name, String surname, int idGroup, String mother,
                          String phoneMother, String father, String phoneFather) {
        if((date == null || date.isBefore(LocalDate.now()) && date.isAfter(LocalDate.ofYearDay(2000, 1)))
                || !name.trim().equals("") || !surname.trim().equals("")) {
            if (mother.equals("") && father.equals("") && phoneMother.equals("") && phoneFather.equals("")) {
                adminDao.saveChild(idChild, date, name, surname, idGroup);
            } else {
                adminDao.saveChild(idChild, date, name, surname, idGroup, mother, father, phoneFather, phoneMother);
            }
            return true;
        } return false;
    }

    public void deleteChild(int idChild) {
        adminDao.deleteChild(idChild);
    }

    public List<Child> findChildBySurname(String findChild, Department department) {
        return adminDao.findChildBySurname(findChild).stream().filter(s->s.getChildGroup().getDepartment().getId() ==
                (department.getId())).collect(Collectors.toList());
    }

    public boolean addChild(LocalDate date, String name, String surname, String mother, String father, String phoneMother,
                         String phoneFather, int idGroup) {
        if((date == null || date.isBefore(LocalDate.now()) && date.isAfter(LocalDate.ofYearDay(2000, 1)))
                || !name.trim().equals("") || !surname.trim().equals("")){
            if (mother.equals("") && father.equals("") && phoneMother.equals("") && phoneFather.equals("")) {
                adminDao.addChild(date,name, surname, idGroup);
            } else {
                adminDao.addChild(date,name, surname, mother, father, phoneMother, phoneFather, idGroup);
            }
            return true;
        } return false;
    }

    public List<Attendance> getAllAttendanceByChild(int idChild) {
        return adminDao.getAllAttendanceByChild(idChild);
    }

    public List<Payment> getAllPaymentsByChild(int idChild) {
        return adminDao.getAllPaymentsByChild(idChild);
    }

    public List<StaffRole> getAllStaffRoles(){
        return adminDao.getAllStaffRoles();
    }

    public boolean addStaff(Staff staff, int idRole, int idDepartment) {
        if (adminDao.getAllStaff().stream().anyMatch(s->s.getLogin().equals(staff.getLogin()) &&
                s.getPass().equals(staff.getPass()))){
            return false;
        }
        adminDao.addStaff(staff, idRole, idDepartment);
        return true;
    }

    public List<Child> getAllChild (Department department){
        return adminDao.getAllChild().stream().filter(s->s.getChildGroup().getDepartment().getId()==department.getId())
                .collect(Collectors.toList());
    }

    public boolean addPayment(LocalDate date, String sum, int idChild) {
        try{
            Integer.parseInt(sum);
            adminDao.addPayment(date, sum, idChild);
            return true;
        } catch (NumberFormatException | IllegalFormatException e){
            return false;
        }

    }

    public boolean addGroup(String name, Department department) {
        String regExp = "^[а-яА-ЯёЁa-zA-Z0-9]+$";
        List<ChildGroup> list = adminDao.getAllChildGroup();
        if (name.trim().matches(regExp) && list.stream().noneMatch(s->s.getName().equals(name)
                && s.getDepartment().getId()==department.getId())) {
            adminDao.addGroup(name, department);
            return true;
        } return false;
    }

    public List<Payment> getAllPaymentsByChildAndPayment(int idPayment) {
        return adminDao.getAllPaymentsByChildAndPayment(idPayment);
    }

    public boolean savePayment(int idPayment, LocalDate date, String sum) {
        try{
            Integer.parseInt(sum);
            adminDao.savePayment(idPayment, date, sum);
            return true;
        } catch (NumberFormatException | IllegalFormatException e){
            return false;
        }
    }

    public ChildGroup getChildGroup(int idChildGroup) {
        return adminDao.getChildGroup(idChildGroup);
    }

    public void saveChildGroup(int idChildGroup, String name) {
        adminDao.saveChildGroup(idChildGroup, name);
    }

    public void deleteGroup(int idChildGroup) {
        adminDao.deleteGroup(idChildGroup);
    }

    public List<Child> getAllChildByGroupAndDepartment(Department department, int childGroup) {
        return adminDao.getAllChildByGroupAndDepartment().stream().filter(s->s.getChildGroup()
                .getId()==childGroup && s.getChildGroup().getDepartment().getId()==department.getId())
                .collect(Collectors.toList());
    }
}
