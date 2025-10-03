package com.nevena.repository;

import com.nevena.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Optional<Ticket> findByPublicCode(String publicCode);
    boolean existsByProjection_ProjectionIdAndSeat_SeatId(Long projectionId, Long seatId);
    Optional<Ticket> findByProjectionProjectionIdAndSeatRowNumberAndSeatSeatNumber(Long projectionId, int rowNumber, int seatNumber);
}
