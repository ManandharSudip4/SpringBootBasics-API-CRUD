package com.example.tutorial.student;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

  private final StudentService studentService;

// Dependency injection
//   In the context of Java, dependency injection involves providing objects 
//   (dependencies) to other objects from outside.
//   The general concept behind dependency injection is called Inversion of Control.
//   Instead of a class creating its own dependencies, the task of creating the object
//    is transferred to someone else, and the dependency is directly used.
//   This helps in decoupling classes and makes them more modular and testable.

//   @Autowired // to create a new instance of StudentService class and inject it into following constructor
  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }

  @GetMapping
  public List<Student> getStudents() {
    return studentService.getStudents();
  }

  @PostMapping
  public void registerNewStudent(@RequestBody Student student){
    studentService.addNewStudent(student);
  }

  @PutMapping(path = "{studentId}")
  public void updateStudent(
      @PathVariable("studentId") Long studentId,
      @RequestBody Student oldStudent
      // @RequestParam(required = false) String name, 
      // @RequestParam(required = false) String email
      ){
    studentService.updateStudent(studentId, oldStudent);
  }

  @DeleteMapping(path = "{studentId}")
  public void deleteStudent(@PathVariable("studentId") Long studentId){
    studentService.deleteStudent(studentId);
  }
}
