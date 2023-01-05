package com.example.reviewservice.command;


import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@Data
public class CreateReviewCommand {
    @TargetAggregateIdentifier
    private final String _id;
    private final String name;
    private final String branch;
    private final String store_type;
    private final String description;
    private final String imageId;
    private final String address;
    private final String timeOpen;
    private final String timeClose;
    private final int rating;
    private final int personReview;
    private final String phone;
}
