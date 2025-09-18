package com.nevena.service;

import com.nevena.entities.Payment;

import java.util.List;

public interface PaymentService {

    List<Payment> getAllPayments();
    Payment getPaymentById(Long id);
    Payment savePayment(Payment payment);
    void deletePayment(Long id);
}
