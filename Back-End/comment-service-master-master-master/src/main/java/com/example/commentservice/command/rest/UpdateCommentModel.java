package com.example.commentservice.command.rest;

import lombok.Data;

import java.io.Serializable;

@Data
public class UpdateCommentModel implements Serializable {
    private String _id;
    private String user;
    private String userid;
    private int rating;
    private String description;
    private String recommendMenu;
    private String imageId;
    private String time;
    private int like;
    private String reviewId;
    private int report;
    private boolean ban;
}
