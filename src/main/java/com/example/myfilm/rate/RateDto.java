package com.example.myfilm.rate;

import com.example.myfilm.film.model.Film;
import com.example.myfilm.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
public class RateDto {




    private Long id;


    private long authorId;


    private Film film;

    private Integer rate;


    public RateDto(Long id, long authorId, Film film, Integer rate) {
        this.id = id;
        this.authorId = authorId;
        this.film = film;
        this.rate = rate;
    }
}
