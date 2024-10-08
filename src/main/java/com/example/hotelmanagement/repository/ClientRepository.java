package com.example.hotelmanagement.repository;

import com.example.hotelmanagement.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ClientRepository extends JpaRepository<Client, Long> {}

