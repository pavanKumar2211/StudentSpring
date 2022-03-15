package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
                Student lando = new Student(
                        1L,
                        "lando",
                        "lando@gmail.com",
                        LocalDate.of(1996, 12, 21)

                );

                Student ric = new Student(
                            2L,
                            "Ric",
                             "Ric@gmail.com",
                            LocalDate.of(1992, 12, 21)


                        );

                repository.saveAll(List.of(lando, ric));
        };
    }
}
