package com.example.myfilm.film;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.myfilm.film.model.Film;


public interface FilmRepository extends JpaRepository<Film, Long> {
}
