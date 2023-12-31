package com.example.fashionista.service;

import com.example.fashionista.dto.PostDto;
import com.example.fashionista.dto.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);
    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);
    PostDto getPostById(Long id);
    PostDto updatePost(PostDto postDto,Long id);
    void deletePostById(Long id);
}
