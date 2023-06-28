package com.abema.springstudentsapi.Controllers;

import com.abema.springstudentsapi.Models.Student;
import com.abema.springstudentsapi.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.FloatBuffer;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
        Student student = new Student(
                Long.parseLong(json.get("id")),
                json.get("first_name"),
                json.get("last_name"),
                json.get("email"),
                Double.parseDouble(json.get("avg_grade")),
                Integer.parseInt(json.get("semester"))
        );
        if (
                studentRepository.findById(student.getId()).isPresent() &&
                Objects.equals(student.getId(), studentRepository.findById(student.getId()).get().getId()) )
        {
            return new ResponseEntity<>(studentRepository.findById(student.getId()).get(), HttpStatus.FOUND);
        }
        studentRepository.saveAndFlush(student);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }
}
