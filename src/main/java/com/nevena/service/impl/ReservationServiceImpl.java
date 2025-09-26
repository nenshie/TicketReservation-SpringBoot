package com.nevena.service.impl;

import com.nevena.dto.reservation.ReservationCreateDto;
import com.nevena.dto.reservation.ReservationResponseDto;
import com.nevena.entities.Reservation;
import com.nevena.entities.Ticket;
import com.nevena.entities.User;
import com.nevena.entities.enums.ReservationStatus;
import com.nevena.mappers.ReservationMapper;
import com.nevena.repository.ReservationRepository;
import com.nevena.repository.TicketRepository;
import com.nevena.repository.UserRepository;
import com.nevena.service.ReservationService;
import com.nevena.service.exception.BusinessException;
import com.nevena.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepo;
    private final TicketRepository ticketRepo;
    private final UserRepository userRepo;
    private final ReservationMapper mapper;

    @Override
    @Transactional
    public ReservationResponseDto create(Long userId, ReservationCreateDto dto) {
        Ticket ticket = ticketRepo.findById(dto.getTicketId())
                .orElseThrow(() -> new NotFoundException("Ticket not found"));

        if (ticket.getStatus() != ReservationStatus.CREATED && ticket.getStatus() != ReservationStatus.RESERVED)
            throw new BusinessException("Ticket not available for reservation");

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        Reservation entity = mapper.toEntity(dto);
        entity.setUser(user);
        entity.setStatus(ReservationStatus.RESERVED);

        ticket.setStatus(ReservationStatus.RESERVED);
        ticketRepo.save(ticket);

        return mapper.toDto(reservationRepo.save(entity));
    }

    @Override
    public ReservationResponseDto get(Long id) {
        return reservationRepo.findById(id).map(mapper::toDto)
                .orElseThrow(() -> new NotFoundException("Reservation not found"));
    }

    @Override
    public Page<ReservationResponseDto> list(Pageable pageable) {
        return reservationRepo.findAll(pageable).map(mapper::toDto);
    }
}
