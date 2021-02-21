package com.codewarts.controller.admin;

import com.codewarts.entity.Child;
import com.codewarts.entity.ChildGroup;
import com.codewarts.entity.Department;
import com.codewarts.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class DeleteController {
    private AdminService adminService;

    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    @ModelAttribute(name = "department")
    public Department getDepartment(HttpSession session){
        return (Department) session.getAttribute("department");
    }

    @ModelAttribute(name = "listGroups")
    public List<ChildGroup> getListGroups(@ModelAttribute("department") Department department){
        return adminService.getAllGroupChild(department);
    }

    @PostMapping(value = "/admin/delete")
    public String deleteChild(@RequestParam(value = "idChild") int idChild){
        adminService.deleteChild(idChild);
        return "redirect:/admin";
    }

    @PostMapping(value = "/admin/delete/group")
    public String deleteGroup(@RequestParam("button") int idChildGroup,
                              @ModelAttribute("department") Department department){
        List<Child> list = adminService.getAllChildByGroupAndDepartment(department,idChildGroup);
        if (list.size() == 0) {
            adminService.deleteGroup(idChildGroup);
        } else {
            return "redirect:/admin";
        }
        return "redirect:/admin";
    }
}
