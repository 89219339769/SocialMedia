package com.example.myfilm.post;


import com.example.myfilm.image.Image;
import com.example.myfilm.post.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByTitle(String title);

    @Query("select p from Post p  where p.userName = ?1 ")
    List<Post> findPostsByUsername(String currentUserName);

}
