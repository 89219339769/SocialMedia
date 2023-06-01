package com.example.social.user;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    @Query("select u from UserEntity u  where u.username = ?1 ")
    Optional<UserEntity> findByUsername(String username);
    Boolean existsByUsername(String username);


    @Query(value = "SELECT u FROM UserEntity u WHERE u.id IN :ids")
    List<UserEntity> searchAllByIdIs(@Param("ids") Collection<Integer> ids);

}