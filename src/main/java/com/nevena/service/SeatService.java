package com.nevena.service;

import com.nevena.dto.SeatDto;

import java.util.List;

public interface SeatService {

    List<SeatDto> getAllSeats();
    SeatDto getSeatById(Long id);
    SeatDto createSeat(SeatDto seatDto);
    SeatDto updateSeat(Long id, SeatDto seatDto);
    void deleteSeat(Long id);
}
