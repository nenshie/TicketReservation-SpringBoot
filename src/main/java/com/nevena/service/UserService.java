package com.nevena.service;

import com.nevena.dto.user.LoginResponseDto;
import com.nevena.dto.user.LoginRequestDto;
import com.nevena.dto.user.RegisterRequestDto;
import com.nevena.entities.User;

public interface UserService {
    LoginResponseDto login(String email, String password);
    String register(RegisterRequestDto dto);
    User getByEmail(String email);
}
