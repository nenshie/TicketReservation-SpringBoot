package com.nevena.entities;

import com.nevena.entities.common.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Film")
public class Film extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "filmId", nullable = false)
    private Long filmId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Integer duration;

    @Column(length = 1024)
    private String posterUrl;

    @ManyToOne
    @JoinColumn(name = "genreId")
    private Genre genre;

    @Column(nullable = false)
    private boolean active = true;
}
