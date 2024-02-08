package com.student.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.student.entity.Student;
import com.student.repo.StudentRepository;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public Student saveStudent(Student student) {
        // Calculate total marks and set pass/fail logic here
        int total = student.getEnglish() + student.getTamil() + student.getMaths() + student.getSocial() + student.getScience();
        student.setTotal(total);
        
        if (student.getEnglish() < 35 || student.getTamil() < 35 || student.getMaths() < 35 || student.getSocial() < 35 || student.getScience() < 35) {
            student.setResult("Fail"); 
        } else {
        	student.setResult("Pass"); 
        }
        
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public List<Student> searchStudents(String keyword) {
        return studentRepository.findByRoleNumberContainingOrNameContaining(keyword, keyword);
    }

    public Student updateStudent(Student newStudent, Long id) {
        return studentRepository.findById(id)
            .map(student -> {
                student.setName(newStudent.getName());
                student.setEnglish(newStudent.getEnglish());
                student.setMaths(newStudent.getMaths());
                student.setScience(newStudent.getScience());
                student.setSocial(newStudent.getSocial());
                student.setTamil(newStudent.getTamil());
                student.setRoleNumber(newStudent.getRoleNumber()); // Set rollNumber
                return studentRepository.save(student);
            })
            .orElseThrow();
    }
    
}
