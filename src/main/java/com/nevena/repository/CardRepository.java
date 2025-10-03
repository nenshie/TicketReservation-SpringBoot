package com.nevena.repository;

import com.nevena.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    Optional<Card> findByCardNumberAndExpiryDateAndCvv(
            String cardNumber,
            String expiryDate,
            String cvv
    );
}
