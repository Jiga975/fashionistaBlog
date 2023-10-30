package com.example.fashionista.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostDto {
    private Long id;
    private String tittle;
    private String description;
    private String content;
}
