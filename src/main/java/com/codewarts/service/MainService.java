package com.codewarts.service;

import com.codewarts.dao.MainDao;
import com.codewarts.entity.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MainService {
    private MainDao mainDao;

    @Autowired
    public void setMainDao(MainDao mainDao) {
        this.mainDao = mainDao;
    }

    public boolean checkLoginPass(String login, String pass){
        return mainDao.checkLoginPass(login, pass);
    }

    public List<Staff> getAllStaff() {
        return mainDao.getAllStaff();
    }

    public Staff getStaff(String login, String pass) {
        List<Staff> list = getAllStaff();
        for (Staff staff : list){
            if (staff.getLogin().equals(login) && staff.getPass().equals(pass)){
                return staff;
            }
        } return null;
    }
}
