package com.nevena.service.impl;

import com.nevena.dto.RoomDto;
import com.nevena.entities.Room;
import com.nevena.mappers.RoomMapper;
import com.nevena.repository.RoomRepository;
import com.nevena.service.RoomService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    public RoomServiceImpl(RoomRepository roomRepository, RoomMapper roomMapper) {
        this.roomRepository = roomRepository;
        this.roomMapper = roomMapper;
    }

    @Override
    public List<RoomDto> getAllRooms() {
        return roomRepository.findAll().stream()
                .map(roomMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public RoomDto getRoomById(Long id) {
        return roomRepository.findById(id)
                .map(roomMapper::toDto)
                .orElse(null);
    }

    @Override
    public RoomDto createRoom(RoomDto roomDto) {
        Room room = roomMapper.toEntity(roomDto);
        return roomMapper.toDto(roomRepository.save(room));
    }

    @Override
    public RoomDto updateRoom(Long id, RoomDto roomDto) {
        return roomRepository.findById(id)
                .map(existing -> {
                    existing.setName(roomDto.getName());
                    existing.setNumberOfRows(roomDto.getNumberOfRows());
                    existing.setSeatsPerRow(roomDto.getSeatsPerRow());
                    return roomMapper.toDto(roomRepository.save(existing));
                })
                .orElse(null);
    }

    @Override
    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }
}
