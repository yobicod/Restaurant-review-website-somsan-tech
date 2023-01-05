package com.example.commentservice.query;

import lombok.Data;

@Data
public class FindCommentByReviewIdQuery {

    private String reviewId;

    public FindCommentByReviewIdQuery(String reviewId) {
        this.reviewId = reviewId;
    }
}
