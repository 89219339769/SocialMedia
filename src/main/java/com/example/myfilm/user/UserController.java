package com.example.myfilm.user;

import com.example.myfilm.film.FilmService;
import com.example.myfilm.film.model.Film;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;


    @PostMapping
    public User create(@RequestBody User user) {
        return userService.save(user);
    }
}