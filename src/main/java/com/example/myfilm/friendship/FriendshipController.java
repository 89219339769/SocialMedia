package com.example.myfilm.friendship;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping()
public class FriendshipController {
private final FriendshipService friendshipService;

    @PostMapping("/addFriend/{friendId}")
    public void addFriend (@PathVariable Long friendId)  {
         friendshipService.addFriend(friendId);

    }

    @PostMapping("/confirmFriend/{friendId}")
    public void confirmFriendship (@PathVariable Long friendId)  {
        friendshipService.confirmFriendship(friendId);
    }
}
