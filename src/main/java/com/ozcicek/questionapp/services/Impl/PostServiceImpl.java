package com.ozcicek.questionapp.services.Impl;

import com.ozcicek.questionapp.entities.Post;
import com.ozcicek.questionapp.entities.User;
import com.ozcicek.questionapp.model.requests.PostCreateRequest;
import com.ozcicek.questionapp.model.requests.PostUpdateRequest;
import com.ozcicek.questionapp.model.responses.LikeResponse;
import com.ozcicek.questionapp.model.responses.PostResponse;
import com.ozcicek.questionapp.repository.PostRepository;
import com.ozcicek.questionapp.services.LikeService;
import com.ozcicek.questionapp.services.PostService;
import com.ozcicek.questionapp.services.UserService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@Component
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserService userService;

    private LikeService likeService;

    public PostServiceImpl(PostRepository postRepository, UserService userService, LikeService likeService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    public void setLikeService(LikeService likeService){
        this.likeService = likeService;
    }


    @Override
    public List<PostResponse> getAllPosts(Optional<Long> userId) {
        List<Post> list;
        if (userId.isPresent()){
            list = postRepository.findByUserId(userId.get());
        }else {
            list = postRepository.findAll();
        }
        return list.stream().map(post -> {
                List<LikeResponse> likes = likeService.getAllLikeWithParam(post.getId(), null);
                return new PostResponse(post,likes);}).collect(Collectors.toList());
    }

    @Override
    public Post savePost(PostCreateRequest newPostRequest) {
        User user = userService.getUser(newPostRequest.getUserId());
        if (Objects.nonNull(user)){
            Post toSave = new Post();
            toSave.setTitle(newPostRequest.getTitle());
            toSave.setUser(user);
            toSave.setText(newPostRequest.getText());
            toSave.setId(newPostRequest.getId());
            postRepository.save(toSave);
        }
        return null;
    }

    @Override
    public Post getPost(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    @Override
    public Post updatePost(Long postId, PostUpdateRequest postUpdateRequest) {
        Post post = postRepository.findById(postId).orElse(null);

        if (Objects.nonNull(post)){
            setText(postUpdateRequest, post);
            setTitle(postUpdateRequest, post);
            return postRepository.save(post);
        }
        return null;
    }

    @Override
    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId).orElse(null);
        if (Objects.nonNull(post))
            postRepository.delete(post);
    }

    private static void setTitle(PostUpdateRequest postUpdateRequest, Post post) {
        if (Objects.nonNull(postUpdateRequest.getTitle())){
            post.setTitle(postUpdateRequest.getTitle());
        }
    }

    private static void setText(PostUpdateRequest postUpdateRequest, Post post) {
        if (Objects.nonNull(postUpdateRequest.getText())){
            post.setText(postUpdateRequest.getText());
        }
    }
}
