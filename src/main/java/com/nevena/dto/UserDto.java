package com.nevena.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDto {

    private Long userId;
    private String email;
    private String name;
    private String surname;
}
