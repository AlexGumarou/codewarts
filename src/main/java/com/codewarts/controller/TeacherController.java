package com.codewarts.controller;

import com.codewarts.entity.Department;
import com.codewarts.entity.Staff;
import com.codewarts.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@PreAuthorize("hasRole('TEACHER')")
public class TeacherController {
    private TeacherService teacherService;
    @Autowired
    public void setService(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @ModelAttribute(name = "department")
    public Department getDepartment(HttpSession session){
        return (Department) session.getAttribute("department");
    }

    @GetMapping("/teacher")
    public String teacherPage(Model model, @ModelAttribute("department") Department department){
        model.addAttribute("listGroups", teacherService.getAllGroupChild(department));
        return "teacher/teacher";
    }

    @PostMapping("/teacher")
    public String teacherPagePost(Model model, HttpSession session, @RequestParam("themeName") String theme,
                                  @RequestParam(value = "child", defaultValue = "") String[] child,
                                  @ModelAttribute("department") Department department){
        Integer group_id = (Integer) session.getAttribute("childGroup");
        if (teacherService.checkDoubleClick(group_id)) {
            model.addAttribute("msg", "НЕЛЬЗЯ дважды отмечать одну и ту же группу");
        } else {
            Staff staff = (Staff) session.getAttribute("staff");
            if (child.length != 0) {
                teacherService.saveChildAttendance(child);
                teacherService.saveTeacherAttendance(staff, group_id, theme);
                model.addAttribute("msg", "Данные о посещении учеников внесены");
            } else {
                model.addAttribute("msg", "Не отмечен ни один ребенок!");
            }
        }
        model.addAttribute("listGroups", teacherService.getAllGroupChild(department));
        return "teacher/teacher";
    }

    @GetMapping("/teacher/{childGroup}")
    public String getChildByGroup(@PathVariable(name = "childGroup") int childGroup, Model model, HttpSession session){
        Department department = (Department) session.getAttribute("department");
        session.setAttribute("childGroup", childGroup);
        model.addAttribute("listChild", teacherService.getAllChildByGroupAndDepartment(department,childGroup));
        model.addAttribute("listTheme", teacherService.getAllTheme());
        model.addAttribute("listThemePast", teacherService.getAllThemePast(childGroup));
        return "teacher/childGroup";
    }
}
