package com.nevena.controller;

import com.nevena.dto.reservation.ReservationCreateDto;
import com.nevena.dto.reservation.ReservationResponseDto;
import com.nevena.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService service;

    // userId tipično dolazi iz SecurityContext-a; ovde eksplicitno za jednostavnost
    @PostMapping("/users/{userId}")
    public ResponseEntity<ReservationResponseDto> create(@PathVariable Long userId,
                                                         @RequestBody ReservationCreateDto dto) {
        return ResponseEntity.ok(service.create(userId, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponseDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.get(id));
    }

    @GetMapping
    public ResponseEntity<Page<ReservationResponseDto>> list(Pageable pageable) {
        return ResponseEntity.ok(service.list(pageable));
    }
}
