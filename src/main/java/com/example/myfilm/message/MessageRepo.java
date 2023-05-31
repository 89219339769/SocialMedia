package com.example.myfilm.message;

import com.example.myfilm.message.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepo extends JpaRepository<Message, Long> {

    @Query("select m from Message m  where m.recipient.id = ?1 ")
    List<Message> findAllByResId(int id);
}

