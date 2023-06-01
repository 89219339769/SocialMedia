package com.example.social.message;

import com.example.social.message.model.MessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("message")
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/send")
    public void send(@RequestParam Long resId, @RequestParam String text) {
        messageService.sendMessage(resId, text);
    }

    @GetMapping("/receive")
    public List<MessageDto> receiveMessages() {
        return messageService.receiveMessage();
    }

    @GetMapping("/receiveFrom/{userId}")
    public List<MessageDto> receiveMessagesFromUser(@PathVariable Long userId) {
        return messageService.receiveMessageFromUser(userId);
    }
}
