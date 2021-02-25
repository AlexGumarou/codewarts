package com.codewarts.controller;

import com.codewarts.entity.Staff;
import com.codewarts.service.MainServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class MainController {
    private MainServiceImpl service;

    @Autowired
    public void setService(MainServiceImpl service) {
        this.service = service;
    }

    @GetMapping(value = "/login")
    public String loginPage(){
        return "index";
    }

    @PostMapping(value = "/logout")
    public String logout(){
        return "index";
    }

    @GetMapping(value = "/")
    public String loginPagePost(Model model, Principal principal, HttpSession session){
        if (principal == null){
            return "index";
        }
        Staff staff = service.getStaff(principal.getName());
        session.setAttribute("staff", staff);
        session.setAttribute("name", staff.getName());
        switch (staff.getStaffRole().getRole()) {
            case "ADMIN":
                return "redirect:/admin";
            case "TEACHER":
                return "redirect:/teacher";
            case "DIRECTOR":
                return "redirect:/director";
            default:
                return "error";
        }
    }
}
