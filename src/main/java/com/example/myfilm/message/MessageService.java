package com.example.myfilm.message;


import com.example.myfilm.message.model.Mapping;
import com.example.myfilm.message.model.Message;
import com.example.myfilm.message.model.MessageDto;
import com.example.myfilm.user.UserEntity;
import com.example.myfilm.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
        UserEntity userRec = userRepository.findById(Math.toIntExact(resId))
                                           .orElseThrow(() -> new RuntimeException(
                                                   "user with id = " + resId + " not found"));

        Message message = new Message();
        message.setSender(userSend.get());
        message.setRecipient(userRec);
        message.setText(text);
        messageRepo.save( message);
    }

    public List<MessageDto> receiveMessage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        Optional<UserEntity> userRec = userRepository.findByUsername(currentUserName);

        List<Message>messages = messageRepo.findAllByResId(userRec.get().getId());
        List<MessageDto>messageDtos = new ArrayList<>();
        for (var message: messages)
            messageDtos.add(Mapping.toMessageDto(message, message.getSender()));
        return messageDtos;

    }

    public List<MessageDto> receiveMessageFromUser(Long userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        Optional<UserEntity> userRec = userRepository.findByUsername(currentUserName);
        Integer userRecId = userRec.get().getId();
        List<Message>messages = messageRepo.findMessagesByRecAndSend(userRecId, Math.toIntExact(userId));
        List<MessageDto>messageDtos = new ArrayList<>();
        for (var message: messages)
            messageDtos.add(Mapping.toMessageDto(message, message.getSender()));
        return messageDtos;
    }
}
