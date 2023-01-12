package com.example.myfilm;

import com.example.myfilm.user.controller.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import ru.practicum.shareit.exceptions.NotFoundException;
import ru.practicum.shareit.user.model.User;


import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor

public class UserServiceImpl implements ru.practicum.shareit.user.UserService {

    private final UserRepository repository;

    @Override
    public User saveUser(User user) {
        repository.save(user);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public User get(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Не найден пользователь с id: " + id));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);

    }

    @Override
    public User updateUser(Long id, User user) {
        user.setId(id);
        User updateUser = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Не найден пользователь с id: " + id));
        if (updateUser.getEmail().equals(user.getEmail())) {
            throw new NotFoundException("адрес указанной обновляемой электронной почты уже сущетсвует ");
        }
        if (user.getEmail() != null && user.getEmail() != updateUser.getEmail()) {
            updateUser.setEmail(user.getEmail());
        }
        if (user.getName() != null && user.getName() != updateUser.getName()) {
            updateUser.setName(user.getName());
        }
        repository.save(updateUser);
        return updateUser;
    }
}
