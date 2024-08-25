package com.example.hotelmanagement.repository;

import com.example.hotelmanagement.model.Invoice;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InvoiceRepository extends MongoRepository<Invoice, String> {
}
