package com.codewarts.service;

import com.codewarts.dao.AdminDao;
import com.codewarts.entity.Attendance;
import com.codewarts.entity.Child;
import com.codewarts.entity.Department;
import com.codewarts.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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

    public boolean saveChild(int idChild, LocalDate date, String name, String surname, int idGroup, String mother,
                          String phoneMother, String father, String phoneFather) {
        if((date.isBefore(LocalDate.now()) && date.isAfter(LocalDate.ofYearDay(2000, 1)))
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
        if((date.isBefore(LocalDate.now()) && date.isAfter(LocalDate.ofYearDay(2000, 1)))
                || !name.trim().equals("") || !surname.trim().equals("")){
            if (mother.equals("") && father.equals("") && phoneMother.equals("") && phoneFather.equals("")) {
                adminDao.addChild(date,name, surname, idGroup);
            } else {
                adminDao.addChild(date,name, surname, mother, father, phoneMother, phoneFather, idGroup);
            }
            return true;
        } return false;
    }

    public int getGroupByChild(int idChild) {
        return adminDao.getGroupByChild(idChild);
    }

    public List<Attendance> getAllAttendanceByChild(int idChild) {
        return adminDao.getAllAttendanceByChild(idChild);
    }

    public List<Payment> getAllPaymentsByChild(int idChild) {
        return adminDao.getAllPaymentsByChild(idChild);
    }
}
