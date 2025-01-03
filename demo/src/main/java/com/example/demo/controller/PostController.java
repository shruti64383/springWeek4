package com.example.demo.controller;

import com.example.demo.dto.PostDto;
import com.example.demo.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @GetMapping
    public List<PostDto> getAllPost(){
        return postService.getAllPost();
    }

    @GetMapping("/{postId}")
    public PostDto getPostById(@PathVariable Long postId){
        return postService.getPostById(postId);
    }

    @PostMapping
    public PostDto createNewPost(@RequestBody PostDto postDto){
        return postService.createNewPost(postDto);
    }

    @PutMapping("/{postId}")
    public PostDto updatePost(@RequestBody PostDto inputDto, @PathVariable Long postId){
        return postService.updatePost(inputDto, postId);
    }

}
