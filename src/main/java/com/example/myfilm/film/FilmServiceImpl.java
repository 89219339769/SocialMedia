package com.example.myfilm.film;

import com.example.myfilm.film.model.FilmDto;
import com.example.myfilm.film.model.Mapper;
import com.example.myfilm.rate.Rate;
import com.example.myfilm.rate.RateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import ru.practicum.shareit.exceptions.NotFoundException;
import com.example.myfilm.film.model.Film;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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


        List<Integer> average = rates.stream()
                .map(rate -> rate.getRate())
                .collect(Collectors.toList());

        Double sum = 0.0;
        for (Integer rage : average) {
            sum = sum + rage;
        }
        Double averageRnge = 0.0;
        if (average.size() != 0) {
            averageRnge = sum / average.size();
        }

        FilmDto filmDto = Mapper.toFilmDto(film);
        filmDto.setAverageRates(averageRnge);
        return filmDto;

    }


    @Override
    public void delete(Long id) {
        repository.deleteById(id);

    }

    @Override
    public Page<FilmDto> getAllFilms(int from, int size,Pageable pageable ) {
//сортируем от большего к меньшему по номерам нужно на DESC поменять
        Pageable pageable1 = PageRequest.of(from, size, Sort.by(Sort.Direction.ASC, "id"));
        Collection<Rate> rates = rateRepository.findAll();
        Page<Film> films = repository.findAll(pageable1);


        List<FilmDto> filmDtos = new ArrayList<>();
        for (Film film : films) {
            Collection<Rate> ratesForDto;
            ratesForDto = rates.stream()
                    .filter(rate -> rate.getFilm().getId().equals(film.getId()))
                    .collect(Collectors.toList());

            FilmDto filmDto = Mapper.toFilmDto(film);


            List<Integer> average = ratesForDto.stream()
                    .map(rate -> rate.getRate())
                    .collect(Collectors.toList());

            Double sum = 0.0;
            for (Integer rage : average) {
                sum = sum + rage;
            }
            Double averageRnge = 0.0;
            if (average.size() != 0) {
                averageRnge = sum / average.size();
            }

            filmDto.setAverageRates(averageRnge);
            filmDtos.add(filmDto);

        }

        Page<FilmDto> page = new PageImpl<>(filmDtos);
        return page;
    }
}
