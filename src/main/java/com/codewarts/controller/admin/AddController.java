package com.codewarts.controller.admin;

import com.codewarts.entity.Child;
import com.codewarts.entity.ChildGroup;
import com.codewarts.entity.Department;
import com.codewarts.entity.Parent;
import com.codewarts.service.AdminServiceImpl;
import com.codewarts.service.MainServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AddController {
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

    @GetMapping("/addChild")
    public String addChildGet(Model model, @ModelAttribute("listGroups") List<ChildGroup> groupList,
                              Child child, Parent parent){
        model.addAttribute("listGroups", groupList);
        return "admin/add/addChild";
    }

    @PostMapping("/addChild")
    public String addChildPost(Model model, @ModelAttribute("child") @Valid Child child,
                               BindingResult result, @RequestParam(name = "birthdayDate", defaultValue = "")
                               @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                               @ModelAttribute("parent") Parent parent,
                               @RequestParam(value = "selectGroup") int idGroup){
        if (result.hasErrors()){
            return "admin/add/addChild";
        }
        if (adminServiceImpl.addChild(date, child, parent, idGroup)){
            model.addAttribute("msg", "Данные успешно добавлены!");
        } else {
            model.addAttribute("msg", "Введены некорректные данные");
        }
        return "admin/add/addChild";
    }

    @GetMapping("/addPayment")
    public String addPaymentGet(Model model, @ModelAttribute("department") Department department){
        model.addAttribute("now", LocalDate.now());
        model.addAttribute("listChild", adminServiceImpl.getAllChild(department));
        return "admin/add/addPayment";
    }

    @PostMapping("/addPayment")
    public String addPaymentGet(Model model, @RequestParam(name = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                @RequestParam(value = "sum") String sum,
                                @RequestParam(value = "listChild") int idChild,
                                @ModelAttribute("department") Department department){
        if (adminServiceImpl.addPayment(date, sum, idChild)){
            model.addAttribute("msg", "Оплата успешно внесена");
        } else {
            model.addAttribute("msg", "Неверный формат суммы");
        }
        model.addAttribute("now", LocalDate.now());
        model.addAttribute("listChild", adminServiceImpl.getAllChild(department));
        return "admin/add/addPayment";
    }

    @GetMapping("/addGroup")
    public String makeNewGroup(){
        return "admin/add/addGroup";
    }

    @PostMapping("/addGroup")
    public String makeNewGroupPost(Model model, @ModelAttribute("department") Department department,
                                   @RequestParam("name")String name, @RequestParam("lessonTime") String lessonTime){
        if (adminServiceImpl.addGroup(name, department, lessonTime)){
            model.addAttribute("msg", "Группа успешно добавлена");
        } else {
            model.addAttribute("msg", "Имя группы должно содержать только буквы и цифры" + "<br>" +
                    "Либо такая группа уже существует");
        }
        return "admin/add/addGroup";
    }
}
