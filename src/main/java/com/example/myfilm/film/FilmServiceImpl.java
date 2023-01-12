package com.example.myfilm.film;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import ru.practicum.shareit.exceptions.NotFoundException;
import com.example.myfilm.film.model.Film;


import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor

public class FilmServiceImpl implements FilmService {

    private final FilmRepository repository;

    @Override
    public Film save(Film film) {
        repository.save(film);
        return film;
    }

    @Override
    public List<Film> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public Film get(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Не найден пользователь с id: " + id));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);

    }


}
