package com.example.social.message.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@AllArgsConstructor
@Builder
public class MessageDto {
    private String senderName;
    private String text;
}
