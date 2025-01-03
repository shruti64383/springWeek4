package com.example.demo.service;

import com.example.demo.dto.PostDto;

import java.util.List;

public interface PostService {
    List<PostDto> getAllPost();

    PostDto createNewPost(PostDto inputPost);

    PostDto getPostById(Long postId);

    PostDto updatePost(PostDto inputDto, Long postId);
}
