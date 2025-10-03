package com.nevena.service;

import com.nevena.dto.seat.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SeatService {

    SeatResponseDto get(Long id);
    Page<SeatResponseDto> list(Pageable pageable);
    OccupiedSeatDto getSeatById(Long id);
    List<OccupiedSeatDto> getSeatsWithAvailability(Long projectionId);

}
