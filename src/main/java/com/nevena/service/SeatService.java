package com.nevena.service;

import com.nevena.dto.seat.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SeatService {

    SeatResponseDto get(Long id);
    void delete(Long id);
    Page<SeatResponseDto> list(Pageable pageable);
}
