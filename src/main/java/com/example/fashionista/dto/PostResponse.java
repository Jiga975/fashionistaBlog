package com.example.fashionista.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class PostResponse {
    private List<PostDto>content;
    private int pageNo;
    private int pageSize;
    private long totalElement;
    private int totalPage;
    private boolean last;
}
