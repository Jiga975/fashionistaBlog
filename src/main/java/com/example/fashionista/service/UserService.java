package com.example.fashionista.service;

import com.example.fashionista.dto.LoginDto;
import com.example.fashionista.dto.SignupDto;
import com.example.fashionista.dto.UserResponseDto;


public interface UserService {

    UserResponseDto registerNewUser(SignupDto signupDto);
    UserResponseDto loginUser(LoginDto loginDto);
    void deleteUser (Long userId);

}
