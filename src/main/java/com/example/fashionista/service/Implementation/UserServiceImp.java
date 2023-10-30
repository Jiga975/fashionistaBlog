package com.example.fashionista.service.Implementation;

import com.example.fashionista.dto.LoginDto;
import com.example.fashionista.dto.SignupDto;
import com.example.fashionista.dto.UserResponseDto;
import com.example.fashionista.entity.User;
import com.example.fashionista.exception.DuplicateEmailException;
import com.example.fashionista.exception.PasswordNotMatchedException;
import com.example.fashionista.exception.WrongDetailException;
import com.example.fashionista.repository.UserRepository;
import com.example.fashionista.service.UserService;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper mapper = new ModelMapper();
    @Override
    public UserResponseDto registerNewUser(SignupDto signupDto) {
        if (userRepository.existsByMail(signupDto.getMail())){
            throw new DuplicateEmailException("email already exists");

        }

        if (!signupDto.getPassword().equals(signupDto.getConfirmPassword())){
            throw new PasswordNotMatchedException("password no match ");
        }
        User newUser = mapper.map(signupDto, User.class);
        User savedUser = userRepository.save(newUser);

        UserResponseDto responseDto = mapper.map(savedUser,UserResponseDto.class);
        return responseDto;

        //return mapper.map(savedUser,UserResponseDto.class);
    }

    @Override
    public UserResponseDto loginUser(LoginDto loginDto) {
        User user = userRepository
                .findUserEntitiesByMailAndPassword(loginDto.getMail(), loginDto.getPassword())
                .orElseThrow(()-> new WrongDetailException("Email/password incorrect"));
        return mapper.map(user, UserResponseDto.class);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);

    }
}
