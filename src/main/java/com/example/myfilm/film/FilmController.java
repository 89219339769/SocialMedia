package com.example.myfilm.film;


import com.example.myfilm.film.model.FilmDto;
//import com.example.myfilm.rate.Rate;
//import com.example.myfilm.rate.RateService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.myfilm.film.model.Film;


import javax.xml.bind.annotation.XmlType;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/films")
public class FilmController {

    private final FilmService filmService;

   // private final RateService rateService;



    @PostMapping
    public Film create(@RequestBody Film film) {
        return filmService.save(film);
    }


//    @PostMapping("/{filmId}/rate")
//    public Rate add(@RequestHeader("X-Sharer-User-Id") Long userId,
//                    @PathVariable Long filmId,
//                    @RequestBody Rate rate) {
//        return rateService.addRate(userId, filmId, rate);
//    }


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
