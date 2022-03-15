package com.example.demo.student;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping
public class StudentController {

    private final StudentService studentService;
    @Autowired
    public StudentController(StudentService studentService){
        this.studentService = studentService;

    }
    
    @GetMapping
    public List<Student> getStudents(){
        return studentService.getStudents();
    }



    @PostMapping
    public void registerNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentid") Long studentId){
        studentService.delStudent(studentId);
    }

    public void updateStudent(@PathVariable("studentid") Long studentId,
                              @PathVariable(required = false)String name,
                              @PathVariable(required = false)String email){
        studentService.updateStudent(studentId, name, email);

    }
}
