package com.nevena.controller;

import com.nevena.dto.ticket.TicketCreateDto;
import com.nevena.dto.ticket.TicketResponseDto;
import com.nevena.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @GetMapping
    public ResponseEntity<Page<TicketResponseDto>> list(Pageable pageable) {
        return ResponseEntity.ok(ticketService.list(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketResponseDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.get(id));
    }

    @GetMapping("/by-code/{code}")
    public ResponseEntity<TicketResponseDto> byCode(@PathVariable String code) {
        return ResponseEntity.ok(ticketService.getByPublicCode(code));
    }

    @PostMapping
    public ResponseEntity<TicketResponseDto> create(@RequestBody TicketCreateDto dto) {
        return ResponseEntity.ok(ticketService.create(dto));
    }
}
