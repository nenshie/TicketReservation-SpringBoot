package com.nevena.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Card")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;

    @Column(nullable = false, unique = true, length = 16)
    private String cardNumber;

    @Column(nullable = false, length = 5)
    private String expiryDate; // MM/YY

    @Column(nullable = false, length = 3)
    private String cvv;

    @Column(nullable = false)
    private Double balance;

}
