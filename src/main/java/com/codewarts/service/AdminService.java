package com.codewarts.service;

import com.codewarts.dao.AdminDao;
import com.codewarts.entity.Child;
import com.codewarts.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

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
        if (date.isBefore(LocalDate.now()) && date.isAfter(LocalDate.ofYearDay(2000, 1))){
            adminDao.saveChild(idChild, date, name,surname,idGroup, mother, father,phoneFather,phoneMother);
            return true;
        } else {
            return false;
        }
    }

    public void deleteChild(int idChild) {
        adminDao.deleteChild(idChild);
    }
}
