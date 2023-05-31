package com.example.myfilm.message;

import com.example.myfilm.post.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepo extends JpaRepository<Message, Long> {



}

