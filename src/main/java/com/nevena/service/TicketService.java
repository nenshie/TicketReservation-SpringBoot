package com.nevena.service;

import com.nevena.dto.ticket.TicketCreateDto;
import com.nevena.dto.ticket.TicketResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TicketService {
    TicketResponseDto create(TicketCreateDto dto);
    TicketResponseDto get(Long id);
    TicketResponseDto getByPublicCode(String publicCode);
    Page<TicketResponseDto> list(Pageable pageable);
}
