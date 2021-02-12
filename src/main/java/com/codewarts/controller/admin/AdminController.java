package com.codewarts.controller.admin;

import com.codewarts.entity.Child;
import com.codewarts.entity.Department;
import com.codewarts.entity.Staff;
import com.codewarts.service.AdminService;
import com.codewarts.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
//@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    private AdminService adminService;
    private TeacherService teacherService;

    @Autowired
    public void setAdminService(AdminService adminService, TeacherService teacherService) {
        this.adminService = adminService;
        this.teacherService = teacherService;
    }

    @GetMapping(value = "/admin")
    public String adminPage(Model model, HttpSession session, Principal principal){
        String name = principal.getName();
        Staff staff = adminService.getStaffByName(name);
        session.setAttribute("staff", staff);
        session.setAttribute("name", staff.getName());
        session.setAttribute("department", staff.getDepartment());
        model.addAttribute("listGroups",
                adminService.getAllGroupChild((Department) session.getAttribute("department")));
        return "admin/admin";
    }

    @GetMapping("/admin/{childGroup}")
    public String getChildByGroup(@PathVariable(name = "childGroup") int childGroup, Model model, HttpSession session){
        Department department = (Department) session.getAttribute("department");
        session.setAttribute("childGroup", childGroup);
        List<Child> list = teacherService.getAllChildByGroupAndDepartment(department,childGroup);
        if (list.isEmpty()){
            model.addAttribute("msg", "В данной группе нет ни одного ученика");
        }
        model.addAttribute("listChild", list);
        model.addAttribute("idChildGroup", childGroup);
        return "admin/childGroup";
    }

    @PostMapping(value = "/admin/search")
    public String searchChildBySurname(@RequestParam(value = "findChild") String findChild, Model model,
                                       HttpSession session){
        List<Child> list =
                adminService.findChildBySurname(findChild, (Department) session.getAttribute("department"));
        if (list.isEmpty()){
            model.addAttribute("msg", "Не найдено ни одного ребенка с такой фамилией");
        } else {
            model.addAttribute("msg", "Список найденных детей:");
            model.addAttribute("listFind", list);
        }
        return "admin/find";
    }
}
