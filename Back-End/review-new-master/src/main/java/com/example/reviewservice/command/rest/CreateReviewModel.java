package com.example.reviewservice.command.rest;


import lombok.Data;

import java.io.Serializable;

@Data
public class CreateReviewModel implements Serializable {
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
}
