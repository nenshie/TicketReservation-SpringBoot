package com.nevena.mappers;

import com.nevena.entities.Room;
import com.nevena.dto.RoomDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoomMapper {
    RoomMapper INSTANCE = Mappers.getMapper(RoomMapper.class);

    RoomDto toDto(Room room);

    Room toEntity(RoomDto roomDto);
}
