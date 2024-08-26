package com.example.hotelmanagement.mapper;

import com.example.hotelmanagement.dto.ClientDTO;
import com.example.hotelmanagement.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientDTO toDTO(Client client);
    Client toEntity(ClientDTO clientDTO);
}


