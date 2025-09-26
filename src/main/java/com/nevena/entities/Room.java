package com.nevena.entities;

import com.nevena.entities.common.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Room")
public class Room extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roomId", nullable = false)
    private Long roomId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer numberOfRows;

    @Column(nullable = false)
    private Integer seatsPerRow;
}
