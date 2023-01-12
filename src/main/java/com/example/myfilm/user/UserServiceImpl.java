package com.example.myfilm.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements  UserService{

   private final UserRepository repository;

    @Override
    public User save(User user) {
        return repository.save(user);
    }
}
