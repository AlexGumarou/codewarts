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

    public List<Staff> getAllStaff() {
        return mainDao.getAllStaff();
    }

    public Staff getStaff(String login) {
        return getAllStaff().stream().filter(s->login.equals(s.getLogin())).findFirst().orElse(null);
    }

}
