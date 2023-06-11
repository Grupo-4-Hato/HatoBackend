package com.upc.Hato.service.Impl;

import com.upc.Hato.model.Room;
import com.upc.Hato.repository.RoomRepository;
import com.upc.Hato.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomRepository roomRepository;

    @Override
    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }
}
