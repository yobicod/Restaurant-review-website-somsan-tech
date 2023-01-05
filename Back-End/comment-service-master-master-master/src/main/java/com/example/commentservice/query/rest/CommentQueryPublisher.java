package com.example.commentservice.query.rest;

import com.example.commentservice.core.CommentEntity;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/comment")
public class CommentQueryPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping
    public List<CommentRestModel> getComments(){
        Object result = rabbitTemplate.convertSendAndReceive("CommentExchange", "allcomment", "hello");
        return ((List<CommentRestModel>) result);
    }

    @GetMapping("/findByReviewId")
    public List<CommentRestModel> getCommentByReviewId(@RequestParam String reviewId){
        Object result = rabbitTemplate.convertSendAndReceive("CommentExchange", "idcomment", reviewId);
        return (List<CommentRestModel>) result;

    }

    @GetMapping("/findById")
    private CommentEntity getCommentById(@RequestParam String id){
        Object result = rabbitTemplate.convertSendAndReceive("CommentExchange", "commentbyid", id);
        return (CommentEntity) result;
    }

    @GetMapping("/findByUserId")
    public List<CommentRestModel> getCommentByUserId(@RequestParam String userId){
        Object result = rabbitTemplate.convertSendAndReceive("CommentExchange", "commentbyuserid", userId);
        return (List<CommentRestModel>) result;
    }
}
