package com.example.school.service;

import com.example.school.model.Student;
import com.example.school.model.StudentRowMapper;
import com.example.school.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;

@Service

public class StudentH2Service implements StudentRepository {
    
    @Autowired
    private JdbcTemplate db;

    @Override 

    public ArrayList<Student> getStudents() {
        return (ArrayList<Student>) db.query("select * from student", new StudentRowMapper());
    }

    @Override 

    public Student getStudentById(int id) {
        try {
            return db.queryForObject("select * from student where id = ?", new StudentRowMapper(), id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    
    

    @Override 

    public Student addStudent(Student student) {
    db.update("insert into student(studentName, gender, standard) values (?,?,?)", student.getStudentName(),
              student.getGender(), student.getStandard());
    return db.queryForObject("select * from student where studentName = ? and gender = ?", new StudentRowMapper(),
              student.getStudentName(), student.getGender());
    }
   

    @Override 

    public void deleteStudent(int studentId) {
        db.update("delete from student where studentId = ?", studentId);
    }

    @Override 

    public Student updateStudent(int studentId, Student student) {
        if (student.getStudentName() != null) {
            db.update("update student set studentName = ? where studentId =?", student.getStudentName(), studentId);
        }

        if (student.getGender() != null) {
            db.update("update student set gender = ? where studentId =?", student.getGender(), studentId);
        }
        if (student.getStandard() != 0) {
            db.update("update student set standard = ? where studentId =?", student.getStandard(), studentId);
        }
        return getStudentById(studentId);

    }
}
