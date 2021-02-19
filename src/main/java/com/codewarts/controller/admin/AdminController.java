package com.codewarts.controller.admin;

import com.codewarts.entity.Child;
import com.codewarts.entity.Department;
import com.codewarts.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AdminController {
    private AdminService adminService;

    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    @ModelAttribute(name = "department")
    public Department getDepartment(HttpSession session){
        return (Department) session.getAttribute("department");
    }

    @GetMapping(value = "/admin")
    public String adminPage(Model model, @ModelAttribute("department") Department department){
        model.addAttribute("listBirthday", adminService.getAllChildBirthday(department));
        model.addAttribute("listGroups", adminService.getAllGroupChild(department));
        return "admin/admin";
    }

    @RequestMapping("/admin/{childGroup}")
    public String getChildByGroup(@PathVariable(name = "childGroup") int childGroup, Model model, HttpSession session){
        Department department = (Department) session.getAttribute("department");
        session.setAttribute("childGroup", childGroup);
        List<Child> list = adminService.getAllChildByGroupAndDepartment(department,childGroup);
        if (list.isEmpty()){
            model.addAttribute("msg", "В данной группе нет ни одного ученика");
        } else {
            model.addAttribute("listChild", list);
        }
        model.addAttribute("idChildGroup", childGroup);
        return "admin/childGroup";
    }

    @PostMapping(value = "/admin/search")
    public String searchChildBySurname(@RequestParam(value = "findChild", defaultValue = "") String findChild,
                                       Model model, @ModelAttribute("department") Department department){
        List<Child> list = adminService.findChildBySurname(findChild, department);
        if (list == null || list.isEmpty()){
            model.addAttribute("msg", "Не найдено ни одного ребенка с такой фамилией");
        } else {
            model.addAttribute("msg", "Список найденных детей:");
            model.addAttribute("listFind", list);
        }
        return "admin/find";
    }
}
