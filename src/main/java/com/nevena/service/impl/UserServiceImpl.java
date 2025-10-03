package com.nevena.service.impl;

import com.nevena.dto.user.LoginResponseDto;
import com.nevena.dto.user.LoginRequestDto;
import com.nevena.dto.user.RegisterRequestDto;
import com.nevena.entities.Role;
import com.nevena.entities.User;
import com.nevena.repository.RoleRepository;
import com.nevena.repository.UserRepository;
import com.nevena.service.JwtService;
import com.nevena.service.UserService;
import com.nevena.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    /***
     * Login user
     * @param email
     * @param password
     * @return
     */
    @Override
    public LoginResponseDto login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        Set<String> roles = user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toSet());

        String token = jwtService.createJwt(user, roles);
        return new LoginResponseDto(token);
    }

    /***
     * Register new user
     * @param dto
     * @return
     */
    @Override
    public String register(RegisterRequestDto dto) {
        if (userRepository.findByEmail(dto.getEmail()).isPresent()){
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setJmbg(dto.getJmbg());

        Role clientRole = roleRepository.findByName("CLIENT")
                .orElseThrow(() -> new RuntimeException("Default role CLIENT not found"));

        user.getRoles().add(clientRole);

        userRepository.save(user);

        return "User registered successfully!";
    }


}
