package com.codewarts.service;

import com.codewarts.dao.MainDaoImpl;
import com.codewarts.entity.Child;
import com.codewarts.entity.ChildGroup;
import com.codewarts.entity.Department;
import com.codewarts.entity.Staff;
import com.codewarts.service.IService.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MainServiceImpl implements MainService {
    private MainDaoImpl mainDaoImpl;

    @Autowired
    public void setMainDao(MainDaoImpl mainDaoImpl) {
        this.mainDaoImpl = mainDaoImpl;
    }

    public Staff getStaff(String login) {
        return mainDaoImpl.getAllStaff(login);
    }

    public List<ChildGroup> getAllGroupChild(Department department){
        return mainDaoImpl.getAllChildGroup(department);
    }

    public List<Child> getAllChildByGroupAndDepartment(Department department, int childGroup) {
        return mainDaoImpl.getAllChildByGroupAndDepartment(department,childGroup);
    }
}
