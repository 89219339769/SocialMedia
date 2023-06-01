package com.example.myfilm.post_tests;


import com.example.myfilm.post.model.PostInDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
class PostJsonTest {
    @Autowired
    JacksonTester<PostInDto> json;

    @Test
    void testPostInDto() throws Exception {
        PostInDto postInDto = new PostInDto();
        postInDto.setTitle("title");
        postInDto.setDescription("description");

        JsonContent<PostInDto> result = json.write(postInDto);
        assertThat(result).extractingJsonPathStringValue("$.title").isEqualTo("title");
        assertThat(result).extractingJsonPathStringValue("$.description").isEqualTo("description");
    }
}