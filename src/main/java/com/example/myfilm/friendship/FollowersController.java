package com.example.myfilm.friendship;

import com.example.myfilm.post.model.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping()
public class FollowersController {

    private final FriendshipService friendshipService;

    @GetMapping("/getActivity")
    public List<Post> getActivity(@RequestParam(defaultValue = "0") int from,
                                  @RequestParam(defaultValue = "10") int size) {

        return friendshipService.addActivity(from, size);
    }
}
