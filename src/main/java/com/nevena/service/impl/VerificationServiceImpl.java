package com.nevena.service.impl;

import com.nevena.entities.Ticket;
import com.nevena.entities.enums.ReservationStatus;
import com.nevena.repository.TicketRepository;
import com.nevena.service.VerificationService;
import com.nevena.service.exception.BusinessException;
import com.nevena.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class VerificationServiceImpl implements VerificationService {
    private final TicketRepository ticketRepo;

    @Override
    @Transactional
    public String verifyByPublicCode(String publicCode, Long adminUserId) {
        Ticket ticket = ticketRepo.findByPublicCode(publicCode)
                .orElseThrow(() -> new NotFoundException("Ticket not found"));

        if (ticket.getStatus() != ReservationStatus.PAID && ticket.getStatus() != ReservationStatus.VERIFIED)
            throw new BusinessException("Ticket not paid");

        if (ticket.getStatus() == ReservationStatus.VERIFIED || ticket.getStatus() == ReservationStatus.USED) {
            return "Already verified at " + ticket.getVerifiedAt();
        }

        ticket.setStatus(ReservationStatus.VERIFIED);
        ticket.setVerifiedAt(LocalDateTime.now());
        ticket.setVerifiedByUserId(adminUserId);
        ticketRepo.save(ticket);
        return "Verified OK at " + ticket.getVerifiedAt();
    }
}
