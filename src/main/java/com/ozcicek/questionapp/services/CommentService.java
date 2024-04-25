package com.ozcicek.questionapp.services;

import com.ozcicek.questionapp.entities.Comment;
import com.ozcicek.questionapp.model.requests.CommentCreateRequest;
import com.ozcicek.questionapp.model.requests.CommentUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CommentService {
    List<Comment> getAllCommentsWithParam(Optional<Long> postId, Long userId);

    Comment getComment(Long commentId);

    Comment updateComment(Long commentId, CommentUpdateRequest commentUpdateRequest);

    Comment saveComment(CommentCreateRequest commentCreateRequest);

    void deleteComment(Long commentId);
}
