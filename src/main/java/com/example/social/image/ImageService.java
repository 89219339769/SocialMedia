package com.example.social.image;


import com.example.social.post.PostRepository;
import com.example.social.post.model.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


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
                Long id = imageRepository.save(image).getId();
                post.setImageId(id);

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
