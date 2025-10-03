package com.nevena.controller;

import com.nevena.dto.room.RoomResponseDto;
import com.nevena.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @GetMapping
    public ResponseEntity<List<RoomResponseDto>> getAllRooms() {
        List<RoomResponseDto> rooms = roomService.list();
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomResponseDto> getRoom(@PathVariable Long id) {
        return ResponseEntity.ok(roomService.get(id));
    }
}
