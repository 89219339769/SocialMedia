package com.example.myfilm.user;

import com.example.myfilm.film.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
