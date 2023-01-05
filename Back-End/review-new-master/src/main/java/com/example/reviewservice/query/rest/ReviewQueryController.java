package com.example.reviewservice.query.rest;

import com.example.reviewservice.core.ReviewEntity;
import com.example.reviewservice.query.FindByUserIdQuery;
import com.example.reviewservice.query.FindReviewByIdQuery;
import com.example.reviewservice.query.FindReviewByStoreTypeQuery;
import com.example.reviewservice.query.FindReviewsQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Service
public class ReviewQueryController {
    @Autowired
    QueryGateway queryGateway;

    @RabbitListener(queues = "GetAllReview")
    public List<ReviewRestModel> getReviews(String messange){
        FindReviewsQuery findReviewsQuery = new FindReviewsQuery(false);
        List<ReviewRestModel> reviews = queryGateway
                .query(findReviewsQuery, ResponseTypes.multipleInstancesOf(ReviewRestModel.class)).join();
        return reviews;
    }

    @RabbitListener(queues = "GetReviewById")
    public ReviewEntity getReviewById(String id){
        ReviewEntity review = queryGateway.query(new FindReviewByIdQuery(id), ReviewEntity.class).join();
        if (review == null){
            return new ReviewEntity();
        }
        return review;
    }

    @RabbitListener(queues = "GetReviewByStoreType")
    public List<ReviewRestModel> getReviewsByStoreType(String storeType){
        List<ReviewRestModel> reviews = queryGateway
                .query(new FindReviewByStoreTypeQuery(storeType), ResponseTypes.multipleInstancesOf(ReviewRestModel.class)).join();
        return reviews;
    }

    @RabbitListener(queues = "GetReviewByUserId")
    public List<ReviewRestModel> getReviewByUserId(String userId){
        List<ReviewRestModel> reviews = queryGateway
                .query(new FindByUserIdQuery(userId), ResponseTypes.multipleInstancesOf(ReviewRestModel.class)).join();
        return reviews;
    }


}
