package com.nevena.service;

import com.nevena.dto.room.RoomResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoomService {
    RoomResponseDto get(Long id);
    List<RoomResponseDto> list();
}
