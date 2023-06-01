package com.example.myfilm.friendship;


import com.example.myfilm.exceptions.NotFoundException;
import com.example.myfilm.post.PostRepository;
import com.example.myfilm.post.model.Post;
import com.example.myfilm.user.UserEntity;
import com.example.myfilm.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    private final PostRepository postRepository;

    public void addFriend(Long friendId) {
        Followers followers = new Followers();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        Optional<UserEntity> userFr = userRepository.findByUsername(currentUserName);
        UserEntity friend2 = userRepository.findById(Math.toIntExact(friendId))
                                           .orElseThrow(() -> new RuntimeException(
                                                   "user with id = " + friendId + " not found"));
        if (friendId == userFr.get().getId()) {
            throw new NotFoundException("you can't be friends with yourself");
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
                                           .orElseThrow(() -> new NotFoundException(
                                                   "user with id = " + friendId + " not found"));

        Integer UserFr = userFr.get().getId();
        Integer UserFr2 = friend2.getId();
        followersRepo.findByFolIdAndId(UserFr2, UserFr)
                     .orElseThrow(() -> new NotFoundException("user with id = " + friendId + " not found"));

        Friendship friendship = new Friendship();
        friendship.setFriend1(userFr.get());
        friendship.setFriend2(friend2);
        friendshipRepo.save(friendship);
    }

    @Transactional
    public List<Post> addActivity( int from, int size) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        Optional<UserEntity> userFr = userRepository.findByUsername(currentUserName);

        List<Followers> followers = followersRepo.findByFr(userFr.get().getId());
        List<Integer> postsId = new ArrayList<>();
        for (var follower : followers) {
            postsId.add(follower.getFollower2().getId());
        }

        List<UserEntity> users = userRepository.searchAllByIdIs(postsId);

        List<String> userNames = new ArrayList<>();
        for (var user : users) {
            userNames.add(user.getUsername());
        }

        Pageable pageable = PageRequest.of(from, size, Sort.by(Sort.Direction.DESC, "dateOfCreated"));
        List<Post> posts = postRepository.searchAllByUserNames(userNames, pageable);
        return posts;
    }
}
