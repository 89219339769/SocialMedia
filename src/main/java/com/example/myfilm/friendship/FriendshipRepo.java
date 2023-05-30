package com.example.myfilm.friendship;

import com.example.myfilm.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendshipRepo extends JpaRepository<Friendship, Long> {




}
