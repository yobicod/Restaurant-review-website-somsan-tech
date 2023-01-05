package com.example.reviewservice.command;

//import com.example.review.event.ReviewCreatedEvent;
import com.example.reviewservice.core.event.ReviewCreatedEvent;
import com.example.reviewservice.core.event.ReviewUpdatedEvent;
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
    private float rating;
    private int personReview;
    private String phone;
    private boolean ban;

    private int range;
    private boolean delivery;
    private boolean pickUp;
    private String userId;


    //    private String _obid = _id.toString();
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
        this.ban = reviewCreatedEvent.isBan();
        this.userId = reviewCreatedEvent.getUserId();
        this.range = reviewCreatedEvent.getRange();
        this.delivery = reviewCreatedEvent.isDelivery();
        this.pickUp = reviewCreatedEvent.isPickUp();
    }

    @CommandHandler
    public void on(UpdateReviewCommand command){
        ReviewUpdatedEvent reviewUpdatedEvent = new ReviewUpdatedEvent();
        BeanUtils.copyProperties(command, reviewUpdatedEvent);
        AggregateLifecycle.apply(reviewUpdatedEvent);
    }

    @EventSourcingHandler
    public void on(ReviewUpdatedEvent event){
        this._id = event.get_id();
        this.name = event.getName();
        this.branch = event.getBranch();
        this.store_type = event.getStore_type();
        this.description = event.getDescription();
        this.imageId = event.getImageId();
        this.address = event.getAddress();
        this.timeOpen = event.getTimeOpen();
        this.timeClose = event.getTimeClose();
        this.rating = event.getRating();
        this.personReview = event.getPersonReview();
        this.phone = event.getPhone();
        this.ban = event.isBan();
        this.userId = event.getUserId();
        this.range = event.getRange();
        this.delivery = event.isDelivery();
        this.pickUp = event.isPickUp();
    }

}
