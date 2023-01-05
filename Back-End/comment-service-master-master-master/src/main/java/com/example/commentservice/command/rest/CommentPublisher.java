package com.example.commentservice.command.rest;

import com.example.commentservice.core.CommentEntity;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/comment")
public class CommentPublisher {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping
    public String createComment(@RequestBody CreateCommentModel model){
        Object result = rabbitTemplate.convertSendAndReceive("CommentExchange", "ccomment", model);

        String mess = model.getReviewId() + "__" + model.getRating();

        rabbitTemplate.convertAndSend("ReviewExchange", "uprating", mess);
        return (String) result;
    }

    @PutMapping
    public String updateComment(@RequestBody UpdateCommentModel model){
        Object query = rabbitTemplate.convertSendAndReceive("CommentExchange", "commentbyid", model.get_id());
        CommentEntity old_result = (CommentEntity) query;
        Object result = rabbitTemplate.convertSendAndReceive("CommentExchange", "ucomment", model);
        String mess = model.getReviewId() + "__" + (model.getRating() - old_result.getRating());
        rabbitTemplate.convertAndSend("ReviewExchange", "ratingold", mess);
        return "Update Complete";
    }

    @PutMapping(value = "/addlike")
    public void addlike(@RequestParam String commentId){
        Object query = rabbitTemplate.convertSendAndReceive("CommentExchange", "commentbyid", commentId);
//        UpdateCommentModel model = (UpdateCommentModel) query;
        rabbitTemplate.convertAndSend("CommentExchange", "addlike", query);
    }

}
