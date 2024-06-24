package com.tindering.service;

import com.tindering.models.Story;
import com.tindering.models.User;

import java.util.List;

public interface StoryService {

    public Story createStory(Story story, Integer userId) throws Exception;

    public List<Story> findStoryByUserId(Integer userId) throws Exception;
}
