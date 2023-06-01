package com.example.social.friendship;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendshipRepo extends JpaRepository<Friendship, Long> {
}
