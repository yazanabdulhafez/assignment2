package com.brightMinds.assignment2.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String homePage(){
        return "index";
    }

    @GetMapping("/addEmployeeList")
    public String addEmployee(){

    }
}
