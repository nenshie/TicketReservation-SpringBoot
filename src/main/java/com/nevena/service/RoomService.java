package com.nevena.service;

import com.nevena.dto.room.RoomResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoomService {
    RoomResponseDto get(Long id);
    Page<RoomResponseDto> list(Pageable pageable);
}
