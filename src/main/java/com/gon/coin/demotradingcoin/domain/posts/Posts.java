package com.gon.coin.demotradingcoin.domain.posts;

import com.gon.coin.demotradingcoin.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="posts_id")
    private Long id;


    private String title;

    private String content;

    private String author;

    @Builder
    public Posts(String title,String content,String author){
        this.title=title;
        this.content=content;
        this.author=author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
