package com.example.myfilm.image;

import com.example.myfilm.post.model.Post;
import com.example.myfilm.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {

    //@Query("select m from Image m  where m.post.id = ?1 ")
    List<Image> findAllByPost(Post post);
}
