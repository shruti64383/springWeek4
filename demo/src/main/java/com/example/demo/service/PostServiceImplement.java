package com.example.demo.service;

import com.example.demo.dto.PostDto;
import com.example.demo.entities.PostEntity;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repositories.PostRepository;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImplement implements PostService{

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<PostDto> getAllPost() {
        return postRepository
                .findAll()
                .stream()
                .map(postEntity -> modelMapper.map(postEntity, PostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PostDto createNewPost(PostDto inputPost) {
        PostEntity postEntity = modelMapper.map(inputPost, PostEntity.class);
        return modelMapper.map(postRepository.save(postEntity), PostDto.class);
    }

    @Override
    public PostDto getPostById(Long postId) {
        PostEntity postEntity = postRepository
                .findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("Post not find with id"+postId));
        return modelMapper.map(postEntity, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto inputDto, Long postId) {
        PostEntity olderPost = postRepository
                .findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post not find with id"+postId));
        inputDto.setId(postId);
        modelMapper.map(inputDto, olderPost);
        return modelMapper.map(postRepository.save(olderPost), PostDto.class);
    }

}
