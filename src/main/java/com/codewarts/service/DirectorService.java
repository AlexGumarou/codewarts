package com.codewarts.service;

import com.codewarts.dao.DirectorDao;
import com.codewarts.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.List;

@Service
@Transactional
public class DirectorService {
    private DirectorDao directorDao;
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public void setDirectorDao(DirectorDao directorDao) {
        this.directorDao = directorDao;
    }

    @Autowired
    public void setBCryptPasswordEncoder(PasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public List<StaffRole> getAllStaffRoles(){
        return directorDao.getAllStaffRoles();
    }

    public List<Department> getAllDepartments(){
        return directorDao.getAllDepartments();
    }

    public boolean addStaff(Staff staff, int idRole, int idDepartment) {
        staff.setPass(bCryptPasswordEncoder.encode(staff.getPassword()));
        try {
            if (!staff.getPricePerHour().equals("")) {
                Integer.parseInt(staff.getPricePerHour());
            }
            if (directorDao.getAllStaff().stream().anyMatch(s->s.getLogin().trim().equals(staff.getLogin().trim()))){
                return false;
            }
            directorDao.addStaff(staff, idRole, idDepartment);
            return true;
        } catch (NumberFormatException | IllegalFormatException e){
            return false;
        }
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
        return new ArrayList<>(directorDao.getAllHoursByTeacher(idTeacher, dateFrom, dateTo)).size();
    }

    public int getPricePerHourByTeacher(int idTeacher) {
        return Integer.parseInt(directorDao.getPricePerHourByTeacher(idTeacher));
    }

    public int getAllPayments(LocalDate dateFrom, LocalDate dateTo) {
       return directorDao.getAllPayments(dateFrom, dateTo).stream().mapToInt(s-> Integer.parseInt(s.getSum())).sum();
    }

    public boolean changePricePerHour(int idTeacher, String pricePerHour) {
        try {
            int price = Integer.parseInt(pricePerHour);
            if (price > 0) {
                directorDao.changePricePerHour(idTeacher, pricePerHour);
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException | IllegalFormatException e){
            return false;
        }
    }
}
