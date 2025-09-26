package com.nevena.service.impl;

import com.nevena.dto.room.RoomResponseDto;
import com.nevena.entities.Room;
import com.nevena.mappers.RoomMapper;
import com.nevena.repository.RoomRepository;
import com.nevena.service.RoomService;
import com.nevena.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository repo;
    private final RoomMapper mapper;

    @Override
    public RoomResponseDto get(Long id) {
        return repo.findById(id).map(mapper::toDto)
                .orElseThrow(() -> new NotFoundException("Room not found"));
    }

    @Override
    public Page<RoomResponseDto> list(Pageable pageable) {
        return repo.findAll(pageable).map(mapper::toDto);
    }
}
