package com.tradeflow.tradeservice.controller;

import com.tradeflow.tradeservice.entity.User;
import com.tradeflow.tradeservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.tradeflow.tradeservice.dto.AuthRequest;
import com.tradeflow.tradeservice.repository.UserRepository;
import com.tradeflow.tradeservice.security.JwtUtil;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String register(@RequestBody AuthRequest request) {

        User user = new User();

        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setRole("USER"); // default role

        if(userRepository.findByUsername(request.getUsername()).isPresent()){
            throw new RuntimeException("Username already exists");
        }
        else {
            userRepository.save(user);
        }
        return "User registered successfully";
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest request) {

        User existingUser = userRepository
                .findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), existingUser.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return jwtUtil.generateToken(existingUser.getUsername());
    }

}