package com.example.commentservice.query;

import lombok.Data;

@Data
public class FindCommentByUserIdQuery {
    private String userId;

    public FindCommentByUserIdQuery(String userId) {
        this.userId = userId;
    }
}
