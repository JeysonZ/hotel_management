package com.example.hotelmanagement.mapper;

import com.example.hotelmanagement.dto.RoomDTO;
import com.example.hotelmanagement.model.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RoomMapper {
    RoomMapper INSTANCE = Mappers.getMapper(RoomMapper.class);
    RoomDTO toDTO(Room room);
    Room toEntity(RoomDTO roomDTO);
}


