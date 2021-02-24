package com.codewarts.controller.admin;

import com.codewarts.entity.Child;
import com.codewarts.entity.ChildGroup;
import com.codewarts.entity.Department;
import com.codewarts.service.AdminServiceImpl;
import com.codewarts.service.MainServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
public class EditController {
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

    @GetMapping("/admin/child/{editChild}")
    public String getChildAndEdit(@PathVariable(name = "editChild") int idChild, Model model,
                                  @ModelAttribute("listGroups") List<ChildGroup> groupList){
        model.addAttribute("listGroups", groupList);
        model.addAttribute("child", adminServiceImpl.getChildById(idChild));
        model.addAttribute("listAttendance", adminServiceImpl.getAllAttendanceByChild(idChild));
        model.addAttribute("listPayments", adminServiceImpl.getAllPaymentsByChild(idChild));
        return "admin/edit/childEdit";
    }

    @PostMapping(value = "/admin/child/{editChild}")
    public String adminEditAndSave(@PathVariable(name = "editChild") int idChild,
                                   @ModelAttribute("child") Child child, Model model,
                                   @RequestParam(name = "birthdayDate")
                                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                   @RequestParam(value = "mother") String mother,
                                   @RequestParam(value = "phoneMother") String phoneMother,
                                   @RequestParam(value = "father")String father,
                                   @RequestParam(value = "phoneFather") String phoneFather,
                                   @RequestParam(value = "selectGroup") int idGroup,
                                   @ModelAttribute("listGroups") List<ChildGroup> groupList){
        model.addAttribute("listGroups", groupList);
        if (adminServiceImpl.saveChild(idChild, date, child.getName(), child.getSurname(), idGroup, mother,
                phoneMother, father, phoneFather)){
            model.addAttribute("msg", "Данные успешно сохранены");
        } else {
            model.addAttribute("msg", "Неверный формат вводных данных");
        }
        model.addAttribute("listGroups", groupList);
        model.addAttribute("child", adminServiceImpl.getChildById(idChild));
        model.addAttribute("listAttendance", adminServiceImpl.getAllAttendanceByChild(idChild));
        model.addAttribute("listPayments", adminServiceImpl.getAllPaymentsByChild(idChild));
        return "admin/edit/childEdit";
    }

    @PostMapping(value = "/admin/edit/editPayment/{editChild}")
    public String editPaymentPage(@PathVariable(name = "editChild") int idChild, Model model,
                                  @RequestParam(value = "idPayment", defaultValue = "0") int idPayment){
        if (idPayment != 0) {
            model.addAttribute("paymentToEdit", adminServiceImpl.getAllPaymentsByChildAndPayment(idPayment));
            return "admin/edit/editPayment";
        } else {
            return "redirect:/admin/child/" + idChild;
        }
    }

    @GetMapping(value = "/admin/edit/editGroups")
    public String editGroups(@RequestParam("button") int idChildGroup, Model model){
        model.addAttribute("childGroup", adminServiceImpl.getChildGroup(idChildGroup));
        return "admin/edit/editGroup";
    }

    @RequestMapping(value = "/admin/edit/editPaymentSave")
    public String editPaymentPageSave(Model model, @RequestParam("idPayment") int idPayment,
                                      @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                      @RequestParam("sum") String sum){
        if (adminServiceImpl.savePayment(idPayment,date,sum)){
            model.addAttribute("msg", "Данные успешно сохранены");
        } else {
            model.addAttribute("msg", "Неверный формат вводных данных");
        }
        model.addAttribute("paymentToEdit", adminServiceImpl.getAllPaymentsByChildAndPayment(idPayment));
        return "admin/edit/editPayment";
    }

    @PostMapping(value = "/admin/edit/editGroupSave")
    public String editGroupsSave(@ModelAttribute("department") Department department,
                                 @RequestParam("idGroup") int idChildGroup,
                                 @RequestParam("nameGroup") String name, Model model) {
        if (adminServiceImpl.saveChildGroup(idChildGroup, name, department)){
            model.addAttribute("msg", "Данные успешно сохранены");
        } else {
            model.addAttribute("msg", "Неверный формат вводных данных");
        }
        model.addAttribute("childGroup", adminServiceImpl.getChildGroup(idChildGroup));
        return "admin/edit/editGroup";
    }
}
