package com.example.myfilm.film.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@Table(name = "films", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public
class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(unique = true, length = 100, nullable = false)
    private String description;

}