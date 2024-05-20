package com.anil.TestRun.controller;

import com.anil.TestRun.entity.Student;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/student")
public class StudentController
{
    Map<Integer, Student>map=new HashMap<>();

    @GetMapping("/getallstudents")
    public List<Student> getAllStudent(){
        List<Student>list=new ArrayList<>();
        for(Map.Entry<Integer, Student>entry:map.entrySet()){
            list.add(entry.getValue());
        }
        return list;
    }
    @PostMapping("/insertstudent")
    public String insertStudent(@RequestBody Student student){
        System.out.println("Student :"+student);
        map.put(student.getId(), student);
        return "Inserted Successfully";
    }

}
