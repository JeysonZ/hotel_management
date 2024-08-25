package com.example.hotelmanagement.service;

import com.example.hotelmanagement.dto.RoomDTO;
import com.example.hotelmanagement.mapper.RoomMapper;
import com.example.hotelmanagement.model.Room;
import com.example.hotelmanagement.model.RoomStatus;
import com.example.hotelmanagement.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomMapper roomMapper;

    public List<RoomDTO> getAllRooms() {
        return roomRepository.findAll().stream()
                .map(roomMapper::toDTO)
                .toList();
    }

    public RoomDTO getRoomById(Long id) {
        return roomRepository.findById(id)
                .map(roomMapper::toDTO)
                .orElse(null);
    }

    public RoomDTO saveRoom(RoomDTO roomDTO) {
        Room room = roomMapper.toEntity(roomDTO);
        room.setStatus(RoomStatus.AVAILABLE); // Default status
        return roomMapper.toDTO(roomRepository.save(room));
    }

    public Room updateRoomStatus(Long roomId, RoomStatus status) {
        Optional<Room> roomOptional = roomRepository.findById(roomId);
        if (roomOptional.isPresent()) {
            Room room = roomOptional.get();
            room.setStatus(status);
            return roomRepository.save(room);
        }
        return null;
    }

    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }
}


