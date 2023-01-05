package com.example.commentservice.command.rest;

import com.example.commentservice.command.CreateCommentCommand;
import com.example.commentservice.command.UpdateCommentCommand;
import com.example.commentservice.core.CommentEntity;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

//@RestController
//@RequestMapping("/comment")
@Service
public class CommentController {

    private final CommandGateway commandGateway;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public CommentController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

//    @PostMapping
    @RabbitListener(queues = "CreateCommentQueue")
    public String createComment(CreateCommentModel model){
        CreateCommentCommand command = CreateCommentCommand.builder()
                ._id(UUID.randomUUID().toString())
                .user(model.getUser())
                .userid(model.getUserid())
                .rating(model.getRating())
                .recommendMenu(model.getRecommendMenu())
                .description(model.getDescription())
                .imageId(model.getImageId())
                .time(model.getTime())
                .like(model.getLike())
                .reviewId(model.getReviewId())
                .ban(model.isBan())
                .report(model.getReport())
                .build();
        String result;
        try{
            result = commandGateway.sendAndWait(command);
        }catch (Exception e){
            result = e.getLocalizedMessage();
        }
        return result;
    }


//    @PutMapping
    @RabbitListener(queues = "UpdateCommentQueue")
    public String updateComment(UpdateCommentModel model){
        UpdateCommentCommand command = UpdateCommentCommand.builder()
                ._id(model.get_id())
                .user(model.getUser())
                .userid(model.getUserid())
                .rating(model.getRating())
                .recommendMenu(model.getRecommendMenu())
                .description(model.getDescription())
                .imageId(model.getImageId())
                .time(model.getTime())
                .like(model.getLike())
                .reviewId(model.getReviewId())
                .ban(model.isBan())
                .report(model.getReport())
                .build();
        String result;
        try{
            String idUpdate = commandGateway.sendAndWait(command);
            result = "Update Complete ID: " + idUpdate;
        }catch (Exception e){
            result = e.getLocalizedMessage();
        }
        return result;
    }

    @RabbitListener(queues = "BanCommentQueue")
    public void BanComment(String itemId){
        Object query = rabbitTemplate.convertSendAndReceive("CommentExchange", "commentbyid", itemId);
        CommentEntity model = (CommentEntity) query;
        UpdateCommentCommand command = UpdateCommentCommand.builder()
                ._id(model.get_id())
                .user(model.getUser())
                .userid(model.getUserid())
                .rating(model.getRating())
                .recommendMenu(model.getRecommendMenu())
                .description(model.getDescription())
                .imageId(model.getImageId())
                .time(model.getTime())
                .like(model.getLike())
                .reviewId(model.getReviewId())
                .ban(true)
                .report(model.getReport())
                .build();
        String result;
        try {
            result = commandGateway.sendAndWait(command);
        }catch (Exception e){
            result = e.getLocalizedMessage();
        }
    }

    @RabbitListener(queues = "AddLike")
    public void addLike(CommentEntity model){
        UpdateCommentCommand command = UpdateCommentCommand.builder()
                ._id(model.get_id())
                .user(model.getUser())
                .userid(model.getUserid())
                .rating(model.getRating())
                .recommendMenu(model.getRecommendMenu())
                .description(model.getDescription())
                .imageId(model.getImageId())
                .time(model.getTime())
                .like(model.getLike() + 1)
                .reviewId(model.getReviewId())
                .ban(model.isBan())
                .report(model.getReport())
                .build();
//        System.out.println(command.getLike());
        String result;
        try {
            result = commandGateway.sendAndWait(command);
        }catch (Exception e){
            result = e.getLocalizedMessage();
        }
    }


}
