package com.tindering.service;

import com.tindering.models.Comment;
import com.tindering.models.User;

public interface CommentServce {

    public Comment createComment(Comment content, Integer postId, Integer userId) throws Exception;

    public Comment likeComment(Integer CommentId,Integer userId) throws Exception;

    public Comment findCommentById(Integer commentId) throws Exception;
}
