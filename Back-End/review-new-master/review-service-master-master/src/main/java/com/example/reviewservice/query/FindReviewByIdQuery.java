package com.example.reviewservice.query;

import lombok.Data;

@Data
public class FindReviewByIdQuery {
    private String _id;

    public FindReviewByIdQuery(String _id){
        this._id = _id;
    }
}
