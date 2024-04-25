package com.ozcicek.questionapp.services.Impl;

import com.ozcicek.questionapp.entities.Like;
import com.ozcicek.questionapp.entities.Post;
import com.ozcicek.questionapp.entities.User;
import com.ozcicek.questionapp.model.requests.LikeCreateRequest;
import com.ozcicek.questionapp.model.responses.LikeResponse;
import com.ozcicek.questionapp.repository.LikeRepository;
import com.ozcicek.questionapp.services.LikeService;
import com.ozcicek.questionapp.services.PostService;
import com.ozcicek.questionapp.services.UserService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class LikeServiceImpl implements LikeService {
    private final LikeRepository likeRepository;
    private final UserService userService;
    private  PostService postService;

    public LikeServiceImpl(LikeRepository likeRepository, UserService userService) {
        this.likeRepository = likeRepository;
        this.userService = userService;
    }

    public void setPostService(PostService postService){
        this.postService = postService;
    }


    @Override
    public List<LikeResponse> getAllLikeWithParam(Long postId, Long userId) {
        List<Like> list;
        if (Objects.nonNull(postId) && Objects.nonNull(userId)){
            list = likeRepository.findByUserIdAndPostId(userId,postId);
        }else if(Objects.nonNull(postId)){
            list = likeRepository.findByPostId(postId);
        } else if (Objects.nonNull(userId)) {
            list = likeRepository.findByUserId(userId);
        }else{
            list = likeRepository.findAll();
        }
        return list.stream().map(like -> new LikeResponse(like)).collect(Collectors.toList());
    }

    @Override
    public Like saveLike(LikeCreateRequest likeCreateRequest) {
        User user = userService.getUser(likeCreateRequest.getUserId());
        Post post = postService.getPost(likeCreateRequest.getPostId());
        if (Objects.nonNull(user) && Objects.nonNull(post)){
            Like toSave = new Like();
            toSave.setId(likeCreateRequest.getId());
            toSave.setUser(user);
            toSave.setPost(post);
            likeRepository.save(toSave);
        }
        return null;
    }

    @Override
    public void deleteLike(Long likeId) {
        Like like = likeRepository.findById(likeId).orElse(null);
        if (Objects.nonNull(like))
            likeRepository.delete(like);
    }

    @Override
    public Like getLike(Long likeId) {
        return likeRepository.findById(likeId).orElse(null);
    }


}
