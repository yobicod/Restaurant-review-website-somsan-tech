package com.example.reviewservice.command;

//import com.example.review.event.ReviewCreatedEvent;
import com.example.reviewservice.core.event.ReviewCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class ReviewAggregate {
    @AggregateIdentifier
    private String _id;
    private String name;
    private String branch;
    private String store_type;
    private String description;
    private String imageId;
    private String address;
    private String timeOpen;
    private String timeClose;
    private int rating;
    private int personReview;
    private String phone;

    public ReviewAggregate() {
    }

    @CommandHandler
    public ReviewAggregate(CreateReviewCommand createReviewCommand){
        if(createReviewCommand.getName() == null || createReviewCommand.getName().isBlank()){
            throw new IllegalArgumentException("Name is not empty");
        }
        if(createReviewCommand.getStore_type() == null || createReviewCommand.getStore_type().isBlank()){
            throw new IllegalArgumentException("Type can't not be null");
        }
        if(createReviewCommand.getAddress() == null || createReviewCommand.getAddress().isBlank()){
            throw new IllegalArgumentException("Insert Open Time");
        }
        if(createReviewCommand.getTimeOpen() == null || createReviewCommand.getTimeClose().isBlank()){
            throw new IllegalArgumentException("Insert Close Time");
        }

        ReviewCreatedEvent reviewCreatedEvent = new ReviewCreatedEvent();
        BeanUtils.copyProperties(createReviewCommand, reviewCreatedEvent);
        AggregateLifecycle.apply(reviewCreatedEvent);
    }

    @EventSourcingHandler
    public void on(ReviewCreatedEvent reviewCreatedEvent){
        this._id = reviewCreatedEvent.get_id();
        this.name = reviewCreatedEvent.getName();
        this.branch = reviewCreatedEvent.getBranch();
        this.store_type = reviewCreatedEvent.getStore_type();
        this.description = reviewCreatedEvent.getDescription();
        this.imageId = reviewCreatedEvent.getImageId();
        this.address = reviewCreatedEvent.getAddress();
        this.timeOpen = reviewCreatedEvent.getTimeOpen();
        this.timeClose = reviewCreatedEvent.getTimeClose();
        this.rating = reviewCreatedEvent.getRating();
        this.personReview = reviewCreatedEvent.getPersonReview();
        this.phone = reviewCreatedEvent.getPhone();
    }

}
