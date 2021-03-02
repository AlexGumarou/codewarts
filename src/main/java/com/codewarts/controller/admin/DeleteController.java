package com.codewarts.controller.admin;

import com.codewarts.entity.Child;
import com.codewarts.entity.ChildGroup;
import com.codewarts.entity.Department;
import com.codewarts.service.AdminServiceImpl;
import com.codewarts.service.MainServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class DeleteController {
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

    @ModelAttribute(name = "listGroups")
    public List<ChildGroup> getListGroups(@ModelAttribute("department") Department department){
        return mainServiceImpl.getAllGroupChild(department);
    }

    @PostMapping("/delete")
    public String deleteChild(@RequestParam(value = "idChild") int idChild){
        adminServiceImpl.deleteChild(idChild);
        return "redirect:/admin";
    }

    @PostMapping("/delete/group")
    public String deleteGroup(@RequestParam("button") int idChildGroup,
                              @ModelAttribute("department") Department department){
        List<Child> list = mainServiceImpl.getAllChildByGroupAndDepartment(department,idChildGroup);
        if (list.size() == 0) {
            adminServiceImpl.deleteGroup(idChildGroup);
        } else {
            return "redirect:/admin";
        }
        return "redirect:/admin";
    }
}
