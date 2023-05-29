package com.example.myfilm.image;

import com.example.myfilm.post.model.Post;
import com.example.myfilm.post.model.PostInDto;

public class MappingPost {

    public static Post toCompilation(PostInDto postDto) {




        return Post.builder()
                          .id(postDto.getId())
                          .title(postDto.getTitle())
                          .description(postDto.getDescription())
                          .build();
    }
}