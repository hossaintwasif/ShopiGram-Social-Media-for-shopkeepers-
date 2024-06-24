package com.tindering.controller;

import com.tindering.models.Reels;
import com.tindering.models.User;
import com.tindering.service.ReelsService;
import com.tindering.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReelsController {

    @Autowired
    private ReelsService reelsService;

    @Autowired
    private UserService userService;

    @PostMapping("/api/reels")
    public Reels createReels(@RequestBody Reels reels, @RequestHeader("Authorization") String jwt){
        User user=userService.findUserByJwt(jwt);
        Reels createReel = reelsService.createReel(reels,user);
        return createReel;
    }

    @GetMapping("/api/reels")
    public List<Reels> findAllReels(){
        List<Reels> reels=reelsService.findAllReels();
        return reels;
    }

    @GetMapping("/api/reels/user/{userId}")
    public List<Reels> findUserReels(@PathVariable("userId") Integer userId) throws Exception {
        List<Reels> reels=reelsService.findUsersReels(userId);
        return reels;
    }
}
