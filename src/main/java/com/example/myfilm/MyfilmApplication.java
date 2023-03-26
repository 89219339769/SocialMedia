package com.example.myfilm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class MyfilmApplication {

    public static void main(String[] args) {
        ApplicationContext context =    SpringApplication.run(MyfilmApplication.class, args);



    }

}
