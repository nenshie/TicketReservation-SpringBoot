package com.nevena.service.impl;

import com.nevena.dto.payment.PaymentCreateDto;
import com.nevena.dto.payment.PaymentRequestDto;
import com.nevena.dto.payment.PaymentResponseDto;
import com.nevena.entities.*;
import com.nevena.entities.enums.PaymentStatus;
import com.nevena.entities.enums.ReservationStatus;
import com.nevena.mappers.PaymentMapper;
import com.nevena.repository.*;
import com.nevena.service.PaymentService;
import com.nevena.service.exception.BusinessException;
import com.nevena.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepo;
    private final ReservationRepository reservationRepo;
    private final TicketRepository ticketRepo;
    private final PaymentMapper mapper;

    private final CardRepository cardRepository;
    private final ReservationRepository reservationRepository;
    private final CinemaAccountRepository cinemaAccountRepository;

    @Override
    @Transactional
    public void payForReservation(Long reservationId, PaymentRequestDto dto) {
        // 1. find the card
        Card card = cardRepository.findByCardNumberAndExpiryDateAndCvv(
                dto.getCardNumber(),
                dto.getExpiryDate(),
                dto.getCvv()
        ).orElseThrow(() -> new RuntimeException("Invalid card"));

        // 2. find the reservation
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

    // 3. calculate the sum
        BigDecimal totalPrice = reservation.getTickets().stream()
                .map(t -> t.getPrice() != null ? t.getPrice() : BigDecimal.ZERO)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    // 4. check the balance of the card
        BigDecimal cardBalanceBD = card.getBalance() != null
                ? BigDecimal.valueOf(card.getBalance())
                : BigDecimal.ZERO;

        if (cardBalanceBD.compareTo(totalPrice) < 0) {
            throw new RuntimeException("Insufficient balance");
        }

    // 5. remove the amount of the price from the card
        card.setBalance((double) cardBalanceBD.subtract(totalPrice).floatValue());
        cardRepository.save(card);

    // 6. add money to the cinema card
        CinemaAccount cinemaAccount = cinemaAccountRepository.findFirstByOrderByIdAsc()
                .orElseThrow(() -> new RuntimeException("Cinema account not found"));

        BigDecimal cinemaBalanceBD = cinemaAccount.getBalance() != null
                ? cinemaAccount.getBalance()
                : BigDecimal.ZERO;

        cinemaAccount.setBalance(cinemaBalanceBD.add(totalPrice));
        cinemaAccountRepository.save(cinemaAccount);

        // 7. update reservation and ticket status
        reservation.setStatus(ReservationStatus.PAID);
        reservation.getTickets().forEach(t -> t.setStatus(ReservationStatus.PAID));
        reservationRepository.save(reservation);
    }


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

//        List<Ticket> ticket = reservation.getTickets();
//        if (ticket != null) {
//            ticket.setStatus(com.nevena.entities.enums.ReservationStatus.PAID);
//            ticketRepo.save(ticket);
//        }

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

