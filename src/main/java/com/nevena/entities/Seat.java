package com.nevena.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Seat")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seatId", nullable = false)
    private Long seatId;

    @Column(nullable = false)
    private Integer seatNumber;

    @Column(nullable = false)
    private Integer rowNumber;

    @ManyToOne
    @JoinColumn(name = "roomId", nullable = false)
    private Room room;

}