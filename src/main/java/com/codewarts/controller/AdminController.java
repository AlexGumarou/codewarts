package com.codewarts.controller;

import com.codewarts.entity.Attendance;
import com.codewarts.entity.Child;
import com.codewarts.entity.Department;
import com.codewarts.service.AdminService;
import com.codewarts.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

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
        List<Child> list = teacherService.getAllChildByGroupAndDepartment(department,childGroup);
        if (list.isEmpty()){
            model.addAttribute("msg", "В данной группе нет ни одного ученика");
        }
        model.addAttribute("listChild", list);
        return "admin/childGroup";
    }

    @GetMapping("/admin/child/{editChild}")
    public String getChildAndEdit(@PathVariable(name = "editChild") int idChild, Model model, HttpSession session){
        Department department = (Department) session.getAttribute("department");
        model.addAttribute("listGroups", teacherService.getAllGroupChild(department));
        model.addAttribute("child", adminService.getChildById(idChild));
        model.addAttribute("listAttendance", adminService.getAllAttendanceByChild(idChild));
        model.addAttribute("listPayments", adminService.getAllPaymentsByChild(idChild));
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
                                   Model model, HttpSession session){
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

    @PostMapping(value = "/admin/delete")
    public String deleteChild(@RequestParam(value = "idChild") int idChild, HttpSession session, Model model){
        Department department = (Department) session.getAttribute("department");
        model.addAttribute("listGroups", teacherService.getAllGroupChild(department));
        adminService.deleteChild(idChild);
        model.addAttribute("msg", "Данные успешно удалены!");
        return "admin/admin";
    }

    @PostMapping(value = "/admin/search")
    public String searchChildBySurname(@RequestParam(value = "findChild") String findChild, Model model,
                                       HttpSession session){
        Department department = (Department) session.getAttribute("department");
        List<Child> list = adminService.findChildBySurname(findChild, department);
        if (list.isEmpty()){
            model.addAttribute("msg", "Не найдено ни одного ребенка с такой фамилией");
        } else {
            model.addAttribute("msg", "Список найденных детей:");
            model.addAttribute("listFind", list);
        }
        return "admin/find";
    }

    @GetMapping(value = "/admin/addChild")
    public String addChildGet(HttpSession session, Model model){
        Department department = (Department) session.getAttribute("department");
        model.addAttribute("listGroups", teacherService.getAllGroupChild(department));
        return "admin/addChild";
    }

    @PostMapping(value = "/admin/addChild")
    public String addChildPost(HttpSession session, Model model,
                               @ModelAttribute("child") @Valid Child child,
                               BindingResult result,
                               @RequestParam(name = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                               @RequestParam(value = "name") String name,
                               @RequestParam(value = "surname") String surname,
                               @RequestParam(value = "mother") String mother,
                               @RequestParam(value = "phoneMother") String phoneMother,
                               @RequestParam(value = "father")String father,
                               @RequestParam(value = "phoneFather") String phoneFather,
                               @RequestParam(value = "selectGroup") int idGroup){
        if (result.hasErrors()){
            model.addAttribute("msg", "Имя или фамилия ребенка не заполнены");
            return "admin/admin";
        }
        if (adminService.addChild(date, name, surname, mother, father, phoneMother, phoneFather, idGroup)){
            Department department = (Department) session.getAttribute("department");
            model.addAttribute("listGroups", teacherService.getAllGroupChild(department));
            model.addAttribute("msg", "Данные успешно добавлены!");
        } else {
            model.addAttribute("msg", "Поле дата не заполнена");
        }
        return "admin/admin";
    }
}
