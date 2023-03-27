package com.example.myfilm.rate;

public class Mappnig {

    public static RateDto RateToRateDto(Rate rate) {


        return new RateDto(rate.getId(), rate.getAuthor().getId(), rate.getFilm(), rate.getRate());
    }
}
