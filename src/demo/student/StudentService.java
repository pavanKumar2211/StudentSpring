package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }


    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentByEmail= studentRepository.findStudentByEmail(student.getEmail());
        if(studentByEmail.isPresent()) {
            throw new IllegalStateException("Email is taken");
        }else{
            studentRepository.save(student);
        }

    }


    public void delStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if(!exists){
            throw new IllegalStateException("The id " + studentId + " Doesnt exist");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
         Student student = studentRepository.findById(studentId)
                                            .orElseThrow(() -> new IllegalStateException("Student by id" + studentId + " doesnt exist"));

         if(name != null && name.length() > 0 && !Objects.equals(student.getName(), name)){
             student.setName(name);
         }

         if(email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)){

             Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
             if(studentOptional.isPresent()){
                 throw new IllegalStateException("The email " + email + "already exists");
             }
             student.setEmail(email);
         }
    }
}
