package com.codewarts.controller.admin;

import com.codewarts.entity.Child;
import com.codewarts.entity.Department;
import com.codewarts.service.AdminServiceImpl;
import com.codewarts.service.MainServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
public class AdminController {
    private AdminServiceImpl adminServiceImpl;
    private MainServiceImpl mainServiceImpl;

    @Autowired
    public void setAdminService(AdminServiceImpl adminServiceImpl) {
        this.adminServiceImpl = adminServiceImpl;
    }

    @Autowired
    public void setMainService(MainServiceImpl mainServiceImpl) {
        this.mainServiceImpl = mainServiceImpl;
    }

    @ModelAttribute(name = "department")
    public Department getDepartment(Principal principal){
        return mainServiceImpl.getStaff(principal.getName()).getDepartment();
    }

    @GetMapping(value = "/admin")
    public String adminPage(Model model, @ModelAttribute("department") Department department){
        model.addAttribute("listBirthday", adminServiceImpl.getAllChildBirthday(department));
        model.addAttribute("listGroups", mainServiceImpl.getAllGroupChild(department));
        return "admin/admin";
    }

    @RequestMapping("/admin/{childGroup}")
    public String getChildByGroup(@PathVariable(name = "childGroup") int childGroup, Model model, HttpSession session){
        Department department = (Department) session.getAttribute("department");
        List<Child> list = mainServiceImpl.getAllChildByGroupAndDepartment(department,childGroup);
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
        List<Child> list = adminServiceImpl.findChildBySurname(findChild, department);
        if (list == null || list.isEmpty()){
            model.addAttribute("msg", "Не найдено ни одного ребенка с такой фамилией");
        } else {
            model.addAttribute("msg", "Список найденных детей:");
            model.addAttribute("listFind", list);
        }
        return "admin/find";
    }
}
