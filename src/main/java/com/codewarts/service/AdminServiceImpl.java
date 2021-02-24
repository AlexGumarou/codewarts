package com.codewarts.service;

import com.codewarts.dao.AdminDaoImpl;
import com.codewarts.dao.MainDaoImpl;
import com.codewarts.entity.*;
import com.codewarts.service.IService.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.List;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    private AdminDaoImpl adminDaoImpl;
    private MainDaoImpl mainDaoImpl;
    private final String regExp = "^[а-яА-ЯёЁa-zA-Z0-9]+$";

    @Autowired
    public void setAdminDao(AdminDaoImpl adminDaoImpl) {
        this.adminDaoImpl = adminDaoImpl;
    }

    @Autowired
    public void setMainDao(MainDaoImpl mainDaoImpl) {
        this.mainDaoImpl = mainDaoImpl;
    }

    public Child getChildById(int editChild) {
        return adminDaoImpl.getChildById(editChild);
    }

    public boolean saveChild(int idChild, LocalDate date, String name, String surname, int idGroup, String mother,
                          String phoneMother, String father, String phoneFather) {
        if(checkBeforeSave(date, name, surname)) {
            if (mother.equals("") && father.equals("") && phoneMother.equals("") && phoneFather.equals("")) {
                adminDaoImpl.saveChild(idChild, date, name, surname, idGroup);
            } else {
                adminDaoImpl.saveChild(idChild, date, name, surname, idGroup, mother, father, phoneFather, phoneMother);
            }
            return true;
        } return false;
    }

    public boolean addChild(LocalDate date, String name, String surname, String mother, String father,
                            String phoneMother, String phoneFather, int idGroup) {
        if(checkBeforeSave(date, name, surname)){
            String surnameRight = surname.substring(0, 1).toUpperCase() + surname.substring(1).toLowerCase();
            if (mother.equals("") && father.equals("") && phoneMother.equals("") && phoneFather.equals("")) {
                adminDaoImpl.addChild(date,name, surnameRight, idGroup);
            } else {
                adminDaoImpl.addChild(date,name, surnameRight, mother, father, phoneMother, phoneFather, idGroup);
            }
            return true;
        } return false;
    }

    public boolean checkBeforeSave(LocalDate date, String name, String surname){
        return date != null && !name.trim().equals("") &&
                !surname.trim().equals("") && name.matches(regExp) && surname.matches(regExp);
    }

    public void deleteChild(int idChild) {
        adminDaoImpl.deleteChild(idChild);
    }

    public List<Child> findChildBySurname(String findChild, Department department) {
        if (!findChild.trim().equals("")){
            String surnameChild = findChild.substring(0, 1).toUpperCase() + findChild.substring(1).toLowerCase();
            return adminDaoImpl.findChildBySurname(surnameChild, department);
        } return null;
    }

    public List<Attendance> getAllAttendanceByChild(int idChild) {
        return adminDaoImpl.getAllAttendanceByChild(idChild);
    }

    public List<Payment> getAllPaymentsByChild(int idChild) {
        return adminDaoImpl.getAllPaymentsByChild(idChild);
    }

    public List<Child> getAllChild (Department department){
        return adminDaoImpl.getAllChild(department);
    }

    public boolean addPayment(LocalDate date, String sum, int idChild) {
        try{
            if (Integer.parseInt(sum) > 0){
                adminDaoImpl.addPayment(date, sum, idChild);
                return true;
            }
            return false;
        } catch (NumberFormatException | IllegalFormatException e){
            return false;
        }
    }

    public boolean addGroup(String name, Department department, String lessonTime) {
        List<ChildGroup> list = mainDaoImpl.getAllChildGroup(department);
        if (name.trim().matches(regExp) && list.stream().noneMatch(s->s.getName().equals(name))) {
            adminDaoImpl.addGroup(name, department, lessonTime);
            return true;
        } return false;
    }

    public List<Payment> getAllPaymentsByChildAndPayment(int idPayment) {
        return adminDaoImpl.getAllPaymentsByChildAndPayment(idPayment);
    }

    public boolean savePayment(int idPayment, LocalDate date, String sum) {
        try{
            if (Integer.parseInt(sum)>0){
                adminDaoImpl.savePayment(idPayment, date, sum);
                return true;
            } return false;
        } catch (NumberFormatException | IllegalFormatException e){
            return false;
        }
    }

    public ChildGroup getChildGroup(int idChildGroup) {
        return adminDaoImpl.getChildGroupById(idChildGroup);
    }

    public boolean saveChildGroup(int idChildGroup, String name, Department department) {
        List<ChildGroup> list = mainDaoImpl.getAllChildGroup(department);
        if (!name.trim().equals("") && name.trim().matches(regExp)
            && list.stream().noneMatch(s->s.getName().equals(name))){
            adminDaoImpl.saveChildGroup(idChildGroup, name);
            return true;
        } return false;
    }

    public void deleteGroup(int idChildGroup) {
        adminDaoImpl.deleteGroup(idChildGroup);
    }

    public List<Child> getAllChildBirthday(Department department) {
        LocalDate date = LocalDate.now();
        int monthNow = date.getMonthValue();
        int dayNow = date.getDayOfMonth();
        List<Child> listFilter = new ArrayList<>();
        List<Child> list = adminDaoImpl.getAllChild(department);
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
