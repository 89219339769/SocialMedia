package com.example.myfilm.post;


import com.example.myfilm.friendship.Followers;
import com.example.myfilm.image.Image;
//import com.example.myfilm.image.MappingPost;
import com.example.myfilm.image.ImageRepository;
import com.example.myfilm.post.model.Post;
import com.example.myfilm.post.model.PostInDto;
import com.example.myfilm.user.UserEntity;
import com.example.myfilm.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    private final ImageRepository imageRepository;

    public List<Post> listProducts(String title) {
        if (title != null) return postRepository.findByTitle(title);
        return postRepository.findAll();
    }


    public void savePost(PostInDto postInDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();

        Post post = Post.builder()
                        .id(postInDto.getId())
                        .title(postInDto.getTitle())
                        .userName(currentUserName)
                        .description(postInDto.getDescription())
                        .dateOfCreated(LocalDateTime.now())
                        .build();
        postRepository.save(post);

    }

    public void deletePost(Long postId) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        List<Post> posts = postRepository.findPostsByUsername(currentUserName);
        boolean isPr = false;
        for (var post : posts) {
            if (post.getId() == postId) {
                postRepository.deleteById(postId);
                isPr = true;
            }
        }
        if (!isPr) {
            throw new RuntimeException("пост не найден");
        }

    }
}
