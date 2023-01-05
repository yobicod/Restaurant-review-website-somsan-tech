package com.example.reviewservice.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@Data
public class UpdateReviewCommand {

    @TargetAggregateIdentifier
    private String _id;
    private String userId;

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


    //    private final String _id;
//    private final String name;
//    private final String branch;
//    private final String store_type;
//    private final String description;
//    private final String imageId;
//    private final String address;
//    private final String timeOpen;
//    private final String timeClose;
//    private final int rating;
//    private final int personReview;
//    private final String phone;
//    private final String flag;
//
//    public UpdateReviewCommand(String _id, String flag) {
//        this._id = _id;
//        this.flag = flag;
//    }
}
