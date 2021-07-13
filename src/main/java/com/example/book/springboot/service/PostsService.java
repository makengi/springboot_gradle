package com.example.book.springboot.service;

import com.example.book.springboot.domain.posts.Posts;
import com.example.book.springboot.domain.posts.PostsRepository;
import com.example.book.springboot.web.dto.PostUpdateRequestDto;
import com.example.book.springboot.web.dto.PostsResponseDto;
import com.example.book.springboot.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;
    private final Logger LOG = LogManager.getLogger(PostsService.class);

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(()->new IllegalArgumentException(
                "해당 게시글이 없습니다. id="+id
        ));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(()->new
                IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        return new PostsResponseDto(entity);
    }

    public List<PostsResponseDto.All> findAll(){
        List<PostsResponseDto.All> posts =  postsRepository.findAll()
                .stream().map(post->PostsResponseDto.All.builder().posts(post).build())
                .collect(Collectors.toList());
        LOG.info("@ size: {}",posts.size());
        return posts;
    }

}
