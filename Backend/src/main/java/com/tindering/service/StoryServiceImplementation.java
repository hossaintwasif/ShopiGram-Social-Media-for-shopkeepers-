package com.tindering.service;

import com.tindering.models.Story;
import com.tindering.models.User;
import com.tindering.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StoryServiceImplementation implements StoryService {

    @Autowired
    public StoryRepository storyRepository;

    @Autowired
    public UserService userService;

    @Override
    public Story createStory(Story story, Integer userId) throws Exception {

        User user = userService.findUserById(userId);
        Story storyCreate=new Story();
        storyCreate.setCaption(story.getCaption());
        storyCreate.setImage(story.getImage());
        storyCreate.setUser(user);
        storyCreate.setTimeStamp(LocalDateTime.now());


        return storyRepository.save(storyCreate);
    }

    @Override
    public List<Story> findStoryByUserId(Integer userId) throws Exception {
        User userStory=userService.findUserById(userId);
        return storyRepository.findByUserId(userStory.getId());
    }
}
