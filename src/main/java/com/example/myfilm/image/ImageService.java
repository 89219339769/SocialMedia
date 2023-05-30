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
        if (currentUserName == null) {
            throw new RuntimeException("unable to save picture, user does not exist");
        }

        List<Post> posts = postRepository.findPostsByUsername(currentUserName);
        boolean isPr = false;
        for (var post : posts) {
            if (post.getId() == postId) {
                image = toImageEntity(file);
                imageRepository.save(image);
                isPr = true;
            }
        }
        if (isPr==false) throw new RuntimeException("no post number" + postId + " found");
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
