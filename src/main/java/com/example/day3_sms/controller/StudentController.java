package com.example.day3_sms.controller;


import com.example.day3_sms.dto.StudentRequestDto;
import com.example.day3_sms.dto.StudentResponseDto;
import com.example.day3_sms.model.StudentModel;
import com.example.day3_sms.repository.StudentRepository;
import com.example.day3_sms.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
   private final StudentService service;
    private final StudentRepository studentRepository;

    public StudentController(StudentService service, StudentRepository studentRepository) {
        this.service = service;
        this.studentRepository = studentRepository;
    }

    //create function API
    @PostMapping("/add-student")
    public StudentResponseDto addStudent( @Valid @RequestBody StudentRequestDto student){
        return service.addStudent(student);
    }

    //Display or read
    @GetMapping("/students")
    public List<StudentResponseDto> getStudents(){
        return service.getAllStudents();
    }

    //update (PUT)
    @PutMapping("/update/{id}")
    public StudentResponseDto updateStudent(@PathVariable String id , @RequestBody StudentModel student){
        return service.updateStudent(id,student);
    }

    //Delete
    @DeleteMapping("/deleteId/{id}")
    public StudentResponseDto deleteById(@PathVariable String id){
        return service.deleteById(id);
    }

}
