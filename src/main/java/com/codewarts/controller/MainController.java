package com.codewarts.controller;

import com.codewarts.service.MainServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String loginPagePost(Principal principal){
        if (principal == null){
            return "index";
        }
        if (service.getStaff(principal.getName()).getStaffRole().getRole().equals("ADMIN")){
            return "redirect:/admin";
        } else if (service.getStaff(principal.getName()).getStaffRole().getRole().equals("TEACHER")){
            return "redirect:/teacher";
        } else if (service.getStaff(principal.getName()).getStaffRole().getRole().equals("DIRECTOR")) {
            return "redirect:/director";
        } else {
            return "error";
        }
    }
}
