package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.LoginResponse;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.security.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public LoginResponse login(LoginRequest request) {
        User user = userMapper.findByUsername(request.getUsername());

        if (user == null) {
            throw new IllegalArgumentException("帳號或密碼錯誤");
        }

        boolean passwordCorrect = passwordEncoder.matches(
                request.getPassword(),
                user.getPassword());

        if (!passwordCorrect) {
            throw new IllegalArgumentException("帳號或密碼錯誤");
        }

        String token = jwtService.generateToken(user);

        return new LoginResponse(token);
    }
}