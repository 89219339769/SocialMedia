package com.example.myfilm.image;


import com.example.myfilm.post.PostRepository;
import com.example.myfilm.post.model.Post;
import com.example.myfilm.security.SecurityConfig;
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
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;

    private final PostRepository postRepository;


    public void savePicture(MultipartFile file, Long postId) throws IOException {
        Image image;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();

        List<Post> posts = postRepository.findPostsByUsername(currentUserName);
        boolean isPr = false;
        for (var userPost : posts) {
            if (userPost.getId() == postId) {

                Post post = postRepository.findById(postId)
                                          .orElseThrow(() -> new RuntimeException(
                                                  "user with id = " + postId + " not found"));
                image = toImageEntity(file);
                image.setPost(post);
                post.setImage(image);
                imageRepository.save(image);
                postRepository.save(post);
                isPr = true;
            }
        }
        if (!isPr) throw new RuntimeException("no post number " + postId + " found");
    }

    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }


}
