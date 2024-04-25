package com.ozcicek.questionapp.services;

import com.ozcicek.questionapp.entities.Like;
import com.ozcicek.questionapp.model.requests.LikeCreateRequest;
import com.ozcicek.questionapp.model.responses.LikeResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LikeService {
    List<LikeResponse> getAllLikeWithParam(Long postId, Long userId);

    Like saveLike(LikeCreateRequest likeCreateRequest);

    void deleteLike(Long likeId);

    Like getLike(Long likeId);
}
