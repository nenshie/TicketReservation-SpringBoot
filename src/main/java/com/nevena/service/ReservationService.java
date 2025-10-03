package com.nevena.service;

import com.nevena.dto.reservation.MakeReservationDto;
import com.nevena.dto.reservation.QrRequest;
import com.nevena.dto.reservation.ReservationResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReservationService {
    ReservationResponseDto makeReservation(MakeReservationDto dto);
    List<ReservationResponseDto> getByUser(String userId);
    ReservationResponseDto get(Long id);
    Page<ReservationResponseDto> list(Pageable pageable);
    boolean confirmReservationFromQr(QrRequest qrContent);
}
