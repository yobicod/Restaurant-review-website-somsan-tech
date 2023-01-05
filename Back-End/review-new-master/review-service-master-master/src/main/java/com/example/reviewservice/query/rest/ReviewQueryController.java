package com.example.reviewservice.query.rest;

import com.example.reviewservice.core.ReviewEntity;
import com.example.reviewservice.query.FindReviewByIdQuery;
import com.example.reviewservice.query.FindReviewByStoreTypeQuery;
import com.example.reviewservice.query.FindReviewsQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewQueryController {
    @Autowired
    QueryGateway queryGateway;

    @GetMapping("/getAll")
    public List<ReviewRestModel> getReviews(){
        FindReviewsQuery findReviewsQuery = new FindReviewsQuery();
        List<ReviewRestModel> reviews = queryGateway
                .query(findReviewsQuery, ResponseTypes.multipleInstancesOf(ReviewRestModel.class)).join();
        return reviews;
    }

    @GetMapping("/findById")
    public ResponseEntity<ReviewEntity> getReviewById(@RequestParam String id){
        ReviewEntity review = queryGateway.query(new FindReviewByIdQuery(id), ReviewEntity.class).join();
        if (review == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @GetMapping("/findByStoreType")
    public List<ReviewRestModel> getReviewsByStoreType(@RequestParam String storeType){
        List<ReviewRestModel> reviews = queryGateway
                .query(new FindReviewByStoreTypeQuery(storeType), ResponseTypes.multipleInstancesOf(ReviewRestModel.class)).join();
        return reviews;
    }
}
