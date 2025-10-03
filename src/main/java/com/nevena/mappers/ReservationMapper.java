package com.nevena.mappers;

import com.nevena.dto.reservation.MakeReservationDto;
import com.nevena.dto.reservation.ReservationResponseDto;
import com.nevena.entities.Reservation;
import com.nevena.mappers.config.CentralMapperConfig;
import com.nevena.mappers.IdResolvers;
import org.mapstruct.*;

@Mapper(config = CentralMapperConfig.class, uses = {IdResolvers.class, TicketMapper.class})
public interface ReservationMapper {

    default com.nevena.entities.Ticket map(java.lang.Long id) {
        if (id == null) return null;
        com.nevena.entities.Ticket t = new com.nevena.entities.Ticket();
        t.setTicketId(id);
        return t;
    }


    // ticketId -> ticket stub
    @Mapping(target = "reservationId", ignore = true)
    @Mapping(target = "tickets", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "paid", ignore = true)
    @Mapping(target = "paidAt", ignore = true)
    @Mapping(target = "paymentProvider", ignore = true)
    @Mapping(target = "paymentTransactionId", ignore = true)
    Reservation toEntity(MakeReservationDto dto);

    // Flatten user/ticket IDs
    @Mapping(target = "userId", source = "user.userId")
    @Mapping(target = "tickets", source = "tickets")
    ReservationResponseDto toDto(Reservation entity);
}
