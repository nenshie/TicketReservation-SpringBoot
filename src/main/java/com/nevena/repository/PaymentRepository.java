package com.nevena.repository;

import com.nevena.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    boolean existsByTransactionId(String transactionId);
}
