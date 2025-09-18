package com.nevena.mappers;

import com.nevena.dto.SeatDto;
import com.nevena.entities.Seat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {RoomMapper.class})
public interface SeatMapper {

    SeatMapper INSTANCE = Mappers.getMapper(SeatMapper.class);


    @Mapping(source = "room", target = "room")
    SeatDto toDto(Seat seat);

    @Mapping(source = "room", target = "room")
    Seat toEntity(SeatDto seatDto);
}
