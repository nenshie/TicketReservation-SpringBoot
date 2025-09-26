package com.nevena.service;

import com.nevena.dto.payment.PaymentCreateDto;
import com.nevena.dto.payment.PaymentResponseDto;

public interface PaymentService {
    PaymentResponseDto initiate(PaymentCreateDto dto);
    PaymentResponseDto simulateSuccess(Long paymentId, String transactionId);
    PaymentResponseDto simulateFailure(Long paymentId, String errorCode, String errorMessage);
}
