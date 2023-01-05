package com.example.reviewservice.query;

import com.example.reviewservice.core.ReviewEntity;
import com.example.reviewservice.core.data.ReviewRepository;
import com.example.reviewservice.query.rest.ReviewRestModel;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReviewQueryHandler {
    private final ReviewRepository reviewRepository;

    public ReviewQueryHandler(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }

    @QueryHandler
    List<ReviewRestModel> findReviews(FindReviewsQuery query){
        List<ReviewRestModel> reviewsRest = new ArrayList<>();
        List<ReviewEntity> storedReviews = reviewRepository.findByReviewBan(query.isBan());
        for (ReviewEntity reviewEntity : storedReviews){
            ReviewRestModel reviewRestModel = new ReviewRestModel();
            BeanUtils.copyProperties(reviewEntity, reviewRestModel);
            reviewsRest.add(reviewRestModel);
        }
        return reviewsRest;
    }

    @QueryHandler
    ReviewEntity handle (FindReviewByIdQuery query){
        ReviewEntity review = reviewRepository
//                .findById(query.getReviewId()).orElse(null);
                .findByReviewId(query.get_id());
        return review;
    }

    @QueryHandler
    List<ReviewRestModel> handle (FindReviewByStoreTypeQuery query){
        List<ReviewRestModel> reviewsRest = new ArrayList<>();
        List<ReviewEntity> storedReviews = reviewRepository
                .findByStoreType(query.getStore_type());
        for (ReviewEntity reviewEntity : storedReviews){
            ReviewRestModel reviewRestModel = new ReviewRestModel();
            BeanUtils.copyProperties(reviewEntity, reviewRestModel);
            reviewsRest.add(reviewRestModel);
        }
        return reviewsRest;

    }

    @QueryHandler
    List<ReviewRestModel> handle (FindByUserIdQuery query){
        List<ReviewRestModel> reviewsRest = new ArrayList<>();
        List<ReviewEntity> storedReviews = reviewRepository
                .findByUserId(query.getUserId());
        for (ReviewEntity reviewEntity : storedReviews){
            ReviewRestModel reviewRestModel = new ReviewRestModel();
            BeanUtils.copyProperties(reviewEntity, reviewRestModel);
            reviewsRest.add(reviewRestModel);
        }
        return reviewsRest;

    }
}
