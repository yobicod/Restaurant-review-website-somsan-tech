package com.example.reviewservice.query;

import com.example.reviewservice.core.ReviewEntity;
import com.example.reviewservice.core.data.ReviewRepository;
import com.example.reviewservice.core.event.ReviewCreatedEvent;
import com.example.reviewservice.core.event.ReviewUpdatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ReviewEventsHandler {
    private final ReviewRepository reviewRepository;

    public ReviewEventsHandler(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }

    @EventHandler
    public void on(ReviewCreatedEvent event){
        ReviewEntity reviewEntity = new ReviewEntity();
        BeanUtils.copyProperties(event, reviewEntity);
        reviewRepository.save(reviewEntity);
    }

    @EventHandler
    public void on(ReviewUpdatedEvent event){
        ReviewEntity reviewEntity = new ReviewEntity();
        BeanUtils.copyProperties(event, reviewEntity);
        reviewRepository.save(reviewEntity);
    }



}
