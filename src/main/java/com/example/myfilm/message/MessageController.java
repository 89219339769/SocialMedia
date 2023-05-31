package com.example.myfilm.message;


import com.example.myfilm.post.model.PostInDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("message")
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/send")
    public void handleFileUpload(@RequestParam Long resId, @RequestParam String text)  {

        messageService.sendMessage(resId, text);

    }



}
