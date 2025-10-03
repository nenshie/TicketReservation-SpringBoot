package com.nevena.controller;

import com.nevena.dto.seat.OccupiedSeatDto;
import com.nevena.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seats")
@RequiredArgsConstructor
public class SeatController {

    private final SeatService seatService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getSeatById(@PathVariable Long id) {
        var seatDto = seatService.getSeatById(id);
        return seatDto != null ? ResponseEntity.ok(seatDto) : ResponseEntity.notFound().build();
    }

    @GetMapping("/availability")
    public ResponseEntity<List<OccupiedSeatDto>> getSeatsWithAvailability(@RequestParam Long projectionId) {
        List<OccupiedSeatDto> seats = seatService.getSeatsWithAvailability(projectionId);
        return ResponseEntity.ok(seats);
    }
}
