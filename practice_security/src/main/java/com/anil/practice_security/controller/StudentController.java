package com.anil.practice_security.controller;

import com.anil.practice_security.service.StudentService;
import com.anil.practice_security.util.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/allstudents")
    public List<Student> getStudents(){
        return studentService.getallStudents();
    }

    @GetMapping("current-user")
    public String getLoginUser(Principal principal){
        return principal.getName();
    }
}
