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
        switch (service.getStaff(principal.getName()).getStaffRole().getRole()) {
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
