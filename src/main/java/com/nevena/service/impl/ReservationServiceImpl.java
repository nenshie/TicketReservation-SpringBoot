package com.nevena.service.impl;

import com.nevena.dto.reservation.MakeReservationDto;
import com.nevena.dto.reservation.QrRequest;
import com.nevena.dto.reservation.ReservationResponseDto;
import com.nevena.dto.seat.SeatDto;
import com.nevena.dto.ticket.TicketResponseDto;
import com.nevena.entities.*;
import com.nevena.entities.enums.ReservationStatus;
import com.nevena.mappers.ReservationMapper;
import com.nevena.repository.*;
import com.nevena.service.ReservationService;
import com.nevena.service.exception.BusinessException;
import com.nevena.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepo;
    private final ProjectionRepository projectionRepo;
    private final TicketRepository ticketRepo;
    private final UserRepository userRepo;
    private final SeatRepository seatRepo;
    private final ReservationMapper mapper;

    @Override
    @Transactional
    public ReservationResponseDto makeReservation(MakeReservationDto dto) {
        User user = userRepo.findByJmbg(dto.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found with JMBG: " + dto.getUserId()));

        Projection projection = projectionRepo.findById(dto.getProjectionId())
                .orElseThrow(() -> new NotFoundException("Projection not foseats = {ArrayList@14370}  size = 2und"));

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setStatus(ReservationStatus.RESERVED);
        reservation.setPaid(false);

        List<Ticket> tickets = new ArrayList<>();

        for (SeatDto seatDto : dto.getSeats()) {
            Seat seat = seatRepo.findById(Long.valueOf(seatDto.getId()))
                    .orElseThrow(() -> new NotFoundException("Seat not found: " + seatDto.getId()));

            boolean exists = ticketRepo.existsByProjection_ProjectionIdAndSeat_SeatId(projection.getProjectionId(), seat.getSeatId());
            if (exists) {
                throw new BusinessException("Seat already taken: row " + seat.getRowNumber() + ", number " + seat.getSeatNumber());
            }

            String qrContent = dto.getUserId() + "-" + projection.getProjectionId() + "-" + seat.getRowNumber() + "-" + seat.getSeatNumber();
            String qrCodeBase64 = QRCodeGenerator.generateQRCodeBase64(qrContent, 200);

            Ticket ticket = new Ticket();
            ticket.setProjection(projection);
            ticket.setSeat(seat);
            ticket.setStatus(ReservationStatus.RESERVED);
            ticket.setQrCode(qrCodeBase64);
            ticket.setReservation(reservation);
            ticket.setPrice(projection.getPrice());

            tickets.add(ticket);
        }

        reservation.setTickets(tickets);

        Reservation saved = reservationRepo.save(reservation);

        return ReservationResponseDto.builder()
                .reservationId(saved.getReservationId())
                .status(saved.getStatus())
                .paid(saved.isPaid())
                .tickets(saved.getTickets().stream().map(t -> TicketResponseDto.builder()
                        .ticketId(t.getTicketId())
                        .seatId(t.getSeat().getSeatId())
                        .seatRow(t.getSeat().getRowNumber())
                        .seatNumber(t.getSeat().getSeatNumber())
                        .projectionId(projection.getProjectionId())
                        .status(t.getStatus())
                        .build()).toList())
                .build();
    }

    @Override
    public List<ReservationResponseDto> getByUser(String userId) {
        List<Reservation> reservations = reservationRepo.findByUserId(userId);
        return reservations.stream()
                .map(mapper::toDto)
                .toList();    }


    @Override
    public ReservationResponseDto get(Long id) {
        return reservationRepo.findById(id).map(mapper::toDto)
                .orElseThrow(() -> new NotFoundException("Reservation not found"));
    }

    @Override
    public Page<ReservationResponseDto> list(Pageable pageable) {
        return reservationRepo.findAll(pageable).map(mapper::toDto);
    }

    @Transactional
    public boolean confirmReservationFromQr(QrRequest dto) {
        String[] parts = dto.getQrContent().split("-");
        if (parts.length != 4) {
            throw new IllegalArgumentException("QR code content is invalid.");
        }

        String jmbg;
        int projectionId, rowNumber, seatNumber;

        try {
            jmbg = parts[0];
            projectionId = Integer.parseInt(parts[1]);
            rowNumber = Integer.parseInt(parts[2]);
            seatNumber = Integer.parseInt(parts[3]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("QR code content format is incorrect.");
        }

        Ticket ticket = ticketRepo.findByProjectionProjectionIdAndSeatRowNumberAndSeatSeatNumber((long) projectionId, rowNumber, seatNumber)
                .orElseThrow(() -> new RuntimeException("Ticket not found."));

        Reservation reservation = reservationRepo.findByTicketIdAndUserJmbg(ticket.getTicketId(), jmbg)
                .orElseThrow(() -> new RuntimeException("Reservation not found."));

        reservation.setStatus(ReservationStatus.VERIFIED);
        reservationRepo.save(reservation);

        return true;
    }

}
