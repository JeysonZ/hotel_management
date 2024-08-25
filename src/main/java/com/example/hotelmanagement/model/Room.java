package com.example.hotelmanagement.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer number;
    private String type;
    private RoomStatus status = RoomStatus.AVAILABLE;
    private Double price;
}

