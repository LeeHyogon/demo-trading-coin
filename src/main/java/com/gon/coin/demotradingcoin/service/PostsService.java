package com.gon.coin.demotradingcoin.service;

import com.gon.coin.demotradingcoin.domain.posts.Posts;
import com.gon.coin.demotradingcoin.domain.posts.dto.PostsListResponseDto;
import com.gon.coin.demotradingcoin.domain.posts.dto.PostsResponseDto;
import com.gon.coin.demotradingcoin.domain.posts.dto.PostsSaveRequestDto;
import com.gon.coin.demotradingcoin.domain.posts.dto.PostsUpdateRequestDto;
import com.gon.coin.demotradingcoin.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts=postsRepository.findById(id).orElseThrow(()->new
                IllegalArgumentException("해당 사용자가 없습니다. id="+id));
        posts.update(requestDto.getTitle(),requestDto.getContent());

        return id;
    }

    public List<Posts> findAll(){
        return postsRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }
}
