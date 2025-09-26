package com.nevena.entities;

import com.nevena.entities.common.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Genre")
@Getter
@Setter
public class Genre extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genreId", nullable = false)
    private Long genreId;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "genre")
    private List<Film> films = new ArrayList<>();
}
