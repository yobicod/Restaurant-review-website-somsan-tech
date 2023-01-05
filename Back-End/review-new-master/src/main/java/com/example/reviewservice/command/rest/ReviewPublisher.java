package com.example.reviewservice.command.rest;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/review")
public class ReviewPublisher {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping
    public String createReview(@RequestBody CreateReviewModel model){
        Object result = rabbitTemplate.convertSendAndReceive("ReviewExchange", "creview", model);
        return ((String) result);
    }

    @PutMapping(value = "/update")
    public String update(@RequestBody UpdateReviewModel model){
        Object result = rabbitTemplate.convertSendAndReceive("ReviewExchange", "ureview", model);
        return "Update Complete";
    }
}
