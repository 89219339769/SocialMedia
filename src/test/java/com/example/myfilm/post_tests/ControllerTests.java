package com.example.myfilm.post_tests;

import com.example.myfilm.image.ImageService;
import com.example.myfilm.post.PostController;
import com.example.myfilm.post.PostService;
import com.example.myfilm.post.model.PostInDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.nio.charset.StandardCharsets;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;




@WebMvcTest(controllers = PostController.class)

public class ControllerTests {

    @MockBean
    PostService postService;
    @MockBean
    ImageService imageService;
    @Autowired
    ObjectMapper mapper;
    @Autowired
    MockMvc mvc;


    @Test
    void createNewBooking() throws Exception {

        PostInDto postInDto = new PostInDto();
        postInDto.setTitle("title");




        mvc.perform(post("/post/create")
                                     .content(mapper.writeValueAsString(postInDto))
                                     .characterEncoding(StandardCharsets.UTF_8)
                                     .contentType(MediaType.APPLICATION_JSON)
                                     .accept(MediaType.APPLICATION_JSON))
           .andExpect(status().isForbidden());

    }
}
