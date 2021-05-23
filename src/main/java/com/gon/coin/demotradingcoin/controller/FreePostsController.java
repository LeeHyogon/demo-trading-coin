package com.gon.coin.demotradingcoin.controller;

import com.gon.coin.demotradingcoin.domain.posts.Posts;
import com.gon.coin.demotradingcoin.domain.posts.dto.PostsResponseDto;
import com.gon.coin.demotradingcoin.domain.posts.dto.PostsSaveRequestDto;
import com.gon.coin.demotradingcoin.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class FreePostsController {
    private final PostsService postsService;

    @GetMapping("/freeposts")
    public String postHome(Model model){

        model.addAttribute("posts",postsService.findAllDesc());
        //기본폼을 넘겨줘야 페이지 생성가능
        return "/posts/freeposts/postsHome";
    }
    @GetMapping("/freeposts/save")
    public String createPosts(Model model){
        model.addAttribute("requestDto",new PostsSaveRequestDto());
        return "/posts/freeposts/save";
    }

    @PostMapping("/freeposts/save")
    public String create(PostsSaveRequestDto requestDto) {
        postsService.save(requestDto);
        //리소스를 생성하는 요청. POST는 리소스생성하는 역할만하고 클라이언트한테
        //redirect할 주소알려주고 클라이언트는 서버가 알려준 주소로 GET요청을 다시보냄.
        //model.addAttribute("posts",postsService.findAllDesc());
        return "redirect:/freeposts";
    }
    @GetMapping("/freeposts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "/posts/freeposts/update";
    }

}
