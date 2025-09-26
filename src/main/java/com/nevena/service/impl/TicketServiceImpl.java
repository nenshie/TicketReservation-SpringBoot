package com.nevena.service.impl;

import com.nevena.dto.ticket.TicketCreateDto;
import com.nevena.dto.ticket.TicketResponseDto;
import com.nevena.entities.Ticket;
import com.nevena.entities.enums.ReservationStatus;
import com.nevena.mappers.TicketMapper;
import com.nevena.repository.TicketRepository;
import com.nevena.service.TicketService;
import com.nevena.service.exception.BusinessException;
import com.nevena.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
    private final TicketRepository repo;
    private final TicketMapper mapper;

    @Override
    @Transactional
    public TicketResponseDto create(TicketCreateDto dto) {
        if (repo.existsByProjection_ProjectionIdAndSeat_SeatId(dto.getProjectionId(), dto.getSeatId()))
            throw new BusinessException("Seat already taken for this projection");
        Ticket entity = mapper.toEntity(dto);
        entity.setStatus(ReservationStatus.CREATED);
        return mapper.toDto(repo.save(entity));
    }

    @Override
    public TicketResponseDto get(Long id) {
        return repo.findById(id).map(mapper::toDto)
                .orElseThrow(() -> new NotFoundException("Ticket not found"));
    }

    @Override
    public TicketResponseDto getByPublicCode(String publicCode) {
        return repo.findByPublicCode(publicCode).map(mapper::toDto)
                .orElseThrow(() -> new NotFoundException("Ticket not found"));
    }

    @Override
    public Page<TicketResponseDto> list(Pageable pageable) {
        return repo.findAll(pageable).map(mapper::toDto);
    }
}
