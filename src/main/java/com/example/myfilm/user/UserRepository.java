package com.example.myfilm.user;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    @Query("select u from UserEntity u  where u.username = ?1 ")
    Optional<UserEntity> findByUsername(String username);
    Boolean existsByUsername(String username);
}