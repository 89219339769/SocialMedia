package com.example.social.post;

import com.example.social.exceptions.NotFoundException;
import com.example.social.post.model.Post;
import com.example.social.post.model.PostInDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

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
            throw new NotFoundException("post not found");
        }
    }


    public void updatePost(PostInDto postInDto, Long postId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        List<Post> posts = postRepository.findPostsByUsername(currentUserName);
        boolean isPr = false;
        for (var post : posts) {
            if (post.getId() == postId) {
                Post postUp = postRepository.findById(postId)
                                            .orElseThrow(() -> new RuntimeException(
                                                    "user with id = " + postId + " not found"));
                postUp.setTitle(postInDto.getTitle());
                postUp.setDescription(postInDto.getDescription());
                postRepository.save(postUp);
                isPr = true;
            }
        }
        if (!isPr) {
            throw new NotFoundException("post not found, not possible to update");
        }
    }
}
