package com.example.hotelmanagement.service;

import com.example.hotelmanagement.model.Invoice;
import com.example.hotelmanagement.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    public void generateInvoice(Long clientId, String firstName, String lastName, String phoneNumber, Long roomId, 
                                String type, Double amount) {
        Invoice invoice = new Invoice();
        invoice.setClientId(clientId);
        invoice.setFullName(firstName + " " + lastName);
        invoice.setPhoneNumber(phoneNumber);
        invoice.setRoomId(roomId);
        invoice.setType(type);
        invoice.setAmount(amount);
        invoice.setIssuedAt(LocalDateTime.now());
        invoiceRepository.save(invoice);
    }
}
