package com.codewarts.controller;

import com.codewarts.entity.Department;
import com.codewarts.entity.Staff;
import com.codewarts.service.DirectorService;
import com.codewarts.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDate;

@Controller
public class DirectorController {
    private DirectorService directorService;
    @Autowired
    public void setDirectorService(DirectorService directorService) {
        this.directorService = directorService;
    }

    @ModelAttribute(name = "department")
    public Department getDepartment(Principal principal){
        return directorService.getStaff(principal.getName()).getDepartment();
    }

    @GetMapping(value = "/director")
    public String directorPage(Model model, @ModelAttribute("department") Department department){
        model.addAttribute("ListTeacher", directorService.getAllTeachers(department));
        return "director/director";
    }

    @GetMapping(value = "/director/addStaff")
    public String addStaffGet(HttpSession session, Staff staff){
        session.setAttribute("listRoles", directorService.getAllStaffRoles());
        session.setAttribute("listDepartment", directorService.getAllDepartments());
        return "director/addStaf";
    }

    @PostMapping(value = "/director/addStaff")
    public String addStaffPost(Model model, @ModelAttribute("staff") @Valid Staff staff,
                               BindingResult result, @RequestParam("role") int idRole,
                               @RequestParam("dep") int idDepartment){
        if (result.hasErrors()){
            return "director/addStaf";
        }
        if (directorService.addStaff(staff, idRole, idDepartment)){
            model.addAttribute("msg", "Персонал успешно добавлен");
        } else {
            model.addAttribute("msg", "Пользователь с таким логином уже существует");
        }
        return "director/addStaf";
    }

    @GetMapping(value = "/director/teacherData")
    public String teacherAccountingGet(Model model, @ModelAttribute("department") Department department){
        model.addAttribute("ListTeacher", directorService.getAllTeachers(department));
        return "director/teacherData";
    }

    @PostMapping(value = "/director/teacherData")
    public String teacherAccounting(@RequestParam("ListTeacher") int idTeacher,
                                    @RequestParam(value = "dateFirst", defaultValue = "")
                                    @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateFrom,
                                    @RequestParam(value = "dateLast", defaultValue = "")
                                    @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateTo, Model model,
                                    @ModelAttribute("department") Department department){
        if (dateFrom==null || dateTo == null){
            model.addAttribute("msg", "Введены не все данные");
        } else if (dateFrom.isAfter(dateTo)){
            model.addAttribute("msg", "Сначала должна быть более ранняя дата");
        } else {
            double hours = directorService.getAllHoursByTeacher(idTeacher, dateFrom, dateTo);
            int quantity = directorService.getAllQuantityByTeacher(idTeacher, dateFrom, dateTo);
            int price = directorService.getPricePerHourByTeacher(idTeacher);
            double sum = hours * price;
            model.addAttribute("msg", "За период с " + dateFrom.getDayOfMonth() + "." + dateFrom.getMonthValue() +
                    "." + dateFrom.getYear() + " и по " + dateTo.getDayOfMonth() + "." + dateTo.getMonthValue() +
                    "." + dateTo.getYear() + " отработано занятий: " + quantity + "<br>" + "Сумма к оплате: " +
                    (int) sum + " рублей");
        }
        model.addAttribute("ListTeacher", directorService.getAllTeachers(department));
        return "director/teacherData";
    }

    @GetMapping(value = "/director/payments")
    public String paymentsGet(){
        return "director/payments";
    }

    @PostMapping(value = "/director/payments")
    public String payments(@RequestParam(value = "dateFirst", defaultValue = "")
                           @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateFrom,
                           @RequestParam(value = "dateLast", defaultValue = "")
                           @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateTo, Model model,
                           @ModelAttribute("department") Department department){
        if (dateFrom==null || dateTo == null) {
            model.addAttribute("msg", "Введены не все данные");
        } else if (dateFrom.isAfter(dateTo)){
            model.addAttribute("msg", "Сначала должна быть более ранняя дата");
        } else {
            model.addAttribute("msg", "За период с " + dateFrom.getDayOfMonth() + "." + dateFrom.getMonthValue() +
                    "." + dateFrom.getYear() + " и по " + dateTo.getDayOfMonth() + "." +dateTo.getMonthValue() + "." +
                    dateTo.getYear() + " получено платежей: " + directorService.getAllPayments(dateFrom, dateTo));
        }
        model.addAttribute("ListTeacher", directorService.getAllTeachers(department));
        return "director/payments";
    }

    @GetMapping(value = "/director/teacherPrice")
    public String teacherPriceNewValueGet(Model model, @ModelAttribute("department") Department department){
        model.addAttribute("ListTeacher", directorService.getAllTeachers(department));
        return "director/teacherPrice";
    }

    @PostMapping(value = "/director/teacherPrice")
    public String teacherPriceNewValue(@RequestParam("ListTeacher") int idTeacher,
                                       @RequestParam("sum") String pricePerHour,
                                       @ModelAttribute("department") Department department, Model model){
        if (directorService.changePricePerHour(idTeacher,pricePerHour)){
            model.addAttribute("msg", "Данные сохранены");
        } else {
            model.addAttribute("msg", "Неверный формат суммы!");
        }
        model.addAttribute("ListTeacher", directorService.getAllTeachers(department));
        return "director/teacherPrice";
    }
}
