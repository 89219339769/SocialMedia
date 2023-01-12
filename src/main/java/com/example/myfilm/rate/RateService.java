package com.example.myfilm.rate;

public interface RateService {


    Rate addRate(Long userId, Long filmId, Rate rate);
}
