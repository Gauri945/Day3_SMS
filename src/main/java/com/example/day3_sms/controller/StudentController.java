package com.example.day3_sms.controller;

import com.example.day3_sms.dto.StudentRequestDto;
import com.example.day3_sms.dto.StudentResponseDto;
import com.example.day3_sms.service.StudentService;
import com.example.day3_sms.util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
public class StudentController {
    private final StudentService service;
    private final JwtUtil jwtUtil;

    public StudentController(StudentService service, JwtUtil jwtUtil) {
        this.service = service;
        this.jwtUtil = jwtUtil;
    }

    private void checkToken(String authHeader){
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            throw new RuntimeException("Invalid Authorization token");
        }
        String token = authHeader.substring(7);
        jwtUtil.validateTokenAndGetEmail(token);
    }

    @PostMapping("/add-student")
    public StudentResponseDto addStudent(
            @RequestHeader("Authorization") String authHeader,
            @Valid @RequestBody StudentRequestDto student){
        checkToken(authHeader);
        return service.addStudent(student);
    }

    @GetMapping("/students")
    public List<StudentResponseDto> getStudents(
            @RequestHeader(value = "Authorization", required = false) String authHeader
    ){
        checkToken(authHeader);
        return service.getAllStudents();
    }

    @PutMapping("/update/{id}")
    public StudentResponseDto updateStudent(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable String id,
            @Valid  @RequestBody StudentRequestDto student){
        checkToken(authHeader);
        return service.updateStudent(id, student);
    }

//    @DeleteMapping("/delete/{id}")
//    public void deleteStudent(
//            @RequestHeader("Authorization") String authHeader,
//            @PathVariable String id){
//        checkToken(authHeader);
//        service.deleteStudent(id);

    //Delete
   @DeleteMapping("/deleteId/{id}")
  public StudentResponseDto deleteById(@PathVariable String id){
      return service.deleteById(id);
   }
}









//    //create function API
//    @PostMapping("/add-student")
//    public StudentResponseDto addStudent( @Valid @RequestBody StudentRequestDto student){
//        return service.addStudent(student);
//    }
//
//    //Display or read
//    @GetMapping("/students")
//    public List<StudentResponseDto> getStudents(){
//        return service.getAllStudents();
//    }
//
//    //update (PUT)
//    @PutMapping("/update/{id}")
//    public StudentResponseDto updateStudent(@PathVariable String id , @RequestBody StudentRequestDto student){
//        return service.updateStudent(id,student);
//    }
//
//    //Delete
//    @DeleteMapping("/deleteId/{id}")
//    public StudentResponseDto deleteById(@PathVariable String id){
//        return service.deleteById(id);
//    }
//
//
//    @PatchMapping("/patch/{id}")
//    public StudentResponseDto patchStudent(
//           @PathVariable String id,
//           @RequestBody StudentRequestDto student) {
//
//       return service.patchStudent(id, student);
//    }


