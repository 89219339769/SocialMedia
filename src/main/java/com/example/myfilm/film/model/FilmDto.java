package com.example.myfilm.film.model;

import com.example.myfilm.rate.Rate;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Collection;
import java.util.List;




@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FilmDto {

    private Long id;

    private String name;


    private String description;

    private Collection<Rate> rates;

}
