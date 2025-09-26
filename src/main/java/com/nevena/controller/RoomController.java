package com.nevena.controller;

import com.nevena.dto.room.RoomResponseDto;
import com.nevena.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @GetMapping
    public ResponseEntity<Page<RoomResponseDto>> getAllRooms(Pageable pageable) {
        return ResponseEntity.ok(roomService.list(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomResponseDto> getRoom(@PathVariable Long id) {
        return ResponseEntity.ok(roomService.get(id));
    }
}
