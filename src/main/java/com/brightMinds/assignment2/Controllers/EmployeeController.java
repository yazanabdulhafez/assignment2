package com.brightMinds.assignment2.Controllers;

import com.brightMinds.assignment2.Models.Employee;
import com.brightMinds.assignment2.Repositories.EmployeeRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.springframework.util.StringUtils.capitalize;

@Controller
public class EmployeeController {

    private static final Logger logger = LogManager.getLogger(EmployeeController.class);

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/employees/add")
    public String addEmployeePage(Model model) {
        model.addAttribute("employees", new Employee());
        return "addEmployee";
    }

    @PostMapping("/employees/add")
    public RedirectView addNewEmployee( @RequestParam long employmentId,
                                  @RequestParam String firstName,
                                  @RequestParam String middleName,
                                  @RequestParam String lastName,
                                  @RequestParam String mobileNumber,
                                  @RequestParam String email,
                                  @RequestParam long extensionNumber,
                                  @RequestParam String employmentType){
        try {
            Employee employee=new Employee(employmentId,firstName,middleName
                                           ,lastName,mobileNumber,email,extensionNumber,employmentType);
            List<Employee> allEmployees;
            allEmployees= (List<Employee>) employeeRepository.findAll();
            if(!allEmployees.contains(employee)) {
                employeeRepository.save(employee);
            }
            return new RedirectView("/employees/fullName");
        } catch (Exception exception){
            System.out.println(exception.getMessage());
            return new RedirectView("/error");
        }
    }

    @GetMapping("/employees/{type}")
    public String returnByEmploymentType(@PathVariable String type, Model m) {
        List<Employee> employeesToBeReturned = new ArrayList<>();
        try {

            List<Employee> employees = (List<Employee>) employeeRepository.findAll();
            if(employees.isEmpty()) throw new Exception();
            for (Employee employee : employees) {
                if (Objects.equals(employee.getEmploymentType(), type)) {
                    employeesToBeReturned.add(employee);
                }
            }
            m.addAttribute("employees", employeesToBeReturned);
            return "employmentType";
        } catch (Exception e) {
            logger.info("logger message"+e.getMessage());
            return "error";
        }

    }

    @GetMapping("/employees/fullName")
    public String getEmployeesFullName(Model m) {
        try {
            List<Employee> employees = (List<Employee>) employeeRepository.findAll();
            if(employees.isEmpty()) throw new Exception();
            List<String> fullNames = new ArrayList<>();
            for (Employee employee : employees) {
                fullNames.add(employee.getFirstName() + " "
                        + employee.getMiddleName() + " " + employee.getLastName());
            }
            m.addAttribute("fullNames", fullNames);
            return "employeeNames";
        } catch (Exception e) {
            logger.info("logger message"+e.getMessage());
            return "error";
        }
    }

    @GetMapping("/employees/vaildEmails")
    public String getVaildAndNotVaildEmails(Model m) {
        try {
            List<Employee> employees = (List<Employee>) employeeRepository.findAll();
            if(employees.isEmpty()) throw new Exception();
            List<Long> validIds = new ArrayList<>();
            List<Long> notValidIds = new ArrayList<>();
            for (Employee employee : employees) {
                if (isValidEmail(employee.getEmail())) {
                    validIds.add(employee.getEmploymentId());

                } else {
                    notValidIds.add(employee.getEmploymentId());
                }
            }
            m.addAttribute("validIdsList", validIds);
            m.addAttribute("notvalidIdsList", notValidIds);
            return "idPage";
        } catch (Exception e) {
            logger.info("logger message"+e.getMessage());
            return "error";
        }

    }

    @GetMapping("/employees/cuc")
    public String getFullNameAndEmploynentType(Model m) {

        try {
            List<Employee> employees = (List<Employee>) employeeRepository.findAll();
            if(employees.isEmpty()) throw new Exception();
            List<String> fullNames = new ArrayList<>();
            for (Employee employee : employees) {
                fullNames.add("Employee Name: " + decapialize(employee.getFirstName())
                        + capitalize(employee.getMiddleName()) + capitalize(employee.getLastName())
                        + '\n' + "Employment Type: " + employee.getEmploymentType().toUpperCase());
            }
            m.addAttribute("camelAndUpperCase", fullNames);
            return "camelUpperCases";
        } catch (Exception e) {
            logger.info("logger message"+e.getMessage());
            return "error";
        }
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

    public String decapialize(String str) {
        /* we can use str.toLowerCase(); */
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }
}
