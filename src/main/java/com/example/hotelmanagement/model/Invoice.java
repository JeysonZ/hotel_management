package com.example.hotelmanagement.model;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "invoices")
public class Invoice {
    @Id
    private String id;
    private Long clientId;
    private String fullName;
    private String phoneNumber;
    private Long roomId;
    private String type;
    private Double amount;
    private LocalDateTime issuedAt;
}
