package com.codewarts.controller;

import com.codewarts.entity.Department;
import com.codewarts.entity.Staff;
import com.codewarts.service.DirectorServiceImpl;
import com.codewarts.service.MailSender;
import com.codewarts.service.MainServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
@RequestMapping("/director")
public class DirectorController {
    private DirectorServiceImpl directorServiceImpl;
    private MainServiceImpl mainServiceImpl;
    private MailSender mailSender;

    @Value("${spring.mail.username}")
    private String username;

    @Autowired
    public void setDirectorService(DirectorServiceImpl directorServiceImpl) {
        this.directorServiceImpl = directorServiceImpl;
    }

    @Autowired
    public void setMainService(MainServiceImpl mainServiceImpl) {
        this.mainServiceImpl = mainServiceImpl;
    }

    @Autowired
    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @ModelAttribute(name = "department")
    public Department setDepartment(Principal principal){
        return mainServiceImpl.getStaff(principal.getName()).getDepartment();
    }

    @ModelAttribute
    public void setName(Principal principal, HttpSession session){
        Staff staff = mainServiceImpl.getStaff(principal.getName());
        session.setAttribute("name", staff.getName());
    }

    @GetMapping
    public String directorPage(Model model, @ModelAttribute("department") Department department){
        model.addAttribute("ListTeacher", directorServiceImpl.getAllTeachers(department));
        return "director/director";
    }

    @GetMapping("/addStaff")
    public String addStaffGet(HttpSession session, Staff staff){
        session.setAttribute("listRoles", directorServiceImpl.getAllStaffRoles());
        session.setAttribute("listDepartment", directorServiceImpl.getAllDepartments());
        return "director/addStaf";
    }

    @PostMapping("/addStaff")
    public String addStaffPost(Model model, @ModelAttribute("staff") @Valid Staff staff,
                               BindingResult result, @RequestParam("role") int idRole,
                               @RequestParam("dep") int idDepartment){
        if (result.hasErrors()){
            return "director/addStaf";
        }
        if (directorServiceImpl.addStaff(staff, idRole, idDepartment)){
            mailSender.send(username, "New registration",
                    mailSender.messageCreateStaff(staff));
            model.addAttribute("msg", "Персонал успешно добавлен");
        } else {
            model.addAttribute("msg", "Пользователь с таким логином уже существует");
        }
        return "director/addStaf";
    }

    @GetMapping("/removeStaff")
    public String removeStaffGet(Model model){
        model.addAttribute("listStaff", directorServiceImpl.getAllStaff());
        return "director/removeStaff";
    }

    @PostMapping("/removeStaff")
    public String removeStaffPost(@RequestParam("listStaff") int idStaff, Model model){
        directorServiceImpl.deleteStaff(idStaff);
        model.addAttribute("listStaff", directorServiceImpl.getAllStaff());
        model.addAttribute("msg", "Данные удалены");
        return "director/removeStaff";
    }

    @GetMapping("/teacherData")
    public String teacherAccountingGet(Model model, @ModelAttribute("department") Department department){
        model.addAttribute("ListTeacher", directorServiceImpl.getAllTeachers(department));
        return "director/teacherData";
    }

    @PostMapping("/teacherData")
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
            double hours = directorServiceImpl.getAllHoursByTeacher(idTeacher, dateFrom, dateTo);
            int quantity = directorServiceImpl.getAllQuantityByTeacher(idTeacher, dateFrom, dateTo);
            int price = directorServiceImpl.getPricePerHourByTeacher(idTeacher);
            double sum = hours * price;
            model.addAttribute("msg", "За период с " + dateFrom.getDayOfMonth() + "." + dateFrom.getMonthValue() +
                    "." + dateFrom.getYear() + " и по " + dateTo.getDayOfMonth() + "." + dateTo.getMonthValue() +
                    "." + dateTo.getYear() + " отработано занятий: " + quantity + "<br>" + "Сумма к оплате: " +
                    (int) sum + " рублей");
        }
        model.addAttribute("ListTeacher", directorServiceImpl.getAllTeachers(department));
        return "director/teacherData";
    }

    @GetMapping("/payments")
    public String paymentsGet(){
        return "director/payments";
    }

    @PostMapping("/payments")
    public String payments(@RequestParam(value = "dateFirst", defaultValue = "")
                           @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateFrom,
                           @RequestParam(value = "dateLast", defaultValue = "")
                           @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateTo, Model model,
                           @ModelAttribute("department") Department department){
        if (dateFrom == null || dateTo == null) {
            model.addAttribute("msg", "Введены не все данные");
        } else if (dateFrom.isAfter(dateTo)){
            model.addAttribute("msg", "Сначала должна быть более ранняя дата");
        } else {
            model.addAttribute("msg", "За период с " + dateFrom.getDayOfMonth() + "." + dateFrom.getMonthValue() +
                    "." + dateFrom.getYear() + " и по " + dateTo.getDayOfMonth() + "." +dateTo.getMonthValue() + "." +
                    dateTo.getYear() + " платежей на сумму: " + directorServiceImpl.getAllPayments(dateFrom, dateTo) +
                    " рублей");
        }
        model.addAttribute("ListTeacher", directorServiceImpl.getAllTeachers(department));
        return "director/payments";
    }

    @GetMapping("/teacherPrice")
    public String teacherPriceNewValueGet(Model model, @ModelAttribute("department") Department department){
        model.addAttribute("ListTeacher", directorServiceImpl.getAllTeachers(department));
        return "director/teacherPrice";
    }

    @PostMapping("/teacherPrice")
    public String teacherPriceNewValue(@RequestParam("ListTeacher") int idTeacher,
                                       @RequestParam("sum") String pricePerHour,
                                       @ModelAttribute("department") Department department, Model model){
        if (directorServiceImpl.changePricePerHour(idTeacher,pricePerHour)){
            model.addAttribute("msg", "Данные сохранены");
        } else {
            model.addAttribute("msg", "Неверный формат суммы!");
        }
        model.addAttribute("ListTeacher", directorServiceImpl.getAllTeachers(department));
        return "director/teacherPrice";
    }
}
