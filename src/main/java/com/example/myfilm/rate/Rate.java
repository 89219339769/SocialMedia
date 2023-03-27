package com.example.myfilm.rate;

import com.example.myfilm.film.model.Film;
import com.example.myfilm.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "rates", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public
class Rate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false)
    private UserEntity author;

    @ManyToOne
    @JoinColumn(name = "film_id", referencedColumnName = "id", nullable = false)
    private Film film;

    private Integer rate;

}
