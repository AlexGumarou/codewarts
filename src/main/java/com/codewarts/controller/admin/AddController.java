package com.codewarts.controller.admin;

import com.codewarts.entity.Child;
import com.codewarts.entity.ChildGroup;
import com.codewarts.entity.Department;
import com.codewarts.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
public class AddController {
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

    @GetMapping(value = "/admin/addChild")
    public String addChildGet(Model model, @ModelAttribute("listGroups") List<ChildGroup> groupList, Child child){
        model.addAttribute("listGroups", groupList);
        return "admin/add/addChild";
    }

    @PostMapping(value = "/admin/addChild")
    public String addChildPost(Model model,
                               @ModelAttribute("child") @Valid Child child,
                               BindingResult result,
                               @RequestParam(name = "birthdayDate", defaultValue = "")
                               @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                               @RequestParam(value = "mother") String mother,
                               @RequestParam(value = "phoneMother") String phoneMother,
                               @RequestParam(value = "father")String father,
                               @RequestParam(value = "phoneFather") String phoneFather,
                               @RequestParam(value = "selectGroup") int idGroup){
        if (result.hasErrors()){
            return "admin/add/addChild";
        }
        if (adminService.addChild(date, child.getName(), child.getSurname(), mother, father, phoneMother,
                phoneFather, idGroup)){
            model.addAttribute("msg", "Данные успешно добавлены!");
        } else {
            model.addAttribute("msg", "Введены некорректные данные");
        }
        return "admin/add/addChild";
    }

    @GetMapping(value = "/admin/addPayment")
    public String addPaymentGet(Model model, @ModelAttribute("department") Department department){
        model.addAttribute("now", LocalDate.now());
        model.addAttribute("listChild", adminService.getAllChild(department));
        return "admin/add/addPayment";
    }

    @PostMapping(value = "/admin/addPayment")
    public String addPaymentGet(Model model, @RequestParam(name = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                @RequestParam(value = "sum") String sum,
                                @RequestParam(value = "listChild") int idChild,
                                @ModelAttribute("department") Department department){
        if (adminService.addPayment(date, sum, idChild)){
            model.addAttribute("msg", "Оплата успешно внесена");
        } else {
            model.addAttribute("msg", "Неверный формат суммы");
        }
        model.addAttribute("now", LocalDate.now());
        model.addAttribute("listChild", adminService.getAllChild(department));
        return "admin/add/addPayment";
    }

    @GetMapping(value = "/admin/addGroup")
    public String makeNewGroup(){
        return "admin/add/addGroup";
    }

    @PostMapping(value = "/admin/addGroup")
    public String makeNewGroupPost(Model model, @ModelAttribute("department") Department department,
                                   @RequestParam("name")String name, @RequestParam("lessonTime") String lessonTime){
        if (adminService.addGroup(name, department, lessonTime)){
            model.addAttribute("msg", "Группа успешно добавлена");
        } else {
            model.addAttribute("msg", "Имя группы должно содержать только буквы и цифры" + "<br>" +
                    "Либо такая группа уже существует");
        }
        return "admin/add/addGroup";
    }
}
