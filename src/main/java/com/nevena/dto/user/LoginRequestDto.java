package com.nevena.dto.user;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String email;
    private String password;
}
