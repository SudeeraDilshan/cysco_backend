package com.example.demo.controller;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.SignupRequest;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider tokenProvider;

@PostMapping("/signup")
public String registerUser(@RequestBody SignupRequest signupRequest) {
    User user = new User();
    user.setUsername(signupRequest.getUsername());
    user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
    user.setRole(signupRequest.getRole()); // Set the role
    userRepository.save(user);
    return "User registered successfully";
}

@PostMapping("/login")
public Map<String,String> authenticateUser(@RequestBody LoginRequest loginRequest) {
    User user = userRepository.findByUsername(loginRequest.getUsername())
            .orElseThrow(() -> new RuntimeException("User not found"));

    if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
        String token =  tokenProvider.generateToken(user.getUsername(), user.getRole()); // Pass role to token generation
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("role", user.getRole());
        map.put("username", user.getUsername());
        map.put("Id",user.getId());
        return map;
    } else {
        throw new RuntimeException("Invalid credentials");
    }
}

}
