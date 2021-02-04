package com.codewarts.controller;

import com.codewarts.entity.Child;
import com.codewarts.entity.Department;
import com.codewarts.service.AdminService;
import com.codewarts.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDate;

@Controller
public class AdminController {
    private AdminService adminService;
    private TeacherService teacherService;

    @Autowired
    public void setAdminService(AdminService adminService, TeacherService teacherService) {
        this.adminService = adminService;
        this.teacherService = teacherService;
    }

    @GetMapping(value = "/admin")
    public String adminPage(Model model, HttpSession session){
        Department department = (Department) session.getAttribute("department");
        model.addAttribute("listGroups", teacherService.getAllGroupChild(department));
        return "admin/admin";
    }

    @GetMapping("/admin/{childGroup}")
    public String getChildByGroup(@PathVariable(name = "childGroup") int childGroup, Model model, HttpSession session){
        Department department = (Department) session.getAttribute("department");
        session.setAttribute("childGroup", childGroup);
        model.addAttribute("listChild", teacherService.getAllChildByGroupAndDepartment(department,childGroup));
        return "admin/childGroup";
    }

    @GetMapping("/admin/child/{editChild}")
    public String getChildAndEdit(@PathVariable(name = "editChild") int editChild, Model model, HttpSession session){
        Department department = (Department) session.getAttribute("department");
        int childGroup = (int) session.getAttribute("childGroup");
        model.addAttribute("listChild", teacherService.getAllChildByGroupAndDepartment(department,childGroup));
        model.addAttribute("listGroups", teacherService.getAllGroupChild(department));
        model.addAttribute("child", adminService.getChildById(editChild));
        model.addAttribute("listDepartments", adminService.getAllDepartments());
        return "admin/childEdit";
    }

    @PostMapping(value = "/admin/child/{editChild}")
    public String adminEditAndSave(@PathVariable(name = "editChild") int idChild,
                                   @ModelAttribute("child") @Valid Child child,
                                   BindingResult result,
                                   @RequestParam(name = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                   @RequestParam(value = "name") String name,
                                   @RequestParam(value = "surname") String surname,
                                   @RequestParam(value = "mother") String mother,
                                   @RequestParam(value = "phoneMother") String phoneMother,
                                   @RequestParam(value = "father")String father,
                                   @RequestParam(value = "phoneFather") String phoneFather,
                                   @RequestParam(value = "selectGroup") int idGroup,
                                   Model model, HttpSession session
                                   ){
        if (result.hasErrors()){
            return "redirect:/admin/child/"+idChild;
        }
        Department department = (Department) session.getAttribute("department");
        model.addAttribute("listGroups", teacherService.getAllGroupChild(department));
        if (adminService.saveChild(idChild, date, name, surname, idGroup, mother, phoneMother, father, phoneFather)){
            model.addAttribute("msg", "Данные успешно сохранены");
        } else {
            model.addAttribute("msg", "Даты не соответствуют допустимому интервалу");
        }
        return "admin/admin";
    }

    @PostMapping(value = "/delete")
    public String deleteChild(@RequestParam(value = "idChild") int idChild){
        adminService.deleteChild(idChild);
        return "redirect:/admin";
    }
}
