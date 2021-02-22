package com.codewarts.service;

import com.codewarts.dao.TeacherDao;
import com.codewarts.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class TeacherService {
    private TeacherDao teacherDao;

    @Autowired
    public void setTeacherDao(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    public List<ChildGroup> getAllGroupChild(Department department){
        return teacherDao.getAllGroupChild(department);
    }

    public List<Child> getAllChildByGroupAndDepartment(Department department, int childGroup) {
        return teacherDao.getAllChildByGroupAndDepartment(department,childGroup);
    }

    public Staff getStaff(String login) {
        return teacherDao.getAllStaff(login);
    }

    public List<Theme> getAllTheme() {
        return teacherDao.getAllTheme();
    }

    public void saveChildAttendance(String[] child) {
        LocalDate date = LocalDate.now();
        teacherDao.saveChildAttendance(date, child);
    }

    public void saveTeacherAttendance(Staff staff, Integer group_id, String theme) {
        LocalDate date = LocalDate.now();
        teacherDao.saveTeacherAttendance(staff, group_id, date, theme);
    }

    public boolean checkDoubleClick(Integer group_id) {
        LocalDate date = LocalDate.now();
        return teacherDao.getAllAccounting(group_id).stream().anyMatch(s->s.getDate().equals(date));
    }

    public List<Accounting> getAllThemePast(Integer childGroup) {
        return teacherDao.getAllAccounting(childGroup);
    }
}
