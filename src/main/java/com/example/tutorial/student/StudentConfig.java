package com.example.tutorial.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {

    @Bean
   CommandLineRunner commandLineRunner(StudentRepository repository){
    return args -> {
        Student sudip = new Student(
        "Sudip",
        "manandharsudip8@gmail.com",
        LocalDate.of(1999, Month.DECEMBER, 13)
      );
      Student sayu = new Student(
        "Sayu",
        "manandharsayu@gmail.com",
        LocalDate.of(2000, Month.DECEMBER, 31)
      );

      repository.saveAll(
        List.of(sudip, sayu)
      );
    };

   } 
}
