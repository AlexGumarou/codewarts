package com.codewarts.service.IService;

import com.codewarts.entity.Accounting;
import com.codewarts.entity.Staff;
import com.codewarts.entity.Theme;

import java.util.List;

public interface TeacherService {
    List<Theme> getAllTheme();
    void saveChildAttendance(String[] child);
    void saveTeacherAttendance(Staff staff, Integer group_id, String theme);
    boolean checkDoubleClick(Integer group_id);
    List<Accounting> getAllThemePast(Integer childGroup);
}
