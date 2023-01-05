package com.example.reviewservice.command.rest;


import com.example.reviewservice.command.CreateReviewCommand;
import com.example.reviewservice.command.UpdateReviewCommand;
import com.example.reviewservice.core.ReviewEntity;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.UUID;


@Service
public class ReviewCommandController {

    private final CommandGateway commandGateway;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public ReviewCommandController(CommandGateway commandGateway){
        this.commandGateway = commandGateway;
    }

    @RabbitListener(queues = "CreateReviewQueue")
    public String createReview(CreateReviewModel model){
        CreateReviewCommand command = CreateReviewCommand.builder()
                ._id(UUID.randomUUID().toString())
                .name(model.getName())
                .branch(model.getBranch())
                .store_type(model.getStore_type())
                .description(model.getDescription())
                .imageId(model.getImageId())
                .address(model.getAddress())
                .timeOpen(model.getTimeOpen())
                .timeClose(model.getTimeClose())
                .personReview(model.getPersonReview())
                .rating(model.getRating())
                .phone(model.getPhone())
                .ban(model.isBan())
                .range(model.getRange())
                .delivery(model.isDelivery())
                .pickUp(model.isPickUp())
                .userId(model.getUserId())
                .build();

        String result;
        try {
            result = commandGateway.sendAndWait(command);
        }catch (Exception e){
            result = e.getLocalizedMessage();
        }
        return result;
    }

    @RabbitListener(queues = "UpdateReviewQueue")
    public String update(UpdateReviewModel model){
        UpdateReviewCommand command = UpdateReviewCommand.builder()
                ._id(model.get_id())
                .name(model.getName())
                .branch(model.getBranch())
                .store_type(model.getStore_type())
                .description(model.getDescription())
                .imageId(model.getImageId())
                .address(model.getAddress())
                .timeOpen(model.getTimeOpen())
                .timeClose(model.getTimeClose())
                .personReview(model.getPersonReview())
                .rating(model.getRating())
                .phone(model.getPhone())
                .rating(model.getRating())
                .ban(model.isBan())
                .range(model.getRange())
                .delivery(model.isDelivery())
                .pickUp(model.isPickUp())
                .userId(model.getUserId())
                .build();

        String result;
        try {
            String idUpdate = commandGateway.sendAndWait(command);
            result = "Update Complete : " + idUpdate;
        }catch (Exception e){
            result = e.getLocalizedMessage();
        }
        return result;
    }
    @RabbitListener(queues = "UpdateRatingQueue")
    public void updateRating(String messange){
//        System.out.println("SS");
        String[] data = messange.split("__");
//        System.out.println(data[0] + ":" +data[1]);
        Object query = rabbitTemplate.convertSendAndReceive("ReviewExchange", "reviewbyid", data[0]);
        ReviewEntity model = (ReviewEntity) query;
        UpdateReviewCommand command = UpdateReviewCommand.builder()
                ._id(model.get_id())
                .name(model.getName())
                .branch(model.getBranch())
                .store_type(model.getStore_type())
                .description(model.getDescription())
                .imageId(model.getImageId())
                .address(model.getAddress())
                .timeOpen(model.getTimeOpen())
                .timeClose(model.getTimeClose())
                .personReview(model.getPersonReview() + 1)
                .phone(model.getPhone())
                .rating(model.getRating() + Float.parseFloat(data[1]))
                .ban(model.isBan())
                .range(model.getRange())
                .delivery(model.isDelivery())
                .pickUp(model.isPickUp())
                .userId(model.getUserId())
                .build();

        String result;
        try {
            result = commandGateway.sendAndWait(command);
        }catch (Exception e){
            result = e.getLocalizedMessage();
        }
    }
    @RabbitListener(queues = "UpdateRatingWithOldCommentQueue")
    public void updateWithOldCommentRating(String messange){
        String[] data = messange.split("__");
        Object query = rabbitTemplate.convertSendAndReceive("ReviewExchange", "reviewbyid", data[0]);
        ReviewEntity model = (ReviewEntity) query;
        UpdateReviewCommand command = UpdateReviewCommand.builder()
                ._id(model.get_id())
                .name(model.getName())
                .branch(model.getBranch())
                .store_type(model.getStore_type())
                .description(model.getDescription())
                .imageId(model.getImageId())
                .address(model.getAddress())
                .timeOpen(model.getTimeOpen())
                .timeClose(model.getTimeClose())
                .personReview(model.getPersonReview())
                .phone(model.getPhone())
                .rating(model.getRating() + Float.parseFloat(data[1]))
                .ban(model.isBan())
                .range(model.getRange())
                .delivery(model.isDelivery())
                .pickUp(model.isPickUp())
                .userId(model.getUserId())
                .build();

        String result;
        try {
            result = commandGateway.sendAndWait(command);
        }catch (Exception e){
            result = e.getLocalizedMessage();
        }
    }
    @RabbitListener(queues = "BanReviewQueue")
    public void BanReview(String itemId){
        Object query = rabbitTemplate.convertSendAndReceive("ReviewExchange", "reviewbyid", itemId);
        ReviewEntity model = (ReviewEntity) query;
        UpdateReviewCommand command = UpdateReviewCommand.builder()
                ._id(model.get_id())
                .name(model.getName())
                .branch(model.getBranch())
                .store_type(model.getStore_type())
                .description(model.getDescription())
                .imageId(model.getImageId())
                .address(model.getAddress())
                .timeOpen(model.getTimeOpen())
                .timeClose(model.getTimeClose())
                .personReview(model.getPersonReview())
                .phone(model.getPhone())
                .rating(model.getRating())
                .ban(true)
                .range(model.getRange())
                .delivery(model.isDelivery())
                .pickUp(model.isPickUp())
                .userId(model.getUserId())
                .build();
        String result;
        try {
            result = commandGateway.sendAndWait(command);
        }catch (Exception e){
            result = e.getLocalizedMessage();
        }
    }

}