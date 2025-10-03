package com.nevena.repository;

import com.nevena.entities.Seat;
import com.nevena.repository.views.OccupiedSeatView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {

    @Query(value = """
        SELECT 
            s.seat_id AS seatId,
            s.row_number AS rowNumber,
            s.seat_number AS seatNumber,
            CASE 
                WHEN EXISTS (
                    SELECT 1 
                    FROM ticket t 
                    WHERE t.seat_id = s.seat_id 
                      AND t.projection_id = :projectionId
                ) 
                THEN CAST(1 AS BIT) 
                ELSE CAST(0 AS BIT) 
            END AS isTaken
        FROM seat s
        WHERE s.room_id = (
            SELECT p.room_id 
            FROM projection p 
            WHERE p.projection_id = :projectionId
        )
        """, nativeQuery = true)
    List<OccupiedSeatView> getSeatsWithAvailability(Long projectionId);
}
