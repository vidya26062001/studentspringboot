package com.example.school.repository;

import java.util.*;

import com.example.school.model.Student;

public interface StudentRepository {
    ArrayList<Student> getAllStudents();

    Student getStudentById(int studentId);

    Student addStudents(Student student);

    String  addMultipleStudents(ArrayList<Student> studentsList);
    
    Student updateStudents(int studentId, Student student);

    void deleteStudents(int studentId);
}