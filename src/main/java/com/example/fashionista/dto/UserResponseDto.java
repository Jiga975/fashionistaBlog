package com.example.fashionista.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {
    private Long userId;
    private String names;
    private String mail;
    private String role;
    private String address;

}
