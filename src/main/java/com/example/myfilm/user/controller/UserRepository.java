package com.example.myfilm.user.controller;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.shareit.user.model.User;


public interface UserRepository extends JpaRepository<User, Long> {
}
