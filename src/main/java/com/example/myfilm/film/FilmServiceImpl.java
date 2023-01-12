package com.example.myfilm.film;

import com.example.myfilm.film.model.FilmDto;
import com.example.myfilm.film.model.Mapper;
import com.example.myfilm.rate.Rate;
import com.example.myfilm.rate.RateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import ru.practicum.shareit.exceptions.NotFoundException;
import com.example.myfilm.film.model.Film;


import java.util.Collection;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor

public class FilmServiceImpl implements FilmService {

    private final FilmRepository repository;

    private final RateRepository rateRepository;

    @Override
    public Film save(Film film) {
        repository.save(film);
        return film;
    }


    @Override
    public FilmDto get(Long id) {
        Film film = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Не найден пользователь с id: " + id));


        Collection<Rate> rates = rateRepository.findAllByFilmIdIs(id);


        FilmDto filmDto = Mapper.toFilmDto(film);
        filmDto.setRates(rates);
        return filmDto;

    }


    @Override
    public void delete(Long id) {
        repository.deleteById(id);

    }

    @Override
    public List<Film> getAllUsers() {
        return repository.findAll();
    }
}
