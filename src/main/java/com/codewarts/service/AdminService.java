package com.codewarts.service;

import com.codewarts.dao.AdminDao;
import com.codewarts.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.List;

@Service
@Transactional
public class AdminService {
    private AdminDao adminDao;

    @Autowired
    public void setAdminDao(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    public Child getChildById(int editChild) {
        return adminDao.getChildById(editChild);
    }

    public List<ChildGroup> getAllGroupChild(Department department){
        return adminDao.getAllChildGroup(department);
    }

    public boolean saveChild(int idChild, LocalDate date, String name, String surname, int idGroup, String mother,
                          String phoneMother, String father, String phoneFather) {
        if((date == null || date.isBefore(LocalDate.now()) && date.isAfter(LocalDate.ofYearDay(2000, 1)))
                || !name.trim().equals("") || !surname.trim().equals("")) {
            if (mother.equals("") && father.equals("") && phoneMother.equals("") && phoneFather.equals("")) {
                adminDao.saveChild(idChild, date, name, surname, idGroup);
            } else {
                adminDao.saveChild(idChild, date, name, surname, idGroup, mother, father, phoneFather, phoneMother);
            }
            return true;
        } return false;
    }

    public void deleteChild(int idChild) {
        adminDao.deleteChild(idChild);
    }

    public List<Child> findChildBySurname(String findChild, Department department) {
        if (!findChild.trim().equals("")){
            String surnameChild = findChild.substring(0, 1).toUpperCase() + findChild.substring(1).toLowerCase();
            return adminDao.findChildBySurname(surnameChild, department);
        } return null;
    }

    public boolean addChild(LocalDate date, String name, String surname, String mother, String father, String phoneMother,
                         String phoneFather, int idGroup) {
        String regExp = "^[а-яА-ЯёЁa-zA-Z]+$";
        if((name.matches(regExp) && surname.matches(regExp) && (date == null || date.isBefore(LocalDate.now())
                && date.isAfter(LocalDate.ofYearDay(2000, 1))))){
            if (mother.equals("") && father.equals("") && phoneMother.equals("") && phoneFather.equals("")) {
                adminDao.addChild(date,name, surname, idGroup);
            } else {
                adminDao.addChild(date,name, surname, mother, father, phoneMother, phoneFather, idGroup);
            }
            return true;
        } return false;
    }

    public List<Attendance> getAllAttendanceByChild(int idChild) {
        return adminDao.getAllAttendanceByChild(idChild);
    }

    public List<Payment> getAllPaymentsByChild(int idChild) {
        return adminDao.getAllPaymentsByChild(idChild);
    }

    public List<Child> getAllChild (Department department){
        return adminDao.getAllChild(department);
    }

    public boolean addPayment(LocalDate date, String sum, int idChild) {
        try{
            if (Integer.parseInt(sum) > 0){
                adminDao.addPayment(date, sum, idChild);
                return true;
            }
            return false;
        } catch (NumberFormatException | IllegalFormatException e){
            return false;
        }
    }

    public boolean addGroup(String name, Department department, String lessonTime) {
        String regExp = "^[а-яА-ЯёЁa-zA-Z0-9]+$";
        List<ChildGroup> list = adminDao.getAllChildGroup(department);
        if (name.trim().matches(regExp) && list.stream().noneMatch(s->s.getName().equals(name))) {
            adminDao.addGroup(name, department, lessonTime);
            return true;
        } return false;
    }

    public List<Payment> getAllPaymentsByChildAndPayment(int idPayment) {
        return adminDao.getAllPaymentsByChildAndPayment(idPayment);
    }

    public boolean savePayment(int idPayment, LocalDate date, String sum) {
        try{
            Integer.parseInt(sum);
            adminDao.savePayment(idPayment, date, sum);
            return true;
        } catch (NumberFormatException | IllegalFormatException e){
            return false;
        }
    }

    public ChildGroup getChildGroup(int idChildGroup) {
        return adminDao.getChildGroupById(idChildGroup);
    }

    public boolean saveChildGroup(int idChildGroup, String name) {
        String regExp = "^[а-яА-ЯёЁa-zA-Z0-9]+$";
        if (!name.trim().equals("") && name.trim().matches(regExp)){
            adminDao.saveChildGroup(idChildGroup, name);
            return true;
        } return false;
    }

    public void deleteGroup(int idChildGroup) {
        adminDao.deleteGroup(idChildGroup);
    }

    public List<Child> getAllChildByGroupAndDepartment(Department department, int childGroup) {
        return adminDao.getAllChild(department, childGroup);
    }

    public List<Child> getAllChildBirthday(Department department) {
        LocalDate date = LocalDate.now();
        int monthNow = date.getMonthValue();
        int dayNow = date.getDayOfMonth();
        List<Child> listFilter = new ArrayList<>();
        List<Child> list = adminDao.getAllChild(department);
        for(Child child : list){
            if (child.getBirthdayDate() != null){
                if (child.getBirthdayDate().getMonthValue() == monthNow){
                    if ((child.getBirthdayDate().getDayOfMonth() - dayNow) <=3 &&
                            (child.getBirthdayDate().getDayOfMonth() - dayNow) >= 0){
                        listFilter.add(child);
                    }
                } else {
                    if (child.getBirthdayDate().getMonthValue() - monthNow == 1){
                        if ((dayNow - child.getBirthdayDate().getDayOfMonth()) > 27){
                            listFilter.add(child);
                        }
                    }
                }
            }
        } return listFilter;
    }
}
