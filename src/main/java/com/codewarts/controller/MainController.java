package com.codewarts.controller;

import com.codewarts.entity.Department;
import com.codewarts.entity.Staff;
import com.codewarts.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {
    private MainService service;

    @Autowired
    public void setService(MainService service) {
        this.service = service;
    }

    @GetMapping(value = "/login")
    public String loginPage(){
        return "index";
    }

    @PostMapping(value = "/login")
    public String loginPagePost(Model model, @RequestParam("login") String login, @RequestParam("pass") String pass,
                                HttpSession session){
        String msg = "Неверный логин или пароль";
        if (service.checkLoginPass(login,pass)){
            Staff staff = service.getStaff(login, pass);
            session.setAttribute("staff", staff);
            session.setAttribute("name", staff.getName());
            session.setAttribute("department", staff.getDepartment());
            switch (staff.getStaffRole().getRole()) {
                case "ADMIN":
                    return "redirect:/admin";
                case "TEACHER":
                    return "redirect:/teacher";
                case "DIRECTOR":
                    return "redirect:/director";
                default:
                    model.addAttribute("msg", msg);
                    return "index";
            }
        } else model.addAttribute("msg", msg);
        return "index";
    }
}
