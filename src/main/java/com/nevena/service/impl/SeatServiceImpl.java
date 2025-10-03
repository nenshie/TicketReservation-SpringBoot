package com.nevena.service.impl;

import com.nevena.dto.seat.*;
import com.nevena.entities.Seat;
import com.nevena.mappers.SeatMapper;
import com.nevena.repository.SeatRepository;
import com.nevena.service.SeatService;
import com.nevena.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class SeatServiceImpl implements SeatService {
    private final SeatRepository repo;
    private final SeatMapper mapper;


    @Override public SeatResponseDto get(Long id) {
        return repo.findById(id).map(mapper::toDto)
                .orElseThrow(() -> new NotFoundException("Seat not found"));
    }
    @Override public Page<SeatResponseDto> list(Pageable p) { return repo.findAll(p).map(mapper::toDto); }

    @Override
    public OccupiedSeatDto getSeatById(Long id) {
        return null;
    }

    @Override
    public List<OccupiedSeatDto> getSeatsWithAvailability(Long projectionId) {
        return repo.getSeatsWithAvailability(projectionId)
                .stream()
                .map(v -> OccupiedSeatDto.builder()
                        .seatId(v.getSeatId())
                        .rowNumber(v.getRowNumber())
                        .seatNumber(v.getSeatNumber())
                        .occupied(v.getIsTaken())
                        .build())
                .toList();
    }
}
