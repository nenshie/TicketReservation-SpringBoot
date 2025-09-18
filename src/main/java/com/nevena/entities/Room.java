package com.nevena.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roomId", nullable = false)
    private Long roomId;

    private String name;
    private Integer numberOfRows;
    private Integer seatsPerRow;

    @OneToMany(mappedBy = "room")
    private List<Seat> seats = new ArrayList<>();

}