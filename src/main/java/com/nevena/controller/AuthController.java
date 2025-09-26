package com.nevena.controller;

import com.nevena.dto.user.LoginRequestDto;
import com.nevena.dto.user.LoginResponseDto;
import com.nevena.dto.user.RegisterRequestDto;
import com.nevena.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto dto) {
        System.out.println("Login attempt: " + dto.getEmail() + " / " + dto.getPassword());
        try {
            LoginResponseDto response = authService.login(dto.getEmail(), dto.getPassword());
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequestDto dto) {
        try {
            return ResponseEntity.ok(authService.register(dto));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

