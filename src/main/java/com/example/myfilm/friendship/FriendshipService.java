package com.example.myfilm.friendship;


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
public class FriendshipService {

    private final FollowersRepo followersRepo;

    private final FriendshipRepo friendshipRepo;
    private final UserRepository userRepository;

    public void addFriend(Long friendId) {

        Followers followers = new Followers();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        Optional<UserEntity> userFr = userRepository.findByUsername(currentUserName);
        UserEntity friend2 = userRepository.findById(Math.toIntExact(friendId))
                                           .orElseThrow(() -> new RuntimeException(
                                                   "user with id = " + friendId + " not found"));

        followers.setFollower1(userFr.get());
        followers.setFollower2(friend2);
        followersRepo.save(followers);

    }

    public void confirmFriendship(Long friendId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        Optional<UserEntity> userFr = userRepository.findByUsername(currentUserName);
        UserEntity friend2 = userRepository.findById(Math.toIntExact(friendId))
                                           .orElseThrow(() -> new RuntimeException(
                                                   "user with id = " + friendId + " not found"));

        Integer UserFr =  userFr.get().getId();
        Integer UserFr2 =  friend2.getId();
        Followers follower = followersRepo.findByFolIdAndId(UserFr2, UserFr)
                                          .orElseThrow(() -> new RuntimeException(
                "user with id = " + friendId + " not found"));
        followersRepo.delete(follower);
        Friendship friendship = new Friendship();
        friendship.setFriend1(userFr.get());
        friendship.setFriend2(friend2);
        friendshipRepo.save(friendship);
    }
}
