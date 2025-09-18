package com.nevena.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Film")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "filmId", nullable = false)
    private Long filmId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Integer duration;
    private String posterUrl;

    @ManyToOne
    @JoinColumn(name = "genreId")
    private Genre genre;

}