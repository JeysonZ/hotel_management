package com.example.hotelmanagement.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ReservationDTO {
    private String id;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
    private Long clientId;
    private Long roomId;
}


