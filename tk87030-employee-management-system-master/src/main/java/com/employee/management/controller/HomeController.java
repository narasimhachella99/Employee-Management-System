package com.employee.management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.employee.management.model.Employee;
import com.employee.management.service.IEmployeeService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private static final String EMP_LOGIN = "emp/login";
    private static final String HR_LOGIN = "hr/login";

    private static final String EMP_DASHBOARD = "emp/dashboard";
    private static final String HR_DASHBOARD = "hr/dashboard";
    private static final String INDEX = "index";

    private static final String EDIT_EMPLOYEE = "emp/edit_emp";
    private static final String ALL_EMPLOYEES  = "hr/employees";

    private final IEmployeeService employeeService;

    private final HttpServletRequest req;

    // get method for the home page
    @GetMapping("/")
    private String home() {
        return INDEX;
    }

    // get method for the employee login page
    @GetMapping("/emp/login")
    private String empLogin() {
        return EMP_LOGIN;
    }

    // get method for the hr login page
    @GetMapping("/hr/login")
    private String hrLogin() {
        return HR_LOGIN;
    }

    // get method for the hr dashboard
    @GetMapping("/hr/dashboard")
    private String hrDashboard(Model model) {
        Employee e = (Employee) req.getSession().getAttribute("user");
        if (e != null) {
            model.addAttribute("employee", e);
        }
        return HR_DASHBOARD;
    }

    // get method for the employee dashboard
    @GetMapping("/emp/dashboard")
    private String empDashboard(Model model) {
        Employee e = (Employee) req.getSession().getAttribute("user");
        if (e != null) {
            model.addAttribute("employee", e);
        }
        return EMP_DASHBOARD;
    }

    // post method for the hr to login
    @PostMapping("/hr/login")
    private String hrDashboard(String email, String password, Model model) {
        Employee e = employeeService.findByEmail(email);
        if (e != null) {
            if (e.getPassword().equals(password) && e.getRole().getName().equals("HR")) {
                req.getSession().setAttribute("user", e);
                return "redirect:/hr/dashboard";
            } else {
                model.addAttribute("error", "Password is incorrect");
                return HR_LOGIN;
            }
        }
        model.addAttribute("error", "User not found");
        return HR_LOGIN;
    }

    // post method for the employee to login
    @PostMapping("/emp/login")
    private String empLogin(String email, String password, Model model) {
        Employee e = employeeService.findByEmail(email);
        if (e != null) {
            if (e.getPassword().equals(password) && e.getRole().getName().equals("EMPLOYEE")) {
                req.getSession().setAttribute("user", e);
                return "redirect:/emp/dashboard";
            } else {
                model.addAttribute("error", "Password is incorrect");
                return EMP_LOGIN;
            }
        }
        model.addAttribute("error", "User not found");
        return EMP_LOGIN;
    }

    // logout method for invalidating session and redirecting to home page
    @GetMapping("/logout")
    private String logout() {
        req.getSession().invalidate();
        return "redirect:/";
    }

    // get method for employee edit page
    @GetMapping("/emp/edit")
    private String empEdit(Model model, String role) {
        Employee e = (Employee) req.getSession().getAttribute("user");
        if (e != null) {
            model.addAttribute("emp", e);
            model.addAttribute("role", e.getRole().getName());
        }
        return EDIT_EMPLOYEE;
    }

    @GetMapping("hr/employees")
    private String employeeList(Model model){
        model.addAttribute("employees", employeeService.getAllEmployees());
        return ALL_EMPLOYEES;
    }

    // post method for employee edit page
    @PostMapping("/emp/edit")
    private String empEdit(
            Long id,
            String firstName,
            String lastName,
            String email,
            String password,
            String contact,
            String qualification,
            String address,
            String birthDate,
            String confirm_password,
            Model model) {
        Employee e = employeeService.getEmployee(id);
        if (password.equals(confirm_password)) {
            String role = e.getRole().getName();
            e.setFirstName(firstName);
            e.setLastName(lastName);
            e.setQualification(qualification);
            e.setContact(contact);
            e.setEmail(email);
            e.setPassword(password);
            e.setAddress(address);
            if (birthDate != null) {
                e.setBirthDate(birthDate);
            }
            employeeService.saveEmployee(e);
            req.getSession().setAttribute("user", e);
            return role.equals("EMPLOYEE") ? "redirect:/emp/dashboard" : "redirect:/hr/dashboard";
        } else {
            model.addAttribute("error", "Password does not match");
            model.addAttribute("emp", e);
            model.addAttribute("role", e.getRole().getName());
            return EDIT_EMPLOYEE;
        }
    }

}
