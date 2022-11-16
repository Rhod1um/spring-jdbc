package com.example.demo.controllers;

import com.example.demo.models.Department;
import com.example.demo.models.Employee;
import com.example.demo.repositories.DepartmentRepository;
import com.example.demo.repositories.EmployeeRepository;
import com.example.demo.repositories.IRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

@Controller
public class EmployeeController {

    //private final IRepository<Department> departmentRepository = new DepartmentRepository();
    private final IRepository<Employee> employeeRepository = new EmployeeRepository();

    @GetMapping("/employees")
    public String allEmployees(Model model){
        model.addAttribute("employees", employeeRepository.getAllEntities());
        return "employees";
    }
    @GetMapping("/employee") //URL bliver localhost://employee?id=x
    public String getEmployee(@RequestParam int id, Model model){
        model.addAttribute("employee", employeeRepository.getSingleById(id));
        return "employee";
    }
    @GetMapping("/create-employee")
    public String createEmployee(){
        return "create-employee";
    }
    @PostMapping("/created-employee")
    public String createEmployee(WebRequest webRequest){
        String name = webRequest.getParameter("name");
        employeeRepository.create(new Employee(name));
        return "index";
    }
}
