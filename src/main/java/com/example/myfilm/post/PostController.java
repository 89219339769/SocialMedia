package com.example.myfilm.post;

import com.example.myfilm.image.ImageService;
import com.example.myfilm.post.model.PostInDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/post")
public class PostController {
    private final PostService postService;
    private final ImageService imageService;


    @PostMapping("/addPicture/{postId}")
    public ResponseEntity<String> addPicture(@RequestParam("file") MultipartFile file, @PathVariable Long postId)
            throws IOException {
        imageService.savePicture(file, postId);
        return ResponseEntity.ok().body("file received successfully");
    }

    @PostMapping("/create")
    public PostInDto createPost(@RequestBody PostInDto postInDto) {

        postService.savePost(postInDto);
        return postInDto;
    }

    @PutMapping("/update/{postId}")
    public PostInDto updatePost(@RequestBody PostInDto postInDto, @PathVariable Long postId) {

        postService.updatePost(postInDto, postId);
        return postInDto;
    }

    @DeleteMapping("/delete/{postId}")
    public void deletePost(@PathVariable Long postId) {

        postService.deletePost(postId);

    }
}
