package com.nevena.entities;

import com.nevena.entities.common.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Projection")
public class Projection extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "projectionId", nullable = false)
    private Long projectionId;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "filmId", nullable = false)
    private Film film;

    @ManyToOne
    @JoinColumn(name = "roomId", nullable = false)
    private Room room;

    @Column(nullable = false)
    private boolean active = true;

    private LocalDateTime salesOpenUntil;

    @OneToMany(mappedBy = "projection")
    private List<Ticket> tickets = new ArrayList<>();

    @Column(nullable = true, precision = 10, scale = 2)
    private BigDecimal price;
}
