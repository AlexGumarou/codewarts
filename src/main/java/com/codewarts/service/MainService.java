package com.codewarts.service;

import com.codewarts.dao.MainDao;
import com.codewarts.entity.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MainService {
    private MainDao mainDao;

    @Autowired
    public void setMainDao(MainDao mainDao) {
        this.mainDao = mainDao;
    }

    public Staff getStaff(String login) {
        return mainDao.getAllStaff(login);
    }

}
