package com.example.fashionista.controller;

import com.example.fashionista.dto.LoginDto;
import com.example.fashionista.dto.SignupDto;
import com.example.fashionista.dto.UserResponseDto;
import com.example.fashionista.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class LoginAndSignupController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto>registerUser(@RequestBody SignupDto signupDto){
        return ResponseEntity.ok(userService.registerNewUser(signupDto));
    }
    @PostMapping("/login")
    public  ResponseEntity<UserResponseDto>loginUser(@RequestBody LoginDto loginDto){
        return ResponseEntity.ok(userService.loginUser(loginDto));
    }
    @DeleteMapping("/user/{userId}")
    public ResponseEntity<String>removeUser(@PathVariable Long userId){
        userService.deleteUser(userId);
        return ResponseEntity.ok("user with ID: "+userId+ " deleted.");
    }

}
