package com.example.commentservice.command;

import com.example.commentservice.core.event.CommentCreatedEvent;
import com.example.commentservice.core.event.CommentUpdatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class CommentAggregate {
    @AggregateIdentifier
    private String _id;
    private String user;
    private String userid;
    private Integer rating;
    private String description;
    private String recommendMenu;
    private String imageId;
    private String time;
    private Integer like;
    private String reviewId;
    private int report;
    private boolean ban;

    public CommentAggregate() {
    }

    @CommandHandler
    public CommentAggregate(CreateCommentCommand createCommentCommand){
        if(createCommentCommand.getDescription() == null || createCommentCommand.getDescription().isBlank()){
            throw new IllegalArgumentException("Pls Enter Comment");
        }
        if(createCommentCommand.getRecommendMenu() == null || createCommentCommand.getRecommendMenu().isBlank()){
            throw new IllegalArgumentException("Please Enter Recommend Menu");
        }
        CommentCreatedEvent commentCreatedEvent = new CommentCreatedEvent();
        BeanUtils.copyProperties(createCommentCommand, commentCreatedEvent);
        AggregateLifecycle.apply(commentCreatedEvent);
    }

    @CommandHandler
    public void handle(UpdateCommentCommand updateCommentCommand){
        if(updateCommentCommand.getDescription() == null || updateCommentCommand.getDescription().isBlank()){
            throw new IllegalArgumentException("Pls Enter Comment");
        }
        if(updateCommentCommand.getRecommendMenu() == null || updateCommentCommand.getRecommendMenu().isBlank()){
            throw new IllegalArgumentException("Please Enter Recommend Menu");
        }
        CommentUpdatedEvent commentUpdatedEvent = new CommentUpdatedEvent();
        BeanUtils.copyProperties(updateCommentCommand, commentUpdatedEvent);
        AggregateLifecycle.apply(commentUpdatedEvent);
    }

    @EventSourcingHandler
    public void on(CommentCreatedEvent commentCreatedEvent){
        this._id = commentCreatedEvent.get_id();
        this.user = commentCreatedEvent.getUser();
        this.userid = commentCreatedEvent.getUserid();
        this.rating = commentCreatedEvent.getRating();
        this.description = commentCreatedEvent.getDescription();
        this.recommendMenu = commentCreatedEvent.getRecommendMenu();
        this.imageId = commentCreatedEvent.getImageId();
        this.time = commentCreatedEvent.getTime();
        this.like = commentCreatedEvent.getLike();
        this.reviewId = commentCreatedEvent.getReviewId();
        this.report = commentCreatedEvent.getReport();
        this.ban = commentCreatedEvent.isBan();
    }

    @EventSourcingHandler
    public void on(CommentUpdatedEvent commentUpdatedEvent){
        this._id = commentUpdatedEvent.get_id();
        this.user = commentUpdatedEvent.getUser();
        this.userid = commentUpdatedEvent.getUserid();
        this.rating = commentUpdatedEvent.getRating();
        this.description = commentUpdatedEvent.getDescription();
        this.recommendMenu = commentUpdatedEvent.getRecommendMenu();
        this.imageId = commentUpdatedEvent.getImageId();
        this.time = commentUpdatedEvent.getTime();
        this.like = commentUpdatedEvent.getLike();
        this.reviewId = commentUpdatedEvent.getReviewId();
        this.report = commentUpdatedEvent.getReport();
        this.ban = commentUpdatedEvent.isBan();
    }
}
