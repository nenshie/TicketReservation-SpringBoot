package com.nevena.controller;

import com.nevena.dto.payment.PaymentRequestDto;
import com.nevena.dto.reservation.MakeReservationDto;
import com.nevena.dto.reservation.QrRequest;
import com.nevena.dto.reservation.ReservationResponseDto;
import com.nevena.service.PaymentService;
import com.nevena.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService service;
    private final PaymentService paymentService;

    @PostMapping("/make")
    public ResponseEntity<ReservationResponseDto> makeReservation(@RequestBody MakeReservationDto reservationRequest) {
        if (reservationRequest == null || reservationRequest.getSeats() == null || reservationRequest.getSeats().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        try {
            ReservationResponseDto savedReservation = service.makeReservation(reservationRequest);
            return ResponseEntity.ok(savedReservation);
        } catch (Exception ex) {
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping("/{reservationId}/pay")
    public ResponseEntity<String> payReservation(
            @PathVariable Long reservationId,
            @RequestBody PaymentRequestDto dto) {
        try {
            paymentService.payForReservation(reservationId, dto);
            return ResponseEntity.ok("Payment successful!");
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Payment failed: " + ex.getMessage());
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReservationResponseDto>> getReservationsByUser(@PathVariable String userId) {
        try {
            List<ReservationResponseDto> reservations = service.getByUser(userId);

            if (reservations == null || reservations.isEmpty()) {
                return ResponseEntity.ok(Collections.emptyList());
            }

            return ResponseEntity.ok(reservations);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.emptyList());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponseDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.get(id));
    }

    @GetMapping
    public ResponseEntity<Page<ReservationResponseDto>> list(Pageable pageable) {
        return ResponseEntity.ok(service.list(pageable));
    }


    @PostMapping("/confirm-from-qr")
    public ResponseEntity<?> confirmFromQr(@RequestBody QrRequest qr) {
        try {
            boolean success = service.confirmReservationFromQr(qr);

            if (success) {
                return ResponseEntity.ok(Collections.singletonMap("success", true));
            } else {
                return ResponseEntity.status(404).body(Collections.singletonMap("message", "Reservation not found"));
            }
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(500).body(Collections.singletonMap("message", "Error confirming reservation: " + ex.getMessage()));
        }
    }
}
