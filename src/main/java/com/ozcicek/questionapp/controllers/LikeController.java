package com.ozcicek.questionapp.controllers;

import com.ozcicek.questionapp.entities.Like;
import com.ozcicek.questionapp.model.requests.LikeCreateRequest;
import com.ozcicek.questionapp.model.responses.LikeResponse;
import com.ozcicek.questionapp.services.LikeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/likes")
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping
    public List<LikeResponse> getAllLikeWithParam(@RequestParam(required = false) Long postId, @RequestParam(required = false) Long userId){
        return likeService.getAllLikeWithParam(postId,userId);
    }

    @GetMapping("/{likeId}")
    public Like getLike(@PathVariable Long likeId){
        return likeService.getLike(likeId);
    }

    @PostMapping
    public Like createLike(@RequestBody LikeCreateRequest likeCreateRequest){
        return likeService.saveLike(likeCreateRequest);
    }

    @DeleteMapping("/{likeId}")
    public void deleteLike(@PathVariable Long likeId){
        likeService.deleteLike(likeId);
    }
}
