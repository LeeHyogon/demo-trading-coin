package com.gon.coin.demotradingcoin.api;


import com.gon.coin.demotradingcoin.domain.posts.dto.PostsUpdateRequestDto;
import com.gon.coin.demotradingcoin.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FreePostsApiController {

    private final PostsService postsService;

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }
}
