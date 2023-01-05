package com.example.userreviewservice.core.event;

import lombok.Data;

@Data
public class UserUpdatedEvent {
    private String _id;
    private String username;
    private String name;
    private String email;
    private String birthday;
    private String phone;
    private String imageId;
    private String role;
}
