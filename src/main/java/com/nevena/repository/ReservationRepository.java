package com.nevena.repository;

import com.nevena.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("""
        select r from Reservation r
            join fetch r.user u 
            join fetch r.tickets t
            left join fetch t.seat s
            join fetch t.projection p
            join fetch p.film
            where u.jmbg = :userId
    """)
    List<Reservation> findByUserId(@Param("userId") String userId);
    @Query("""
    SELECT r FROM Reservation r 
    JOIN r.tickets t 
    WHERE t.ticketId = :ticketId AND r.user.jmbg = :jmbg
""")
    Optional<Reservation> findByTicketIdAndUserJmbg(@Param("ticketId") Long ticketId, @Param("jmbg") String jmbg);

}
