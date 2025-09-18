package com.nevena.service.impl;

import com.nevena.dto.SeatDto;
import com.nevena.entities.Room;
import com.nevena.entities.Seat;
import com.nevena.mappers.RoomMapper;
import com.nevena.mappers.SeatMapper;
import com.nevena.repository.SeatRepository;
import com.nevena.service.SeatService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository;
    private final SeatMapper seatMapper = SeatMapper.INSTANCE;
    private final RoomMapper roomMapper = RoomMapper.INSTANCE;

    public SeatServiceImpl(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    @Override
    public List<SeatDto> getAllSeats() {
        return seatRepository.findAll()
                .stream()
                .map(seatMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public SeatDto getSeatById(Long id) {
        return seatRepository.findById(id)
                .map(seatMapper::toDto)
                .orElse(null);
    }

    @Override
    public SeatDto createSeat(SeatDto seatDto) {
        Seat seat = seatMapper.toEntity(seatDto);
        return seatMapper.toDto(seatRepository.save(seat));
    }

    @Override
    public SeatDto updateSeat(Long id, SeatDto seatDto) {
        return seatRepository.findById(id)
                .map(existing -> {
                    existing.setSeatNumber(seatDto.getSeatNumber());
                    existing.setRowNumber(seatDto.getRowNumber());

                    Room roomEntity = roomMapper.toEntity(seatDto.getRoom());
                    existing.setRoom(roomEntity);

                    return seatMapper.toDto(seatRepository.save(existing));
                }).orElse(null);
    }

    @Override
    public void deleteSeat(Long id) {
        seatRepository.deleteById(id);
    }
}
