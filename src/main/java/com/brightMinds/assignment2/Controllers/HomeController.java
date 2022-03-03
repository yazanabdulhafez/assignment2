package com.brightMinds.assignment2.Controllers;

import com.brightMinds.assignment2.Models.Employee;
import com.brightMinds.assignment2.Repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/")
    public String homePage(){
        return "index";
    }

    @GetMapping("/addEmployeeList")
    public String addEmployee(){
        Employee employeeOne=new Employee(001,"John","Zakaria","","529853225","john@farabi.ae",632,"Permanent");
        Employee employeeTwo=new Employee(002,"Kamini","Krishna","Menon","566333225","kamini@farabi.ae",600,"Intern");
        Employee employeeThree=new Employee(003,"Kalyan","Lohitha","Warrier","529852369","kalyanfarabi.ae",623,"Contract");
        Employee employeeFour=new Employee(004,"Peter","","Jones","236965325","peter@farabi.a",609,"Permanent");
        employeeRepository.save(employeeOne);
        employeeRepository.save(employeeTwo);
        employeeRepository.save(employeeThree);
        employeeRepository.save(employeeFour);
        return "index";
    }
}
