package com.nevena.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class UserDto {
    private String email;
    private String jmbg;
    private String name;
    private String surname;
    private Set<String> roles;
}
