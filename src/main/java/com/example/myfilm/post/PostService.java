package com.example.myfilm.post;


import com.example.myfilm.image.Image;
import com.example.myfilm.image.MappingPost;
import com.example.myfilm.post.model.Post;
import com.example.myfilm.post.model.PostInDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    public void savePicture(MultipartFile file) throws IOException {
        Image image1;

         image1 = toImageEntity(file);




       log.info("Saving new Product. Title: {}; Author: {}");
//        Post productFromDb = productRepository.save(post);
//        productFromDb.setPreviewImageId(productFromDb.getImages().get(0).getId());
//        productRepository.save(post);
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

    public void deleteProduct(Long id) {
        postRepository.deleteById(id);
    }

    public Post getProductById(Long id) {
        return postRepository.findById(id).orElse(null);
    }


    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public void savePost(PostInDto postInDto) {
        postRepository.save(MappingPost.toCompilation(postInDto));

    }
}
