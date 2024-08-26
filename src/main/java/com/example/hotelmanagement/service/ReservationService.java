package com.example.hotelmanagement.service;

import com.example.hotelmanagement.dto.ReservationDTO;
import com.example.hotelmanagement.mapper.ReservationMapper;
import com.example.hotelmanagement.model.Client;
import com.example.hotelmanagement.model.Reservation;
import com.example.hotelmanagement.model.Room;
import com.example.hotelmanagement.model.RoomStatus;
import com.example.hotelmanagement.repository.ClientRepository;
import com.example.hotelmanagement.repository.ReservationRepository;
import com.example.hotelmanagement.repository.RoomRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ReservationMapper reservationMapper;

    @Autowired
    private InvoiceService invoiceService;

    public Reservation createReservation(ReservationDTO reservationDto) {
        Client client = clientRepository.findById(reservationDto.getClientId()).orElseThrow();
        Room room = roomRepository.findById(reservationDto.getRoomId()).orElseThrow();

        if (room.getStatus() != RoomStatus.AVAILABLE) {
            throw new IllegalStateException("Room is not available");
        }

        room.setStatus(RoomStatus.RESERVED);
        roomRepository.save(room);

        Reservation reservation = new Reservation();
        reservation.setCheckInTime(reservationDto.getCheckInTime());
        reservation.setCheckOutTime(reservationDto.getCheckOutTime());
        reservation.setClient(client);
        reservation.setRoom(room);

        invoiceService.generateInvoice(client.getId(), client.getFirstName(), client.getLastName(),
                client.getPhoneNumber(), room.getId(), room.getType(), room.getPrice());

        return reservationRepository.save(reservation);
    }


    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    public Reservation updateReservation(Long id, ReservationDTO reservationDto) {

        Reservation reservation = reservationRepository.findById(id).orElse(null);
        if (reservation == null) {
            return null;
        }

        if (reservationDto.getCheckInTime() != null) {
            reservation.setCheckInTime(reservationDto.getCheckInTime());
        }

        if (reservationDto.getCheckOutTime() != null) {
            reservation.setCheckOutTime(reservationDto.getCheckOutTime());
        }

        if (reservationDto.getClientId() != null) {
            Client client = clientRepository.findById(reservationDto.getClientId()).orElseThrow();
            reservation.setClient(client);
        }

        if (reservationDto.getRoomId() != null) {
            Room room = roomRepository.findById(reservationDto.getRoomId()).orElseThrow();

            if (!room.equals(reservation.getRoom())) {
                if (room.getStatus() != RoomStatus.AVAILABLE) {
                    throw new IllegalStateException("Room is not available");
                }

                Room oldRoom = reservation.getRoom();
                if (oldRoom != null) {
                    oldRoom.setStatus(RoomStatus.AVAILABLE);
                    roomRepository.save(oldRoom);
                }
                
                room.setStatus(RoomStatus.RESERVED);
                roomRepository.save(room);

                reservation.setRoom(room);
            }
        }

        return reservationRepository.save(reservation);
    }

    public void deleteReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElse(null);
        if (reservation == null) {
            throw new EntityNotFoundException("Reservation not found");
        }

        Room room = reservation.getRoom();
        if (room != null) {
            room.setStatus(RoomStatus.AVAILABLE);
            roomRepository.save(room);
        }

        reservationRepository.delete(reservation);
    }
}


