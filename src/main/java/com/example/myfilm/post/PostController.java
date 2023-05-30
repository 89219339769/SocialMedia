package com.example.myfilm.post;


import com.example.myfilm.image.ImageService;
import com.example.myfilm.post.model.PostInDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

//@Controller
@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/post")
public class PostController {
    private final PostService postService;
    private final ImageService imageService;
    @GetMapping("/posts")
    public String posts(Model model) {
        model.addAttribute("posts", postService.findAll());
        return "posts.html";
    }

//    @GetMapping("/product/{id}")
//    public String productInfo(@PathVariable Long id, Model model) {
//        Post product = productService.getProductById(id);
//        model.addAttribute("product", product);
//        model.addAttribute("images", product.getImages());
//        return "product-info";
//    }

//    @PostMapping("/product/create")
//    public String createProduct(@RequestParam("file1") MultipartFile file1, Post product) throws IOException {
//        postService.saveProduct(product, file1);
//        return "redirect:/";
//    }


    @PostMapping("/addPicture/{postId}")
    public ResponseEntity<String> addPicture(@RequestParam("file") MultipartFile file, @PathVariable Long postId) throws IOException {
        imageService.savePicture(file, postId);
        return ResponseEntity.ok().body("file received successfully");
    }

    @PostMapping("/create")
    public PostInDto handleFileUpload(@RequestBody PostInDto postInDto)  {

        postService.savePost(postInDto);
        return postInDto;
    }


//    @PostMapping("/product/delete/{id}")
//    public String deleteProduct(@PathVariable Long id) {
//        productService.deleteProduct(id);
//        return "redirect:/";
//    }
}
