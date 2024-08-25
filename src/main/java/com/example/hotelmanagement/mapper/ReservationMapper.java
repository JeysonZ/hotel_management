package com.example.hotelmanagement.mapper;

import com.example.hotelmanagement.dto.ReservationDTO;
import com.example.hotelmanagement.model.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
    ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);
    ReservationDTO toDTO(Reservation reservation);
    Reservation toEntity(ReservationDTO reservationDTO);
}


