package com.example.myfilm.film;

import com.example.myfilm.film.model.Film;
import com.example.myfilm.film.model.FilmDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FilmService {
    Film save(Film film);

   List<FilmDto> getAllFilms();

    FilmDto get(Long id);

    void delete(Long id);


}