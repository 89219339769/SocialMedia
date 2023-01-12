package com.example.myfilm.film;

import com.example.myfilm.film.model.Film;

import java.util.List;

public interface FilmService {
    Film save(Film film);

    List<Film> getAllUsers();

    Film get(Long id);

    void delete(Long id);


}