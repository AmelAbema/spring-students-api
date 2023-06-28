package com.abema.springstudentsapi.Controllers;

import com.abema.springstudentsapi.Models.Student;
import com.abema.springstudentsapi.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class MainController {
    StudentRepository studentRepository;
    @Autowired
    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents(){
       return new ResponseEntity<>(studentRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Student>> getStudentByID(@PathVariable Long id){
        return new ResponseEntity<>(studentRepository.findById(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Map<String, String> json){
        studentRepository.save(new Student(
                String.valueOf(json.get("id")),
                json.get("first_name"),
                json.get("last_name"),
                json.get("email"),
                json.get("avg_grade"),
                String.valueOf(json.get("semester"))
                )
        );
        return new ResponseEntity<>();
    }
}
