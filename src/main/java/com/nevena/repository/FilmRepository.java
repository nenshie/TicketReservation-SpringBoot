package com.nevena.repository;

import com.nevena.entities.Film;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
    Page<Film> findByGenreNameIgnoreCase(String genreName, Pageable pageable);
    Page<Film> findByTitleContainingIgnoreCase(String title, Pageable pageable);
}
