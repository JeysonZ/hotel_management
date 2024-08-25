package com.example.hotelmanagement.dto;

import com.example.hotelmanagement.model.RoomStatus;
import lombok.Data;

@Data
public class RoomDTO {
    private Long id;
    private String number;
    private String type;
    private RoomStatus status;
    private Double price;
}


