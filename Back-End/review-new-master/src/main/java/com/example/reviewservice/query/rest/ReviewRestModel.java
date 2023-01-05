package com.example.reviewservice.query.rest;

import lombok.Data;

import java.io.Serializable;

@Data
public class ReviewRestModel implements Serializable {
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


}
