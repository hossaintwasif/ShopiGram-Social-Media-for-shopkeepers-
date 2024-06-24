package com.tindering.service;

import com.tindering.models.Reels;
import com.tindering.models.User;

import java.util.List;

public interface ReelsService {

    public Reels createReel(Reels reels, User user);

    public List<Reels> findAllReels();

    public List<Reels> findUsersReels(Integer userId) throws Exception;
}
