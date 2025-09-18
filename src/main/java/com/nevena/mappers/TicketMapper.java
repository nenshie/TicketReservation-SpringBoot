package com.nevena.mappers;


import com.nevena.entities.Ticket;
import com.nevena.dto.TicketDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TicketMapper {

    @Mapping(source = "seat.seatId", target = "seatId")
    @Mapping(source = "projection.projectionId", target = "projectionId")
    TicketDto toDTO(Ticket ticket);

    @Mapping(source = "seatId", target = "seat.seatId")
    @Mapping(source = "projectionId", target = "projection.projectionId")
    Ticket toEntity(TicketDto dto);
}