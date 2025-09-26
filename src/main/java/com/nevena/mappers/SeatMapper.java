package com.nevena.mappers;

import com.nevena.dto.seat.SeatResponseDto;
import com.nevena.entities.Seat;
import com.nevena.mappers.config.CentralMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = CentralMapperConfig.class)
public interface SeatMapper {
    @Mapping(target = "roomId", source = "room.roomId")
    @Mapping(target = "roomName", source = "room.name")
    SeatResponseDto toDto(Seat entity);
}
