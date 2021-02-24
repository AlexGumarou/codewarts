package com.codewarts.dao.IDao;

import com.codewarts.entity.Child;
import com.codewarts.entity.ChildGroup;
import com.codewarts.entity.Department;
import com.codewarts.entity.Staff;

import java.util.List;

public interface MainDao {
    Staff getAllStaff(String login);
    List<ChildGroup> getAllChildGroup(Department department);
    List<Child> getAllChildByGroupAndDepartment(Department department, int childGroup);
}
