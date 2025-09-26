package com.nevena.controller;

import com.nevena.dto.film.FilmCreateDto;
import com.nevena.dto.film.FilmResponseDto;
import com.nevena.dto.film.FilmUpdateDto;
import com.nevena.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/films")
@RequiredArgsConstructor
public class FilmController {

    private final FilmService filmService;

    @GetMapping
    public ResponseEntity<Page<FilmResponseDto>> getAllFilms(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending,
            @RequestParam(required = false) String filterBy,
            @RequestParam(required = false) String filterValue
    ) {
        Pageable pageable = PageRequest.of(
                page,
                size,
                ascending ? Sort.Direction.ASC : Sort.Direction.DESC,
                sortBy
        );

        Page<FilmResponseDto> films = filmService.list(pageable, filterBy, filterValue);
        return ResponseEntity.ok(films);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmResponseDto> getFilmById(@PathVariable Long id) {
        return ResponseEntity.ok(filmService.get(id));
    }

    @PostMapping
    public ResponseEntity<FilmResponseDto> createFilm(@RequestBody FilmCreateDto dto) {
        return ResponseEntity.ok(filmService.create(dto));
    }

    @PutMapping
    public ResponseEntity<FilmResponseDto> updateFilm(@RequestBody FilmUpdateDto dto) {
        return ResponseEntity.ok(filmService.update(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFilm(@PathVariable Long id) {
        filmService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
