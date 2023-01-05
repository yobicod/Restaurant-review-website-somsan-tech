package com.example.reviewservice.core.event;

import lombok.Data;

@Data
public class ReviewUpdatedEvent {
//
//    private String _id;
//    private String flag;
//
//    public ReviewUpdatedEvent(String _id, String flag) {
//        this._id = _id;
//        this.flag = flag;
//    }
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
