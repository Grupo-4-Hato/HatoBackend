package com.upc.Hato.controller;

import com.upc.Hato.model.Room;
import com.upc.Hato.repository.RoomRepository;
import com.upc.Hato.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/hato")
public class RoomController {
    @Autowired
    private RoomService roomService;
    private final RoomRepository roomRepository;
    public RoomController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    //EndPoint: http://localhost:8080/api/hato/rooms
    //Method: GET
    @Transactional(readOnly = true)
    @RequestMapping("/rooms")
    public ResponseEntity<List<Room>> getAllRooms(){
        return new ResponseEntity<List<Room>>(roomRepository.findAll(), HttpStatus.OK);
    }

    //EndPoint: http://localhost:8080/api/hato/rooms
    //Method: POST
    @Transactional
    @PostMapping("/rooms")
    public ResponseEntity<Room> createRoom(@RequestBody Room room) {
        existsById(room);
        validateRoom(room);
        return new ResponseEntity<>(roomService.createRoom(room), HttpStatus.CREATED);
    }

    public void validateRoom(Room room) {
        if(room.getId() == null || room.getId().toString().trim().isEmpty()) {
            throw new RuntimeException("Room ID is required.");
        }
    }

    private void existsById(Room room) {
            if(roomRepository.existsById(room.getId())) {
            throw new RuntimeException("Room ID already exists.");
        }
    }
}
