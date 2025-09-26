package com.nevena.mappers;

import com.nevena.dto.reservation.ReservationCreateDto;
import com.nevena.dto.reservation.ReservationResponseDto;
import com.nevena.entities.Reservation;
import com.nevena.mappers.config.CentralMapperConfig;
import com.nevena.mappers.IdResolvers;
import org.mapstruct.*;

@Mapper(config = CentralMapperConfig.class, uses = {IdResolvers.class})
public interface ReservationMapper {

    default com.nevena.entities.Ticket map(java.lang.Long id) {
        if (id == null) return null;
        com.nevena.entities.Ticket t = new com.nevena.entities.Ticket();
        t.setTicketId(id);
        return t;
    }


    // ticketId -> ticket stub
    @Mapping(target = "reservationId", ignore = true)
    @Mapping(target = "ticket", source = "ticketId")
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "paid", ignore = true)
    @Mapping(target = "paidAt", ignore = true)
    @Mapping(target = "paymentProvider", ignore = true)
    @Mapping(target = "paymentTransactionId", ignore = true)
    Reservation toEntity(ReservationCreateDto dto);

    // Flatten user/ticket IDs
    @Mapping(target = "userId", source = "user.userId")
    @Mapping(target = "ticketId", source = "ticket.ticketId")
    ReservationResponseDto toDto(Reservation entity);
}
