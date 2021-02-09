package com.codewarts.controller;

import com.codewarts.entity.Department;
import com.codewarts.entity.Staff;
import com.codewarts.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDate;

@Controller
public class DirectorController {
    private DirectorService directorService;
    @Autowired
    public void setDirectorService(DirectorService directorService) {
        this.directorService = directorService;
    }

    @ModelAttribute(name = "department")
    public Department getDepartment(HttpSession session){
        return (Department) session.getAttribute("department");
    }

    @GetMapping(value = "/director")
    public String directorPage(Model model, @ModelAttribute("department") Department department){
        model.addAttribute("ListTeacher", directorService.getAllTeachers(department));
        return "director/director";
    }

    @GetMapping(value = "/director/addStaff")
    public String addStaffGet(HttpSession session, Staff staff, Model model){
        session.setAttribute("listRoles", directorService.getAllStaffRoles());
        session.setAttribute("listDepartment", directorService.getAllDepartments());
        return "director/addstaff";
    }

    @PostMapping(value = "/director/addStaff")
    public String addStaffPost(Model model, @ModelAttribute("staff") @Valid Staff staff,
                               BindingResult result, @RequestParam("role") int idRole,
                               @RequestParam("dep") int idDepartment){
        if (result.hasErrors()){
            return "director/addstaff";
        }
        if (directorService.addStaff(staff, idRole, idDepartment)){
            model.addAttribute("msg", "Персонал успешно добавлен");
        } else {
            model.addAttribute("msg", "Пользователь с таким логином уже существует");
        }
        return "director/director";
    }

    @PostMapping(value = "/director/teacherData")
    public String teacherAccounting(@RequestParam("ListTeacher") int idTeacher,
                                    @RequestParam(value = "dateFirst", defaultValue = "")
                                    @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateFrom,
                                    @RequestParam(value = "dateLast")
                                    @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateTo, Model model,
                                    @ModelAttribute("department") Department department){
        double hours = directorService.getAllHoursByTeacher(idTeacher, dateFrom, dateTo);
        int quantity = directorService.getAllQuantityByTeacher(idTeacher, dateFrom, dateTo);
        double sum = hours * 15;
        model.addAttribute("resultmsg", "Отработано занятий: " + quantity + "<br>" +
                "Сумма к оплате равна: " + (int)sum + " рублей");
        model.addAttribute("ListTeacher", directorService.getAllTeachers(department));
        return "director/director";
    }
}
