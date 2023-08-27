package com.example.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;


import com.example.school.service.StudentH2Service;
import com.example.school.model.Student;

@RestController

public class StudentController {

    @Autowired
    private StudentH2Service studentService;

    @GetMapping("/students")
    public ArrayList<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    

    @PostMapping("/students")
    public Student addStudents(@RequestBody Student student){
        return studentService.addStudents(student);
    }

    @PostMapping("/students/bulk")

    public String addMultipleStudents(@RequestBody ArrayList<Student> studentsList) { 

        return studentService.addMultipleStudents(studentsList);
    }
    
    @GetMapping("/students/{studentId}")
    public Student getStudentById(@PathVariable("studentId") int studentId){
        return studentService.getStudentById(studentId);
    }


    @PutMapping("/students/{studentId}")
    public Student updateStudents(@PathVariable("studentId") int studentId, @RequestBody Student student){
        return studentService.updateStudents(studentId, student);
    }

    
    @DeleteMapping("/students/{studentId}")
    public void deleteStudents(@PathVariable int studentId){
        studentService.deleteStudents(studentId);
    }

    
}}