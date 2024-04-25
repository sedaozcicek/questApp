package com.ozcicek.questionapp.services.Impl;

import com.ozcicek.questionapp.entities.Comment;
import com.ozcicek.questionapp.entities.Post;
import com.ozcicek.questionapp.entities.User;
import com.ozcicek.questionapp.model.requests.CommentCreateRequest;
import com.ozcicek.questionapp.model.requests.CommentUpdateRequest;
import com.ozcicek.questionapp.repository.CommentRepository;
import com.ozcicek.questionapp.services.CommentService;
import com.ozcicek.questionapp.services.PostService;
import com.ozcicek.questionapp.services.UserService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final PostService postService;

    public CommentServiceImpl(CommentRepository commentRepository, UserService userService, PostService postService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.postService = postService;
    }

    @Override
    public List<Comment> getAllCommentsWithParam(Optional<Long> postId, Long userId) {
        if (Objects.nonNull(userId) && postId.isPresent()){
            return commentRepository.findByUserIdAndPostId(userId,postId.get());
        }else if (Objects.nonNull(userId)){
            return commentRepository.findByUserId(userId);
        }else if(postId.isPresent()){
            return commentRepository.findByPostId(postId.get());
        }else
            return commentRepository.findAll();
    }

    @Override
    public Comment getComment(Long commentId) {
       return commentRepository.findById(commentId).orElse(null);
    }

    @Override
    public Comment updateComment(Long commentId, CommentUpdateRequest commentUpdateRequest) {
        Comment comment = commentRepository.findById(commentId).orElse(null);
        if (Objects.nonNull(comment)){
            comment.setText(commentUpdateRequest.getText());
            commentRepository.save(comment);
        }
        return comment;
    }

    @Override
    public Comment saveComment(CommentCreateRequest commentCreateRequest) {
        User user = userService.getUser(commentCreateRequest.getUserId());
        Post post = postService.getPost(commentCreateRequest.getPostId());
        if (Objects.nonNull(user) && Objects.nonNull(post)){
            Comment toSave = new Comment();
            toSave.setUser(user);
            toSave.setPost(post);
            toSave.setId(commentCreateRequest.getId());
            toSave.setText(commentCreateRequest.getText());
            return commentRepository.save(toSave);
        }
        return null;
    }

    @Override
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElse(null);
        if (Objects.nonNull(comment)){
            commentRepository.delete(comment);
        }
    }


}
