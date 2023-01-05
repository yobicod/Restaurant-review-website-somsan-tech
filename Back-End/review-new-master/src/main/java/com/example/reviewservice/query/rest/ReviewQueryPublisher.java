package com.example.reviewservice.query.rest;

import com.example.reviewservice.core.ReviewEntity;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/review")
public class ReviewQueryPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/getAll")
    public List<ReviewRestModel> getReviews(){
        Object result = rabbitTemplate.convertSendAndReceive("ReviewExchange", "allreview", "hello");
        return ((List<ReviewRestModel>) result);
    }

    @GetMapping("/findById")
    public ReviewEntity getReviewById(@RequestParam String id){
        Object result = rabbitTemplate.convertSendAndReceive("ReviewExchange", "reviewbyid", id);
        return (ReviewEntity) result;
    }

    @GetMapping("/findByStoreType")
    public List<ReviewRestModel> getReviewsByStoreType(@RequestParam String storeType){
        Object result = rabbitTemplate.convertSendAndReceive("ReviewExchange", "reviewbytype", storeType);
        return ((List<ReviewRestModel>) result);
    }

    @GetMapping("/findByUserId")
    public List<ReviewRestModel> getReviewByUserId(@RequestParam String userId){
        Object result = rabbitTemplate.convertSendAndReceive("ReviewExchange", "reviewbyuserid", userId);
        return ((List<ReviewRestModel>) result);
    }

}
