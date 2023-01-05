package com.example.reviewservice.query;

import lombok.Data;

@Data
public class FindReviewsQuery {
    private boolean ban;

    public FindReviewsQuery(boolean ban) {
        this.ban = ban;
    }
}
