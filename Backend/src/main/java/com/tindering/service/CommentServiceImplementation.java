package com.tindering.service;

import com.tindering.models.Comment;
import com.tindering.models.Post;
import com.tindering.models.User;
import com.tindering.repository.CommentRepository;
import com.tindering.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
@Service
public class CommentServiceImplementation implements CommentServce{

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public Comment createComment(Comment content, Integer postId, Integer userId) throws Exception {

        User user = userService.findUserById(userId);
        Post post = postService.findPostById(postId);

        content.setUser(user);
        content.setContent(content.getContent());
        content.setCreatedAt(LocalDateTime.now());

        Comment saveComment = commentRepository.save(content);
        post.getComments().add(saveComment);
        postRepository.save(post);

        return saveComment;
    }

    @Override
    public Comment likeComment(Integer CommentId, Integer userId) throws Exception {
        Comment comment = findCommentById(CommentId);
        User user = userService.findUserById(userId);
        if(!comment.getCommentLike().contains(user)){
            comment.getCommentLike().add(user);
        }
        else comment.getCommentLike().remove(user);

        return commentRepository.save(comment);
    }

    @Override
    public Comment findCommentById(Integer commentId) throws Exception {
        Optional<Comment> opt=commentRepository.findById(commentId);
        if(opt.isEmpty()){
            throw new Exception("Comment not exist !!!");
        }
        return opt.get();
    }
}
