package com.nevena.mappers;

import com.nevena.entities.Reservation;
import com.nevena.dto.ReservationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    @Mapping(source = "user.userId", target = "userId")
    @Mapping(source = "ticket.ticketId", target = "ticketId")
    ReservationDto toDTO(Reservation reservation);

    @Mapping(source = "userId", target = "user.userId")
    @Mapping(source = "ticketId", target = "ticket.ticketId")
    Reservation toEntity(ReservationDto dto);
}