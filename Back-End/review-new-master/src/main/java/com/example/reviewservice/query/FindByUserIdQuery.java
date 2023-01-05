package com.example.reviewservice.query;

import lombok.Data;

@Data
public class FindByUserIdQuery {
    private String userId;

    public FindByUserIdQuery(String userId) {
        this.userId = userId;
    }
}
