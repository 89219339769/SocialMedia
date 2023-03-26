package com.example.myfilm;

import com.example.myfilm.film.model.Film;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class MyfilmApplicationTests {

    public static void main(String[] args) {
        ApplicationContext context =    SpringApplication.run(MyfilmApplication.class, args);


        Film film2 = context.getBean(Film.class);

        System.out.println(film2.toString());



    }

}
