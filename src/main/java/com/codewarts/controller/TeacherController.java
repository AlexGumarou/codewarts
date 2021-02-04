package com.codewarts.controller;

import com.codewarts.entity.Department;
import com.codewarts.entity.Staff;
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

public class TeacherController {
    private TeacherService teacherService;
    @Autowired
    public void setService(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/teacher")
    public String teacherPage(Model model, HttpSession session){
        Department department = (Department) session.getAttribute("department");
        model.addAttribute("listGroups", teacherService.getAllGroupChild(department));
        model.addAttribute("msg", "Список групп");
        return "teacher/teacher";
    }

    @PostMapping("/teacher")
    public String teacherPagePost(Model model, HttpSession session, @RequestParam("themeName") String theme,
                                  @RequestParam(value = "child", defaultValue = "") String[] child){
        Integer group_id = (Integer) session.getAttribute("childGroup");
        if (teacherService.checkDoubleClick(group_id)) {
            model.addAttribute("msg", "НЕЛЬЗЯ дважды отмечать одну и ту же группу" + "<br>" +
                    "или не отмечен ни один ребенок!");
        } else {
            Staff staff = (Staff) session.getAttribute("staff");
            if (child.length != 0) {
                teacherService.saveChildAttendance(child);
                teacherService.saveTeacherAttendance(staff, group_id, theme);
                model.addAttribute("msg", "Данные о посещении учеников внесены");
            }
        }
        return "teacher/attendance";
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
