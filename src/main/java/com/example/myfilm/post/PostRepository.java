package com.example.myfilm.post;

import com.example.myfilm.post.model.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;


public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByTitle(String title);

    @Query("select p from Post p  where p.userName = ?1 ")
    List<Post> findPostsByUsername(String currentUserName);

    @Query(value = "SELECT p FROM Post p WHERE p.userName IN :userNames")
    List<Post> searchAllByUserNames(@Param("userNames") Collection<String> userNames,  Pageable pageable);

}
