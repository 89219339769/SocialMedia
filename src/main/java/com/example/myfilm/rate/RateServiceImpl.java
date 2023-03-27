package com.example.myfilm.rate;

import com.example.myfilm.film.FilmRepository;
import com.example.myfilm.film.model.Film;
import com.example.myfilm.user.UserEntity;
import com.example.myfilm.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor

public class RateServiceImpl implements RateService {

    private final  RateRepository rateRepository;

    private final  UserRepository userRepository;

    private final   FilmRepository filmRepository;

    @Override
    public RateDto addRate(Long userId, Long filmId) {
        UserEntity user = userRepository.findById(Math.toIntExact(userId))
                                        .orElseThrow(() -> new ru.practicum.shareit.exceptions.NotFoundException("не найден пользователь с id: " + userId));

        Film film = filmRepository.findById(filmId)
                .orElseThrow(() -> new ru.practicum.shareit.exceptions.NotFoundException("не найден пользователь с id: " + userId));

        Rate rate = new Rate();
        rate.setAuthor(user);

        rate.setFilm(film);

        return Mappnig.RateToRateDto(rateRepository.save(rate));
    }
}
