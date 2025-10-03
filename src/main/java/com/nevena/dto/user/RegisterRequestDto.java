package com.nevena.dto.user;

import lombok.Data;

@Data
public class RegisterRequestDto {
    private String email;
    private String password;
    private String name;
    private String surname;
    private String jmbg;
    private String mobileNumber;
    private String roles;
}