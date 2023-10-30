package com.example.fashionista.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignupDto {
    @NotBlank(message = "*required")
    @Size(min = 2, max = 25)
    private String names;

    @NotBlank(message = "*required")
    @Size(min = 5, max = 25)
    private String mail;

    @NotBlank(message = "*required")
    @Size(min = 4, max = 20)
    private String password;
    @NotBlank(message = "*required")
    @Size(min = 4, max = 20)
    private String ConfirmPassword;
    private String address;
}
