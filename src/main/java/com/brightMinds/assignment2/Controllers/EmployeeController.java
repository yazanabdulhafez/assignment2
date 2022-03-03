package com.brightMinds.assignment2.Controllers;

import com.brightMinds.assignment2.Models.Employee;
import com.brightMinds.assignment2.Repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.springframework.util.StringUtils.capitalize;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/employees/{type}")
    public String returnByEmploymentType(@PathVariable String type, Model m){
        List<Employee> employeesToBeReturned = new ArrayList<>();
        List<Employee> employees = (List<Employee>) employeeRepository.findAll();
        for(Employee employee:employees){
            if (Objects.equals(employee.getEmploymentType(), type)){
               employeesToBeReturned.add(employee);
            }
        }
        m.addAttribute("employees" , employeesToBeReturned);
        return "employmentType";
    }

    @GetMapping("/employees/fullName")
    public String getEmployeesFullName(Model m){
        List<Employee> employees = (List<Employee>) employeeRepository.findAll();
        List<String> fullNames=new ArrayList<>();
        for(Employee employee:employees){
            fullNames.add(employee.getFirstName()+" "
                    +employee.getMiddleName()+" "+employee.getLastName());
        }
        m.addAttribute("fullNames",fullNames);
        return "employeeNames";
    }

    @GetMapping("/employees/vaildEmails")
    public String getVaildAndNotVaildEmails(Model m){
        List<Employee> employees = (List<Employee>) employeeRepository.findAll();
        List<Long> vaildIds=new ArrayList<>();
        List<Long> notVaildIds=new ArrayList<>();
        for(Employee employee:employees){
           if (isValidEmail(employee.getEmail())){
               vaildIds.add(employee.getEmploymentId());

           }else{
               notVaildIds.add(employee.getEmploymentId());
           }
        }
        m.addAttribute("vaildIdsList",vaildIds);
        m.addAttribute("notvaildIdsList",notVaildIds);
        return "idPage";

    }

    @GetMapping("/employees/cuc")
    public String getFullNameAndEmploynentType(Model m){
        List<Employee> employees = (List<Employee>) employeeRepository.findAll();
        List<String> fullNames=new ArrayList<>();
        for(Employee employee:employees){
            fullNames.add("Employee Name: "+decapialize(employee.getFirstName())
                    +capitalize(employee.getMiddleName())+capitalize(employee.getLastName())
                    +'\n'+"Employment Type: "+employee.getEmploymentType().toUpperCase());
        }
        m.addAttribute("camelAndUpperCase",fullNames);
        return "camelUpperCases";
    }

    public static boolean isValidEmail(String email) {
        if (email != null) {
            Pattern p = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                    + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
            Matcher m = p.matcher(email);
            return m.find();
        }
        return false;
    }

    public String decapialize(String str){
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }
}
