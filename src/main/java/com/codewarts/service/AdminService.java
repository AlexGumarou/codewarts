package com.codewarts.service;

import com.codewarts.dao.AdminDao;
import com.codewarts.entity.Child;
import com.codewarts.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public void saveChild(int idChild, String name, String surname, int idGroup, String mother,
                          String phoneMother, String father, String phoneFather, int idDepartment) {
        adminDao.saveChild(idChild,name,surname,idGroup, mother, father,phoneFather,phoneMother);
    }
}
