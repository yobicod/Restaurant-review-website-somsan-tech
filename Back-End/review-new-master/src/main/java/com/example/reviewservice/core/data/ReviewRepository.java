package com.example.reviewservice.core.data;

import com.example.reviewservice.core.ReviewEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ReviewRepository extends MongoRepository<ReviewEntity, String> {
    @Query(value = "{'ban': false}")
    List<ReviewEntity> findByReviewBan(boolean ban);

    @Query(value = "{'_id': ?0,'ban': false}")
    ReviewEntity findByReviewId(String reviewId);
    @Query(value = "{'store_type': ?0, 'ban': false}")
    List<ReviewEntity> findByStoreType(String type);
 
    @Query(value = "{'userId':  ?0}")
    List<ReviewEntity> findByUserId(String userId);
}
