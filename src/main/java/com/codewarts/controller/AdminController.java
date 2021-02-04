package com.codewarts.controller;

import com.codewarts.entity.Department;
import com.codewarts.service.AdminService;
import com.codewarts.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

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
    public String adminEditAndSave(@PathVariable(name = "editChild") int idChild, Model model,
                                   @RequestParam(value = "name") String name,
                                   @RequestParam(value = "surname") String surname,
                                   @RequestParam(value = "mother") String mother,
                                   @RequestParam(value = "phoneMother") String phoneMother,
                                   @RequestParam(value = "father")String father,
                                   @RequestParam(value = "phoneFather") String phoneFather,
                                   @RequestParam(value = "selectGroup") int idGroup,
                                   @RequestParam(value = "selectDepartment") int idDepartment
                                   ){
        adminService.saveChild(idChild,name,surname,idGroup, mother, phoneMother, father, phoneFather, idDepartment);
        return "admin/admin";
    }
}
