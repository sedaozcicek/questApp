package com.ozcicek.questionapp.model.responses;

import com.ozcicek.questionapp.entities.Like;
import lombok.Data;

@Data
public class LikeResponse {
    Long id;
    Long userId;
    Long postId;


    public LikeResponse(Like entity){
        this.id = entity.getId();
        this.postId = entity.getPost().getId();
        this.userId = entity.getUser().getId();
    }
}
