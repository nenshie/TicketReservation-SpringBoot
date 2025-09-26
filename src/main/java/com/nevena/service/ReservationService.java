package com.nevena.service;

import com.nevena.dto.reservation.ReservationCreateDto;
import com.nevena.dto.reservation.ReservationResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReservationService {
    ReservationResponseDto create(Long userId, ReservationCreateDto dto);
    ReservationResponseDto get(Long id);
    Page<ReservationResponseDto> list(Pageable pageable);
}
