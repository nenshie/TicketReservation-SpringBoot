package com.nevena.service.impl;

import com.nevena.dto.payment.PaymentCreateDto;
import com.nevena.dto.payment.PaymentResponseDto;
import com.nevena.entities.Payment;
import com.nevena.entities.Reservation;
import com.nevena.entities.Ticket;
import com.nevena.entities.enums.PaymentStatus;
import com.nevena.entities.enums.ReservationStatus;
import com.nevena.mappers.PaymentMapper;
import com.nevena.repository.PaymentRepository;
import com.nevena.repository.ReservationRepository;
import com.nevena.repository.TicketRepository;
import com.nevena.service.PaymentService;
import com.nevena.service.exception.BusinessException;
import com.nevena.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepo;
    private final ReservationRepository reservationRepo;
    private final TicketRepository ticketRepo;
    private final PaymentMapper mapper;

    @Override
    @Transactional
    public PaymentResponseDto initiate(PaymentCreateDto dto) {
        Reservation reservation = reservationRepo.findById(dto.getReservationId())
                .orElseThrow(() -> new NotFoundException("Reservation not found"));
        if (reservation.isPaid()) throw new BusinessException("Reservation already paid");

        Payment payment = mapper.toEntity(dto);
        payment.setStatus(PaymentStatus.INITIATED);
        payment.setCurrency("RSD");
        payment.setTransactionId(UUID.randomUUID().toString());
        payment.setProvider(dto.getProvider());
        payment.setAmount(dto.getAmount());

        return mapper.toDto(paymentRepo.save(payment));
    }

    @Override
    @Transactional
    public PaymentResponseDto simulateSuccess(Long paymentId, String transactionId) {
        Payment payment = paymentRepo.findById(paymentId)
                .orElseThrow(() -> new NotFoundException("Payment not found"));

        if (payment.getStatus() == PaymentStatus.SUCCESS) return mapper.toDto(payment);

        if (transactionId != null && !transactionId.isBlank()) {
            if (paymentRepo.existsByTransactionId(transactionId))
                throw new BusinessException("Duplicate transactionId");
            payment.setTransactionId(transactionId);
        }

        payment.setStatus(PaymentStatus.SUCCESS);
        payment.setPaidAt(LocalDateTime.now());

        Reservation reservation = payment.getReservation();
        reservation.setPaid(true);
        reservation.setPaidAt(payment.getPaidAt());
        reservation.setStatus(ReservationStatus.PAID);
        reservationRepo.save(reservation);

        Ticket ticket = reservation.getTicket();
        if (ticket != null) {
            ticket.setStatus(com.nevena.entities.enums.ReservationStatus.PAID);
            ticketRepo.save(ticket);
        }

        return mapper.toDto(paymentRepo.save(payment));
    }

    @Override
    @Transactional
    public PaymentResponseDto simulateFailure(Long paymentId, String errorCode, String errorMessage) {
        Payment payment = paymentRepo.findById(paymentId)
                .orElseThrow(() -> new NotFoundException("Payment not found"));

        if (payment.getStatus() == PaymentStatus.SUCCESS)
            throw new BusinessException("Cannot fail a successful payment");

        payment.setStatus(PaymentStatus.FAILED);
        payment.setErrorCode(errorCode);
        payment.setErrorMessage(errorMessage);
        return mapper.toDto(paymentRepo.save(payment));
    }
}

