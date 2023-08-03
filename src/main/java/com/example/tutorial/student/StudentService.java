package com.example.tutorial.student;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;


/* 
    In the context of Java and Spring, a bean is an object that is created, constructed, 
    and managed by the Spring IoC (Inversion of Control) container.
    Beans are the fundamental components of any Spring application and can be accessed in different 
    contexts. Beans can be anything from a configuration, a service, a database connection factory, 
    or just about anything else. In Spring, there are several annotations that can be used to define 
    beans, including @Component and @Service. These annotations are used to indicate that a class is a 
    Spring-managed bean and should be instantiated and managed by the Spring container.
*/

@Service
public class StudentService {

  private final StudentRepository studentRepository;


  public StudentService(StudentRepository studentRepository){
    this.studentRepository = studentRepository;
  }

  public List<Student> getStudents() {
    return studentRepository.findAll();
  }

  public void addNewStudent(Student student) {
    Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
    if (studentOptional.isPresent()){
      throw new IllegalStateException("email already taken");
    } else {
      studentRepository.save(student);
    }
  }

  public void deleteStudent(Long studentId) {
    boolean exists = studentRepository.existsById(studentId);
    if (!exists){
      throw new IllegalStateException(
        "Student with id " + studentId + " does not exists" 
      );
    }
    studentRepository.deleteById(studentId);
  }

  @Transactional
  public void updateStudent(Long studentId, Student oldStudent) {
    

    Student student = studentRepository.findById(studentId).orElseThrow(()-> new IllegalStateException(
          "Student with id " + studentId + " does not exists."));

    String name = oldStudent.getName();
    String email = oldStudent.getEmail();

    if (name != null && name.length()>0 && !Objects.equals(student.getName(), name)){
      student.setName(name);
    }

    if (email != null && email.length()>0 && !Objects.equals(student.getEmail(), email)){
      Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
      if (studentOptional.isPresent()){
        throw new IllegalStateException("email already taken");
      }
      student.setEmail(email);
    }

    studentRepository.save(student);
  }
}
