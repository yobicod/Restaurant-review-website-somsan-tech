package com.example.userreviewservice.query.rest;

import lombok.Data;

@Data
public class UserRestModel {
    private String _id;
    private String username;
    private String name;
    private String email;
    private String birthday;
    private String phone;
    private String imageId;
    private String role;
}
