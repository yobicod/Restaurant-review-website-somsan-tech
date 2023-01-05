package com.example.userreviewservice.command.rest;

import lombok.Data;

@Data
public class CreateUserModel {

    private String username;
    private String name;
    private String email;
    private String birthday;
    private String phone;
    private String imageId;
    private String role;
}
