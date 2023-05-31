package com.example.myfilm.message.model;

import com.example.myfilm.user.UserEntity;


public class Mapping {

    public static MessageDto toMessageDto(Message message, UserEntity user) {
        return MessageDto.builder().senderName(user.getUsername()).text(message.getText()).build();
    }
}
