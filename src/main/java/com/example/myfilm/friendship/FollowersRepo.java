package com.example.myfilm.friendship;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface FollowersRepo extends JpaRepository<Followers, Long> {
    @Query("select f from Followers f  where f.follower1.id = ?1 and f.follower2.id = ?2 ")
    Optional<Followers> findByFolIdAndId(Integer userId, Integer folId);

    @Query("select f from Followers f  where f.follower1.id = ?1 ")
    List<Followers> findByFr(int id);
}


