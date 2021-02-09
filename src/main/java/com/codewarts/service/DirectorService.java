package com.codewarts.service;

import com.codewarts.dao.DirectorDao;
import com.codewarts.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.IllegalFormatException;
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
        try {
            Integer.parseInt(staff.getPricePerHour());
            if (directorDao.getAllStaff().stream().anyMatch(s->s.getLogin().equals(staff.getLogin()) &&
                    s.getPass().equals(staff.getPass()))){
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
        return directorDao.getAllHoursByTeacher(idTeacher, dateFrom, dateTo).stream().collect(Collectors.toList()).size();

    }

    public int getPricePerHourByTeacher(int idTeacher) {
        return Integer.parseInt(directorDao.getPricePerHourByTeacher(idTeacher));
    }

    public int getAllPayments(LocalDate dateFrom, LocalDate dateTo) {
       return directorDao.getAllPayments(dateFrom, dateTo).stream().mapToInt(s-> Integer.parseInt(s.getSum())).sum();
    }

    public boolean changePricePerHour(int idTeacher, String pricePerHour) {
        try{
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
