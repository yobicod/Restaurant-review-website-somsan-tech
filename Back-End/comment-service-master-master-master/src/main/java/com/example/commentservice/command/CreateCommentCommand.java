package com.example.commentservice.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@Data
public class CreateCommentCommand {

    @TargetAggregateIdentifier
    private final String _id;
    private final String user;
    private final String userid;
    private final int rating;
    private final String description;
    private final String recommendMenu;
    private final String imageId;
    private final String time;
    private final int like;
    private final String reviewId;
    private final int report;
    private final boolean ban;
}
