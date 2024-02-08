package com.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.student.entity.Student;
import com.student.repo.StudentRepository;
import com.student.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
	@Autowired
	private StudentService studentService;

	@PostMapping("/add")
	public Student addStudent(@RequestBody Student student) {
		return studentService.saveStudent(student);
	}
	@PutMapping("/updateById/{id}")
    public Student updateStudent(@RequestBody Student newStudent, @PathVariable Long id) {
        return studentService.updateStudent(newStudent, id);
    }

	@GetMapping("/all")
	public List<Student> getAllStudents() {
		return studentService.getAllStudents();
	}

	@GetMapping("/{id}")
	public Student getStudentById(@PathVariable Long id) {
		return studentService.getStudentById(id);
	}

	@GetMapping("search/{key}")
	public List<Student> getStudentByName(@PathVariable String key) {
		return studentService.searchStudents(key);
	}
}
