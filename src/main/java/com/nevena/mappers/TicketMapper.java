package com.nevena.mappers;

import com.nevena.dto.ticket.TicketCreateDto;
import com.nevena.dto.ticket.TicketResponseDto;
import com.nevena.entities.Ticket;
import com.nevena.mappers.IdResolvers;
import com.nevena.mappers.config.CentralMapperConfig;
import org.mapstruct.*;

@Mapper(config = CentralMapperConfig.class, uses = {IdResolvers.class})
public interface TicketMapper {

    default com.nevena.entities.Projection map(java.lang.Long id) {
        if (id == null) return null;
        com.nevena.entities.Projection p = new com.nevena.entities.Projection();
        p.setProjectionId(id);
        return p;
    }

    default com.nevena.entities.Seat mapSeat(java.lang.Long id) {
        if (id == null) return null;
        com.nevena.entities.Seat s = new com.nevena.entities.Seat();
        s.setSeatId(id);
        return s;
    }


    // Create: IDs -> reference entiteti
    @Mapping(target = "ticketId", ignore = true)
    @Mapping(target = "projection", source = "projectionId")
    @Mapping(target = "seat", source = "seatId")
    @Mapping(target = "status", ignore = true) // set u servisu
    @Mapping(target = "qrCode", ignore = true)
    @Mapping(target = "verifiedAt", ignore = true)
    @Mapping(target = "verifiedByUserId", ignore = true)
    @Mapping(target = "version", ignore = true)
    Ticket toEntity(TicketCreateDto dto);

    // Response: obogaćeni podaci
    @Mapping(target = "projectionId", source = "projection.projectionId")
    @Mapping(target = "filmTitle", source = "projection.film.title")
    @Mapping(target = "roomId", source = "projection.room.roomId")
    @Mapping(target = "roomName", source = "projection.room.name")
    @Mapping(target = "seatId", source = "seat.seatId")
    @Mapping(target = "seatRow", source = "seat.rowNumber")
    @Mapping(target = "seatNumber", source = "seat.seatNumber")
    TicketResponseDto toDto(Ticket entity);
}
