package com.example.myfilm.film;


import com.example.myfilm.film.model.FilmDto;
//import com.example.myfilm.rate.Rate;
//import com.example.myfilm.rate.RateService;
import com.example.myfilm.rate.Rate;
import com.example.myfilm.rate.RateDto;
import com.example.myfilm.rate.RateService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.example.myfilm.film.model.Film;


import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.XmlType;
import java.net.http.HttpRequest;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/films")
public class FilmController {

    private final FilmService filmService;

    private final RateService rateService;



    @PostMapping
    public Film create(@RequestBody Film film) {
        return filmService.save(film);
    }


    @PostMapping("/{filmId}/rate/{userId}")
    public RateDto add(@PathVariable Long filmId, @PathVariable Long userId) {

     return    rateService.addRate(filmId,userId ) ;

    }


    @GetMapping("/{id}")
    public FilmDto findFilmById(@PathVariable Long id) {
        return filmService.get(id);

    }



    @GetMapping
    public ResponseEntity<Page<FilmDto>> getAll(@RequestParam (defaultValue = "0") int from,
                                                @RequestParam (defaultValue = "100") int size,
                                                Pageable pageable) {
        Page<FilmDto> allFilms =  filmService.getAllFilms(from, size, pageable);
        allFilms.getTotalElements();
        allFilms.getTotalPages();


        return ResponseEntity.ok( allFilms);
    }















    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        filmService.delete(id);
    }
}
