package com.example.myfilm.message;


import com.example.myfilm.user.UserEntity;
import com.example.myfilm.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepo messageRepo;
    private final UserRepository userRepository;
    public void sendMessage(Long resId, String text) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        Optional<UserEntity> userSend = userRepository.findByUsername(currentUserName);
        UserEntity userRes = userRepository.findById(Math.toIntExact(resId))
                                           .orElseThrow(() -> new RuntimeException(
                                                   "user with id = " + resId + " not found"));

        Message message = new Message();
        message.setSender(userSend.get());
        message.setRecipient(userRes);
        message.setText(text);
        messageRepo.save( message);
    }
}
