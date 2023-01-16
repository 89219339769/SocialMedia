package com.example.myfilm.film;

import com.example.myfilm.film.model.Film;
import com.example.myfilm.film.model.FilmDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FilmService {
    Film save(Film film);



    FilmDto get(Long id);

    void delete(Long id);


    Page<FilmDto> getAllFilms(int from, int size, Pageable pageable );
}