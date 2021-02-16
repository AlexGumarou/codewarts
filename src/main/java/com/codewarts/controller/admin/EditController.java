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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
public class EditController {
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

    @GetMapping("/admin/child/{editChild}")
    public String getChildAndEdit(@PathVariable(name = "editChild") int idChild, Model model,
                                  @ModelAttribute("listGroups") List<ChildGroup> groupList){
        model.addAttribute("listGroups", groupList);
        model.addAttribute("child", adminService.getChildById(idChild));
        model.addAttribute("listAttendance", adminService.getAllAttendanceByChild(idChild));
        model.addAttribute("listPayments", adminService.getAllPaymentsByChild(idChild));
        return "admin/edit/childEdit";
    }

    @PostMapping(value = "/admin/child/{editChild}")
    public String adminEditAndSave(@PathVariable(name = "editChild") int idChild,
                                   @ModelAttribute("child") @Valid Child child,
                                   BindingResult result, Model model,
                                   @RequestParam(name = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                   @RequestParam(value = "mother") String mother,
                                   @RequestParam(value = "phoneMother") String phoneMother,
                                   @RequestParam(value = "father")String father,
                                   @RequestParam(value = "phoneFather") String phoneFather,
                                   @RequestParam(value = "selectGroup") int idGroup,
                                   @ModelAttribute("listGroups") List<ChildGroup> groupList){
        if (result.hasErrors()){
            return "redirect:/admin/child/"+idChild;
        }
        model.addAttribute("listGroups", groupList);
        if (adminService.saveChild(idChild, date, child.getName(), child.getSurname(), idGroup, mother, phoneMother,
                father, phoneFather)){
            model.addAttribute("msg", "Данные успешно сохранены");
        } else {
            model.addAttribute("msg", "Даты не соответствуют допустимому интервалу");
        }
        return "admin/admin";
    }

    @PostMapping(value = "/admin/edit/editPayment")
    public String editPaymentPage(Model model, @RequestParam(value = "idPayment", defaultValue = "0") int idPayment){
        if (idPayment != 0) {
            model.addAttribute("paymentToEdit", adminService.getAllPaymentsByChildAndPayment(idPayment));
            return "admin/edit/editPayment";
        } else {
            model.addAttribute("msg", "У данного ребенка нет оплат для редактирования");
            return "admin/admin";
        }
    }

    @PostMapping(value = "/admin/edit/editPaymentSave")
    public String editPaymentPageSave(Model model, @RequestParam("idPayment") int idPayment,
                                      @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                      @RequestParam("sum") String sum){
        if (adminService.savePayment(idPayment,date,sum)){
            model.addAttribute("msg", "Данные успешно сохранены");
        } else {
            model.addAttribute("msg", "Неверный формат вводных данных");
        }
        return "admin/admin";
    }

    @PostMapping(value = "/admin/edit/editGroups")
    public String editGroups(@RequestParam("button") int idChildGroup, Model model){
        model.addAttribute("childGroup", adminService.getChildGroup(idChildGroup));
        return "admin/edit/editGroup";
    }

    @PostMapping(value = "/admin/edit/editGroupSave")
    public String editGroupsSave(@ModelAttribute("department") Department department,
                                 @RequestParam("idGroup") int idChildGroup,
                                 @RequestParam("nameGroup") String name, Model model) {
        adminService.saveChildGroup(idChildGroup, name);
        model.addAttribute("msg", "Данные успешно сохранены");
        model.addAttribute("listGroups", adminService.getAllGroupChild(department));
        return "admin/admin";
    }
}
