package com.tindering.controller;

import com.tindering.models.Story;
import com.tindering.models.User;
import com.tindering.service.StoryService;
import com.tindering.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StoryController {

    @Autowired
    private StoryService storyService;

    @Autowired
    private UserService userService;

    @PostMapping("/api/story")
    public Story createStory(@RequestBody Story story, @RequestHeader("Authorization") String jwt) throws Exception {
        User user=userService.findUserByJwt(jwt);
        Story createStory=storyService.createStory(story, user.getId());
        return createStory;
    }

    @GetMapping("/api/story/user/{userId}")
    public List<Story> findUserStory(@PathVariable("userId") Integer userId) throws Exception {
        List<Story> userStory=storyService.findStoryByUserId(userId);
        return userStory;
    }
}
