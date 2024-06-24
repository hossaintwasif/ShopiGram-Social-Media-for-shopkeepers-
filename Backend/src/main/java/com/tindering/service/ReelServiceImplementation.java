package com.tindering.service;

import com.tindering.models.Reels;
import com.tindering.models.User;
import com.tindering.repository.ReelsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReelServiceImplementation implements ReelsService{

    @Autowired
    private ReelsRepository reelsRepository;

    @Autowired
    private UserService userService;

    @Override
    public Reels createReel(Reels reels, User user) {

        Reels createReel = new Reels();
        createReel.setTitle(reels.getTitle());
        createReel.setUser(user);
        createReel.setVideo(reels.getVideo());

        return reelsRepository.save(createReel);
    }

    @Override
    public List<Reels> findAllReels() {
        return reelsRepository.findAll();
    }

    @Override
    public List<Reels> findUsersReels(Integer userId) throws Exception {
        User reels = userService.findUserById(userId);
        return reelsRepository.findByUserId(reels.getId());
    }
}
