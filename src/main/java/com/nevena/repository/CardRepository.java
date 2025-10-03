package com.nevena.repository;

import com.nevena.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Long> {

    Optional<Card> findByCardNumberAndExpiryDateAndCvv(
            String cardNumber,
            String expiryDate,
            String cvv
    );
}
