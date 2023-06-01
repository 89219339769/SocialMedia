package com.example.myfilm.post_tests;
import com.example.myfilm.post.PostRepository;
import com.example.myfilm.post.model.Post;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDateTime;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
@SpringBootTest
public class PostRepositoryTests {
    @Autowired
    private PostRepository postRepository;

    @Test
    public void savePost() {
        Post post = create();
        Post savePost = postRepository.save(post);
        Assertions.assertThat(savePost).isNotNull();
        Assertions.assertThat(savePost.getId()).isGreaterThan(0);
    }

    @Test
    public void deletePost() {
        Post post = create();
        Long postId = postRepository.save(post).getId();
        postRepository.deleteById(postId);
        postRepository.find(postId);
        assertThatThrownBy(() -> {
            throw new RuntimeException();
        }).isExactlyInstanceOf(RuntimeException.class);
    }

    private Post create() {
        Post post = new Post();
        post.setTitle("test");
        post.setDescription("testing");
        post.setDateOfCreated(LocalDateTime.now());
        post.setUserName("Name");
        return post;
    }
}
