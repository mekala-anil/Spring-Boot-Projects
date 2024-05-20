package com.anil.practice_security.service;

import com.anil.practice_security.util.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    List<Student>students=new ArrayList<>();

    StudentService(){
        students.add(new Student(121,"Anil","VIT"));
        students.add(new Student(122,"Rohit","VIT"));
        students.add(new Student(123,"Virat","SRM"));
    }

    public List<Student>getallStudents(){
        return students;
    }
}
