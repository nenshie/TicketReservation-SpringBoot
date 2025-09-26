package com.nevena.controller;

import com.nevena.dto.payment.PaymentCreateDto;
import com.nevena.dto.payment.PaymentResponseDto;
import com.nevena.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService service;

    // Start a payment attempt
    @PostMapping("/initiate")
    public ResponseEntity<PaymentResponseDto> initiate(@RequestBody PaymentCreateDto dto) {
        return ResponseEntity.ok(service.initiate(dto));
    }

    // Simulate success from gateway (optionally overriding tx id)
    @PostMapping("/{id}/success")
    public ResponseEntity<PaymentResponseDto> success(@PathVariable Long id,
                                                      @RequestParam(required = false) String txId) {
        return ResponseEntity.ok(service.simulateSuccess(id, txId));
    }

    // Simulate failure from gateway
    @PostMapping("/{id}/fail")
    public ResponseEntity<PaymentResponseDto> fail(@PathVariable Long id,
                                                   @RequestParam String code,
                                                   @RequestParam String message) {
        return ResponseEntity.ok(service.simulateFailure(id, code, message));
    }
}
