package com.codewarts.service;

import com.codewarts.dao.DirectorDaoImpl;
import com.codewarts.entity.*;
import com.codewarts.service.IService.DirectorService;
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
public class DirectorServiceImpl implements DirectorService {
    private DirectorDaoImpl directorDaoImpl;
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public void setDirectorDao(DirectorDaoImpl directorDaoImpl) {
        this.directorDaoImpl = directorDaoImpl;
    }

    @Autowired
    public void setBCryptPasswordEncoder(PasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public List<StaffRole> getAllStaffRoles(){
        return directorDaoImpl.getAllStaffRoles();
    }

    public List<Department> getAllDepartments(){
        return directorDaoImpl.getAllDepartments();
    }

    public boolean addStaff(Staff staff, int idRole, int idDepartment) {
        staff.setPass(bCryptPasswordEncoder.encode(staff.getPassword()));
        try {
            if (!staff.getPricePerHour().equals("")) {
                Integer.parseInt(staff.getPricePerHour());
            }
            if (directorDaoImpl.getAllStaff().stream().anyMatch(s->s.getLogin().trim().equals(staff.getLogin().trim()))){
                return false;
            }
            directorDaoImpl.addStaff(staff, idRole, idDepartment);
            return true;
        } catch (NumberFormatException | IllegalFormatException e){
            return false;
        }
    }

    public List<Staff> getAllTeachers(Department department) {
        return directorDaoImpl.getAllTeachers(department);
    }

    public double getAllHoursByTeacher(int idTeacher, LocalDate dateFrom, LocalDate dateTo) {
        List<Accounting> list = directorDaoImpl.getAllHoursByTeacher(idTeacher, dateFrom, dateTo);
        double count = 0;
        for (Accounting accounting : list){
            if (accounting.getChildGroup().getLessonTime().equals("1 час")){
                count++;
            } else if (accounting.getChildGroup().getLessonTime().equals("1 час 20 минут")){
                count = count + 1.2;
            }
        } return count;
    }

    public List<Staff> getAllStaff(){
        return directorDaoImpl.getAllStaff();
    }

    public void deleteStaff(int idStaff) {
        directorDaoImpl.deleteStaff(idStaff);
    }

    public int getAllQuantityByTeacher(int idTeacher, LocalDate dateFrom, LocalDate dateTo) {
        return new ArrayList<>(directorDaoImpl.getAllHoursByTeacher(idTeacher, dateFrom, dateTo)).size();
    }

    public int getPricePerHourByTeacher(int idTeacher) {
        return Integer.parseInt(directorDaoImpl.getPricePerHourByTeacher(idTeacher));
    }

    public int getAllPayments(LocalDate dateFrom, LocalDate dateTo) {
       return directorDaoImpl.getAllPayments(dateFrom, dateTo).stream().mapToInt(s-> Integer.parseInt(s.getSum())).sum();
    }

    public boolean changePricePerHour(int idTeacher, String pricePerHour) {
        try {
            int price = Integer.parseInt(pricePerHour);
            if (price > 0) {
                directorDaoImpl.changePricePerHour(idTeacher, pricePerHour);
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException | IllegalFormatException e){
            return false;
        }
    }
}
