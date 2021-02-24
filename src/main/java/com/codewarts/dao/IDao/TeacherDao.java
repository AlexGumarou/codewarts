package com.codewarts.dao.IDao;

import com.codewarts.entity.Accounting;
import com.codewarts.entity.Staff;
import com.codewarts.entity.Theme;

import java.time.LocalDate;
import java.util.List;

public interface TeacherDao {
    List<Theme> getAllTheme();
    void saveChildAttendance(LocalDate date, String[] child);
    void saveTeacherAttendance(Staff staff, Integer group_id, LocalDate date, String theme);
    List<Accounting> getAllAccounting(Integer childGroup);
}
