package com.codewarts.service;

import com.codewarts.dao.TeacherDaoImpl;
import com.codewarts.entity.*;
import com.codewarts.service.IService.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {
    private TeacherDaoImpl teacherDaoImpl;

    @Autowired
    public void setTeacherDao(TeacherDaoImpl teacherDaoImpl) {
        this.teacherDaoImpl = teacherDaoImpl;
    }

    public List<Theme> getAllTheme() {
        return teacherDaoImpl.getAllTheme();
    }

    public void saveChildAttendance(String[] child) {
        LocalDate date = LocalDate.now();
        teacherDaoImpl.saveChildAttendance(date, child);
    }

    public void saveTeacherAttendance(Staff staff, Integer group_id, String theme) {
        LocalDate date = LocalDate.now();
        teacherDaoImpl.saveTeacherAttendance(staff, group_id, date, theme);
    }

    public boolean checkDoubleClick(Integer group_id) {
        LocalDate date = LocalDate.now();
        return teacherDaoImpl.getAllAccounting(group_id).stream().anyMatch(s->s.getDate().equals(date));
    }

    public List<Accounting> getAllThemePast(Integer childGroup) {
        return teacherDaoImpl.getAllAccounting(childGroup);
    }
}
