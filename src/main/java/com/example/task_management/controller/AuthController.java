package com.example.task_management.controller;

import com.example.task_management.model.User;
import com.example.task_management.repository.UserRepository;
import com.example.task_management.security.jwt.JwtUtils;
import com.example.task_management.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

   @PostMapping("/register")
public Map<String, String> register(@RequestBody User user) {
    if (userRepository.existsByUsername(user.getUsername())) {
        return Map.of("error", "Username already exists");
    }
    userService.saveUser(user, passwordEncoder); // Pass PasswordEncoder explicitly
    return Map.of("message", "User registered successfully");
}


   @PostMapping("/login")
    public Map<String, String> login(@RequestBody User user) {
        // 🔹 Load user details from the database
        UserDetails userDetails = userService.loadUserByUsername(user.getUsername());

        // 🔹 Check if the password matches
        if (!passwordEncoder.matches(user.getPassword(), userDetails.getPassword())) {
            return Map.of("error", "Invalid username or password");
        }

        // 🔹 Generate JWT Token
        String token = jwtUtils.generateToken(userDetails.getUsername());

        return Map.of("token", token);
    }

}
