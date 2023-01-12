package com.example.myfilm.film;

import com.example.myfilm.film.model.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    List<User> getAllUsers();

    User get(Long id);

    void delete(Long id);

    User updateUser(Long id, User user);
}