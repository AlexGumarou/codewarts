package com.codewarts.controller;

import com.codewarts.entity.Staff;
import com.codewarts.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;

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

    @GetMapping(value = "/error")
    public String errorLoginPage(){
        return "error";
    }

    @PostMapping(value = "/logout")
    public String logout(){
        return "index";
    }


    @GetMapping(value = "/")
    public String loginPagePost(Model model, Principal principal,
                                HttpSession session){
        if (principal == null){
            return "index";
        }
        String msg = "Неверный логин или пароль";
        String login = principal.getName();
        Staff staff = service.getStaff(login);
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
                return "error";
        }
    }
}
