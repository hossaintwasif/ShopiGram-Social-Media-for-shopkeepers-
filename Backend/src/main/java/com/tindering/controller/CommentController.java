package com.tindering.controller;

import com.tindering.models.Comment;
import com.tindering.models.User;
import com.tindering.service.CommentServce;
import com.tindering.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {

    @Autowired
    private CommentServce commentServce;

    @Autowired
    private UserService userService;

    @PostMapping("/api/comments/post/{postid}")
    public Comment creaComment(@RequestBody Comment comment, @RequestHeader("Authorization") String jwt, @PathVariable("postid") Integer postId) throws Exception {
       User user = userService.findUserByJwt(jwt);
       Comment createdComment = commentServce.createComment(comment,postId,user.getId());
        return createdComment;
    }

    @PutMapping("/api/comments/like/{commentId}")
    public Comment likeComment(@RequestHeader("Authorization") String jwt, @PathVariable("commentId") Integer commentId) throws Exception{
        User user = userService.findUserByJwt(jwt);
        Comment likeComment = commentServce.likeComment(commentId,user.getId());
        return likeComment;
    }
}
