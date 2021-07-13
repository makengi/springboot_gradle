package com.example.book.springboot.web;

import com.example.book.springboot.service.PostsService;
import com.example.book.springboot.web.dto.PostUpdateRequestDto;
import com.example.book.springboot.web.dto.PostsResponseDto;
import com.example.book.springboot.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostUpdateRequestDto requestDto){
        return postsService.update(id,requestDto);
    }
    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        return postsService.findById(id);
    }

    @GetMapping("/api/v1/posts")
    public List<PostsResponseDto.All> findAll(){
        return postsService.findAll();
    }
}
