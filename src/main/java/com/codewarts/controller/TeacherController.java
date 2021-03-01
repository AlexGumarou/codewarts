package com.codewarts.controller;

import com.codewarts.entity.Department;
import com.codewarts.entity.Staff;
import com.codewarts.service.MainServiceImpl;
import com.codewarts.service.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class TeacherController {
    private TeacherServiceImpl teacherServiceImpl;
    private MainServiceImpl mainServiceImpl;

    @Autowired
    public void setTeacherService(TeacherServiceImpl teacherServiceImpl) {
        this.teacherServiceImpl = teacherServiceImpl;
    }

    @Autowired
    public void setMainService(MainServiceImpl mainServiceImpl) {
        this.mainServiceImpl = mainServiceImpl;
    }

    @ModelAttribute(name = "department")
    public Department setDepartment(Principal principal, HttpSession session){
        Department department = mainServiceImpl.getStaff(principal.getName()).getDepartment();
        session.setAttribute("department", department);
        return department;
    }

    @ModelAttribute
    public void setNameAndStaff(Principal principal, HttpSession session){
        Staff staff = mainServiceImpl.getStaff(principal.getName());
        session.setAttribute("staff", staff);
        session.setAttribute("name", staff.getName());
    }

    @GetMapping("/teacher")
    public String teacherPage(Model model, @ModelAttribute("department") Department department, HttpSession session){
        model.addAttribute("listGroups", mainServiceImpl.getAllGroupChild(department));
        return "teacher/teacher";
    }

    @PostMapping("/teacher")
    public String teacherPagePost(Model model, HttpSession session, @RequestParam("themeName") String theme,
                                  @RequestParam(value = "child", defaultValue = "") String[] child){
        Department department = (Department) session.getAttribute("department");
        Integer group_id = (Integer) session.getAttribute("childGroup");
        if (teacherServiceImpl.checkDoubleClick(group_id)) {
            model.addAttribute("msg", "НЕЛЬЗЯ дважды отмечать одну и ту же группу");
        } else {
            Staff staff = (Staff) session.getAttribute("staff");
            if (child.length != 0) {
                teacherServiceImpl.saveChildAttendance(child);
                teacherServiceImpl.saveTeacherAttendance(staff, group_id, theme);
                model.addAttribute("msg", "Данные о посещении учеников внесены");
            } else {
                model.addAttribute("msg", "Не отмечен ни один ребенок!");
            }
        }
        model.addAttribute("listGroups", mainServiceImpl.getAllGroupChild(department));
        return "teacher/teacher";
    }

    @RequestMapping("/teacher/{childGroup}")
    public String getChildByGroup(@PathVariable(name = "childGroup") int childGroup, Model model, HttpSession session){
        Department department = (Department) session.getAttribute("department");
        session.setAttribute("childGroup", childGroup);
        model.addAttribute("listChild", mainServiceImpl.getAllChildByGroupAndDepartment(department,childGroup));
        model.addAttribute("listTheme", teacherServiceImpl.getAllTheme());
        model.addAttribute("listThemePast", teacherServiceImpl.getAllThemePast(childGroup));
        return "teacher/childGroup";
    }
}
