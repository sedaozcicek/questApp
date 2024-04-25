package com.ozcicek.questionapp.services;

import com.ozcicek.questionapp.entities.Post;
import com.ozcicek.questionapp.model.requests.PostCreateRequest;
import com.ozcicek.questionapp.model.requests.PostUpdateRequest;
import com.ozcicek.questionapp.model.responses.PostResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PostService {
    List<PostResponse> getAllPosts(Optional<Long> userId);

    Post savePost(PostCreateRequest newPostRequest);

    Post getPost(Long postId);

    Post updatePost(Long postId, PostUpdateRequest postUpdateRequest);

    void deletePost(Long postId);
}
