package com.example.myfilm.friendship;


import com.example.myfilm.post.model.Post;
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
        if (friendId == userFr.get().getId()) {
            throw new RuntimeException("you can't be friends with yourself");
        }
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

        Integer UserFr = userFr.get().getId();
        Integer UserFr2 = friend2.getId();
        followersRepo.findByFolIdAndId(UserFr2, UserFr)
                     .orElseThrow(() -> new RuntimeException("user with id = " + friendId + " not found"));

        Friendship friendship = new Friendship();
        friendship.setFriend1(userFr.get());
        friendship.setFriend2(friend2);
        friendshipRepo.save(friendship);
    }

    public List<Post> addActivity() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        Optional<UserEntity> userFr = userRepository.findByUsername(currentUserName);

        List<Followers> followers = followersRepo.findByFr(userFr.get().getId());
        List<Integer> postsId = new ArrayList<>();
        for (var follower : followers) {
            postsId.add(follower.getFollower2().getId());
        }
        List<Post>usPosts = new ArrayList<>();

        //найти не по номеру пользователей а по username
        //взять посты из базы и у которых совпадает username в список постов класть
        List<UserEntity> users = userRepository.findAllById(postsId);
        for (var user : users) {
            usPosts.add(user.)

        }
    }
}
