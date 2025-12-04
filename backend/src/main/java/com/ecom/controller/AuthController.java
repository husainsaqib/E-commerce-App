package com.ecom.controller;
import com.ecom.model.User;
import com.ecom.repository.UserRepository;
import com.ecom.service.UserService;
import com.ecom.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder encoder;

    public AuthController(UserService userService, UserRepository userRepository, JwtUtil jwtUtil, BCryptPasswordEncoder encoder){
        this.userService = userService; this.userRepository = userRepository; this.jwtUtil = jwtUtil; this.encoder = encoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String,String> body){
        User u = userService.register(body.get("username"), body.get("email"), body.get("password"));
        return ResponseEntity.ok(Map.of("id", u.getId(), "username", u.getUsername()));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String,String> body){
        var opt = userRepository.findByUsername(body.get("username"));
        if(opt.isEmpty()) return ResponseEntity.status(401).body(Map.of("error","invalid credentials"));
        User u = opt.get();
        if(!encoder.matches(body.get("password"), u.getPasswordHash())) return ResponseEntity.status(401).body(Map.of("error","invalid credentials"));
        String access = jwtUtil.generateAccessToken(u.getUsername(), u.getRole());
        String refresh = jwtUtil.generateRefreshToken(u.getUsername());
        return ResponseEntity.ok(Map.of("accessToken", access, "refreshToken", refresh));
    }
}

