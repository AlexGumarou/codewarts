package com.codewarts.service;

import com.codewarts.dao.DirectorDao;
import com.codewarts.entity.Accounting;
import com.codewarts.entity.Department;
import com.codewarts.entity.Staff;
import com.codewarts.entity.StaffRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DirectorService {
    private DirectorDao directorDao;
    @Autowired
    public void setDirectorDao(DirectorDao directorDao) {
        this.directorDao = directorDao;
    }

    public List<StaffRole> getAllStaffRoles(){
        return directorDao.getAllStaffRoles();
    }

    public List<Department> getAllDepartments(){
        return directorDao.getAllDepartments();
    }

    public boolean addStaff(Staff staff, int idRole, int idDepartment) {
        if (directorDao.getAllStaff().stream().anyMatch(s->s.getLogin().equals(staff.getLogin()) &&
                s.getPass().equals(staff.getPass()))){
            return false;
        }
        directorDao.addStaff(staff, idRole, idDepartment);
        return true;
    }

    public List<Staff> getAllTeachers(Department department) {
        return directorDao.getAllTeachers(department);
    }

    public double getAllHoursByTeacher(int idTeacher, LocalDate dateFrom, LocalDate dateTo) {
        List<Accounting> list = directorDao.getAllHoursByTeacher(idTeacher, dateFrom, dateTo);
        double count = 0;
        for (Accounting accounting : list){
            if (accounting.getChildGroup().getLessonTime().equals("1 час")){
                count++;
            } else if (accounting.getChildGroup().getLessonTime().equals("1 час 20 минут")){
                count = count + 1.2;
            }
        } return count;
    }

    public int getAllQuantityByTeacher(int idTeacher, LocalDate dateFrom, LocalDate dateTo) {
        return directorDao.getAllHoursByTeacher(idTeacher, dateFrom, dateTo).stream().collect(Collectors.toList()).size();

    }

    public int getPricePerHourByTeacher(int idTeacher) {
        return directorDao.getPricePerHourByTeacher(idTeacher);
    }
}
