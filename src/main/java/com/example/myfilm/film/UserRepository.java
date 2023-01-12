package com.example.myfilm.film;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.myfilm.film.model.User;


public interface UserRepository extends JpaRepository<User, Long> {
}
