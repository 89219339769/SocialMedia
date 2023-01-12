package com.example.myfilm.film.model;


import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Mapper {


    public static FilmDto toFilmDto (Film film) {
        return FilmDto.builder()
                .id(film.getId())
                .name(film.getName())
                .averageRates(0)
                .build();
    }


}
