package com.nevena.mappers;

import com.nevena.dto.room.RoomResponseDto;
import com.nevena.entities.Room;
import com.nevena.mappers.config.CentralMapperConfig;
import org.mapstruct.Mapper;

@Mapper(config = CentralMapperConfig.class)
public interface RoomMapper {
    RoomResponseDto toDto(Room room);
}
