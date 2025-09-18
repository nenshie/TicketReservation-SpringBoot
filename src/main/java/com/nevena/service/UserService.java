package com.nevena.service;

import com.nevena.entities.Film;
import com.nevena.entities.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();
    User getUserById(Long id);
    User saveUser(User user);
    void deleteUser(Long id);
    User getUserByEmail(String email);
}
