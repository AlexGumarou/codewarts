package com.codewarts.service.IService;

import com.codewarts.entity.Child;
import com.codewarts.entity.ChildGroup;
import com.codewarts.entity.Department;
import com.codewarts.entity.Staff;

import java.util.List;

public interface MainService {
    Staff getStaff(String login);
    List<ChildGroup> getAllGroupChild(Department department);
    List<Child> getAllChildByGroupAndDepartment(Department department, int childGroup);
}
