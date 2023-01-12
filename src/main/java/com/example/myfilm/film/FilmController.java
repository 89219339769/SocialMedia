package com.example.myfilm.film;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.myfilm.film.model.Film;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/films")
public class FilmController {

    private final FilmService userService;

    @GetMapping
    public List<Film> getAll() {
        return userService.getAllUsers();
    }

    @PostMapping
    public Film create(@RequestBody Film film) {
        return userService.save(film);
    }


    @GetMapping("/{id}")
    public Film findUserById(@PathVariable Long id) {
        return userService.get(id);

    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }
}
