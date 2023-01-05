package com.example.reviewservice.query;

import lombok.Data;

@Data
public class FindReviewByStoreTypeQuery {
    private String store_type;

    public FindReviewByStoreTypeQuery(String store_type){
        this.store_type = store_type;
    }
}
