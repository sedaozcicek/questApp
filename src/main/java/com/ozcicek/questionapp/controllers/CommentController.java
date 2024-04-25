package com.ozcicek.questionapp.controllers;

import com.ozcicek.questionapp.entities.Comment;
import com.ozcicek.questionapp.entities.Post;
import com.ozcicek.questionapp.entities.User;
import com.ozcicek.questionapp.model.requests.CommentCreateRequest;
import com.ozcicek.questionapp.model.requests.CommentUpdateRequest;
import com.ozcicek.questionapp.services.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @GetMapping
    public List<Comment> getAllComments(@RequestParam Optional<Long> postId, @RequestParam(required = false) Long userId){
        return commentService.getAllCommentsWithParam(postId,userId);
    }

    @GetMapping("/{commentId}")
    public Comment getComment(@PathVariable Long commentId){
        return commentService.getComment(commentId);
    }

    @PostMapping
    public Comment createComment(@RequestBody CommentCreateRequest commentCreateRequest){
        return commentService.saveComment(commentCreateRequest);
    }

    @PutMapping("/{commentId}")
    public Comment updateComment(@PathVariable Long commentId, @RequestBody CommentUpdateRequest commentUpdateRequest){
        return commentService.updateComment(commentId,commentUpdateRequest);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long commentId){
        commentService.deleteComment(commentId);
    }
}
